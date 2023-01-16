package nl.avans.drivioapp.view

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import nl.avans.drivioapp.AWS.`s3-constants`
import nl.avans.drivioapp.R
import nl.avans.drivioapp.databinding.FragmentAddElectricCarBinding
import nl.avans.drivioapp.model.ElectricCar
import nl.avans.drivioapp.model.User1
import nl.avans.drivioapp.viewModel.AddElectricCarViewModel
import nl.avans.drivioapp.viewModel.MyCarsViewModel
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class UpdateCarFragment : Fragment(R.layout.fragment_add_electric_car) {
    private val addElectricCarViewModel: AddElectricCarViewModel by viewModels();
    private var _binding: FragmentAddElectricCarBinding? = null;
    private val binding get() = _binding!!;
    private val myCarsViewModel: MyCarsViewModel by viewModels()
    private var carId: Int? = null;
    private val REQUEST_CODE = 100
    private val REQUEST_IMAGE_CAPTURE = 1
    private var imageUri: Uri? = null
    private var file: File? = null
    private var imageToS3: Unit? = null
    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MM-dd-HH-mm")
    private var fileName: String? = null

    // Function to open camera from phone
    private fun dispatchTakePictureIntent() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, R.string.take_picture)
        values.put(MediaStore.Images.Media.DESCRIPTION, R.string.take_picture_description)

        imageUri =
            activity?.contentResolver?.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(activity, "Failed, picture not uploaded", Toast.LENGTH_SHORT).show()
        }
    }

    // Function to select image from phone
    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)
    }

    // Function to handle the uploaded or selected image which than can be sent to AWS S3
    @Deprecated("Deprecated in Java but not in Kotlin")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            imageUri = data?.data
            binding.ivUploadedImage.setImageURI(imageUri)
            val inputStream: InputStream? = imageUri?.let {
                activity?.contentResolver?.openInputStream(
                    it
                )
            }
            file = File.createTempFile("image", imageUri!!.lastPathSegment)
            val outStream: OutputStream = FileOutputStream(file)
            imageToS3 = outStream.write(inputStream!!.readBytes())
            fileName = LocalDateTime.now().format(formatter) + ".jpg"

        } else if (resultCode == Activity.RESULT_OK) {
            binding.ivUploadedImage.setImageURI(imageUri)
            val inputStream: InputStream? = imageUri?.let {
                activity?.contentResolver?.openInputStream(
                    it
                )
            }
            file = File.createTempFile("image", imageUri!!.lastPathSegment)
            val outStream: OutputStream = FileOutputStream(file)
            imageToS3 = outStream.write(inputStream!!.readBytes())
            fileName = LocalDateTime.now().format(formatter) + ".jpg"
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
        super.onViewCreated(view, savedInstanceState)

        binding.addPhotoBtn.setOnClickListener {
            openGalleryForImage()
        }

        binding.cameraBtn.setOnClickListener {
            dispatchTakePictureIntent()
        }

        setFragmentResultListener("carDetailsId") { requestKey, bundle ->
            val carId = bundle.getInt("carDetailsId")
            myCarsViewModel.getElectricCarById(carId)
        }

        // Fill in the et field with the values from the db and show the connected image
        myCarsViewModel.getElectricCarByIdResponse.observe(viewLifecycleOwner) {
            val myCar = myCarsViewModel.getElectricCarByIdResponse.value
            binding.etFastChargeSpeed.setText(myCar?.body()?.fastChargeSpeed.toString())
            binding.etCarRange.setText(myCar?.body()?.carRange.toString())
            binding.etChargeConnection.setText(myCar?.body()?.chargeConnection.toString())
            binding.etBuildYear.setText(myCar?.body()?.buildYear.toString())
            binding.etNumberPlate.setText(myCar?.body()?.numberPlate.toString())
            binding.etChargeSpeed.setText(myCar?.body()?.chargeSpeed.toString())
            binding.etCarType.setText(myCar?.body()?.carType.toString())
            binding.etFuelType.setText(myCar?.body()?.fuelType.toString())
            binding.etModel.setText(myCar?.body()?.model.toString())
            binding.etWhPerKm.setText(myCar?.body()?.whPerKm.toString())
            binding.etGearBox.setText(myCar?.body()?.gearBox.toString())
            binding.etBrand.setText(myCar?.body()?.brand.toString())
            fileName = myCar?.body()?.imageUrl
            val url = "https://images-drivio-app.s3.eu-west-1.amazonaws.com/$fileName"
            Picasso.get().load(url).into(binding.ivUploadedImage)
        }

        // Clicking the put button will post the information noted in the edit text fields
        binding.postElectricCarBtn.setOnClickListener {

            val fastChargeSpeed = binding.etFastChargeSpeed.text.toString().toInt()
            val carRange = binding.etCarRange.text.toString().toInt()
            val chargeConnection = binding.etChargeConnection.text.toString()
            val buildYear = binding.etBuildYear.text.toString().toInt()
            val numberPlate = binding.etNumberPlate.text.toString()
            val chargeSpeed = binding.etChargeSpeed.text.toString().toInt()
            val carType = binding.etCarType.text.toString()
            val fuelType = binding.etFuelType.text.toString()
            val model = binding.etModel.text.toString()
            val whPerKm = binding.etWhPerKm.text.toString().toInt()
            val gearBox = binding.etGearBox.text.toString()
            val brand = binding.etBrand.text.toString()

            val electricCar = ElectricCar(
                carId,
                fileName,
                fastChargeSpeed,
                carRange,
                chargeConnection,
                buildYear,
                numberPlate,
                chargeSpeed,
                carType,
                fuelType,
                model,
                whPerKm,
                gearBox,
                brand,
                User1(23),
                0.0,
                0.0
            )
            addElectricCarViewModel.putElectricCarWithResponse(electricCar)
            // When the post button is clicked and a new image is selected, the image will be uploaded to an S3 bucket
            if (file != null) {
                fileName?.let { it1 ->
                    addElectricCarViewModel.putImage(
                        `s3-constants`.BUCKET_NAME,
                        it1, file.toString()
                    )
                }
                addElectricCarViewModel.deleteImage(`s3-constants`.BUCKET_NAME, "FILE name")
                // TODO: Change file name to the correct name of the old file
            }

            addElectricCarViewModel.putElectricCarWithResponse.observe(viewLifecycleOwner) {
                val response = addElectricCarViewModel.putElectricCarWithResponse.value
                if (response?.code() == 200) {
                    Toast.makeText(activity, "Success!!", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_updateCarFragment_to_myCarsFragment)
                } else {
                    Toast.makeText(activity, "Failed!!", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}


