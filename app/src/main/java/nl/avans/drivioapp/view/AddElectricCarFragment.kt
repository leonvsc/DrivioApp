package nl.avans.drivioapp.view

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import nl.avans.drivioapp.AWS.ImagesS3AWS
import nl.avans.drivioapp.AWS.`s3-constants`
import nl.avans.drivioapp.R
import nl.avans.drivioapp.databinding.FragmentAddElectricCarBinding
import nl.avans.drivioapp.model.ElectricCar
import nl.avans.drivioapp.model.User1
import nl.avans.drivioapp.viewModel.AddElectricCarViewModel
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class AddElectricCarFragment : Fragment(R.layout.fragment_add_electric_car) {
    private val addElectricCarViewModel: AddElectricCarViewModel by viewModels();
    private var _binding: FragmentAddElectricCarBinding? = null;
    private val binding get() = _binding!!;
    private val REQUEST_CODE = 100
    private val REQUEST_IMAGE_CAPTURE = 1
    private var imageUri: Uri? = null
    private var file: File? = null
    private var imageToS3: Unit? = null
    private var imageBitMap: Bitmap? = null
    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MM-dd-HH-mm")
    private val currentDateTime: String = LocalDateTime.now().format(formatter)


    private fun dispatchTakePictureIntent() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, R.string.take_picture)
        values.put(MediaStore.Images.Media.DESCRIPTION, R.string.take_picture_description)

        imageUri = activity?.contentResolver?.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(activity, "Failed, picture not uploaded", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)
    }

    @Deprecated("Deprecated in Java but not in Kotlin")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            imageUri = data?.data
//            val source =
//                imageUri?.let { ImageDecoder.createSource(requireActivity().contentResolver, it) }
//            imageBitMap = source?.let { ImageDecoder.decodeBitmap(it) }
            binding.ivUploadedImage.setImageURI(imageUri)
//
//            val inputStream: InputStream? = imageUri?.let {
//                activity?.contentResolver?.openInputStream(
//                    it
//                )
//            }
            file = File.createTempFile("image", imageUri!!.lastPathSegment)
            println("File in upload is : $file")
//            val outStream: OutputStream = FileOutputStream(file)

//            imageToS3 = outStream.write(inputStream!!.readBytes())

        } else if (resultCode == Activity.RESULT_OK) {
            binding.ivUploadedImage.setImageURI(imageUri)
            println("Picture data is: $imageUri")
            val inputStream: InputStream? = imageUri?.let {
                activity?.contentResolver?.openInputStream(
                    it
                )
            }
            file = File.createTempFile("image", imageUri!!.lastPathSegment)
            println("File in camera is : $file")
            val outStream: OutputStream = FileOutputStream(file)

            imageToS3 = outStream.write(inputStream!!.readBytes())
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddElectricCarBinding.inflate(inflater, container, false);
        val view = binding.root;
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState);

        binding.addPhotoBtn.setOnClickListener {
            openGalleryForImage()
        }

        binding.cameraBtn.setOnClickListener {
            dispatchTakePictureIntent()
        }

        val imageView: ImageView = binding.ivUploadedImage
        val url = "https://images-drivio-app.s3.eu-west-1.amazonaws.com/01-15-12-15.jpg"
        Picasso.get().load(url).into(imageView)

        binding.postElectricCarBtn.setOnClickListener {

//            val fastChargeSpeed = binding.etFastChargeSpeed.text.toString().toInt()
//            val carRange = binding.etCarRange.text.toString().toInt()
//            val chargeConnection = binding.etChargeConnection.text.toString()
//            val buildYear = binding.etBuildYear.text.toString().toInt()
//            val numberPlate = binding.etNumberPlate.text.toString()
//            val chargeSpeed = binding.etChargeSpeed.text.toString().toInt()
//            val carType = binding.etCarType.text.toString()
//            val fuelType = binding.etFuelType.text.toString()
//            val model = binding.etModel.text.toString()
//            val whPerKm = binding.etWhPerKm.text.toString().toInt()
//            val gearBox = binding.etGearBox.text.toString()
//            val brand = binding.etBrand.text.toString()
//
//            val electricCar = ElectricCar(
//                null,
//                fastChargeSpeed,
//                carRange,
//                chargeConnection,
//                buildYear,
//                numberPlate,
//                chargeSpeed,
//                carType,
//                fuelType,
//                model,
//                whPerKm,
//                gearBox,
//                brand,
//                User1(23)
//            )
//            addElectricCarViewModel.postElectricCarWithResponse(electricCar);
            addElectricCarViewModel.putImage(`s3-constants`.BUCKET_NAME,
                "$currentDateTime.jpg", file.toString())

            addElectricCarViewModel.postElectricCarResponse.observe(viewLifecycleOwner) {
                val response = addElectricCarViewModel.postElectricCarResponse.value

                if (response?.code() == 200) {
                    Toast.makeText(activity, "Success!!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(activity, "Failed!!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }
}