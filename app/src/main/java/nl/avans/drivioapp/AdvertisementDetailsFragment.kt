package nl.avans.drivioapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import nl.avans.drivioapp.databinding.FragmentAdvertisementDetailsBinding

class AdvertisementDetailsFragment : Fragment(R.layout.fragment_advertisement_details) {

    private var _binding: FragmentAdvertisementDetailsBinding? = null;
    private val binding get() = _binding!!;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdvertisementDetailsBinding.inflate(inflater, container, false);
        val view = binding.root;
        return view;
    }

    // TODO: Mooier maken door met het advertisementId een getById Api call uit te voeren.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvTitle: TextView = binding.tvTitle
        val tvDescription: TextView = binding.tvDescription
        val tvPrice: TextView = binding.tvPrice
        val tvStartDate: TextView = binding.tvStartDate
        val tvEndDate: TextView = binding.tvEndDate

        setFragmentResultListener("title") { requestKey, bundle ->
            val title = bundle.getString("title")
            tvTitle.text = title
        }

        setFragmentResultListener("description") { requestKey, bundle ->
            val description = bundle.getString("description")
            tvDescription.text = description
        }

        setFragmentResultListener("price") { requestKey, bundle ->
            val price = bundle.getDouble("price")
            tvPrice.text = price.toString()
        }

        setFragmentResultListener("startDate") { requestKey, bundle ->
            val startDate = bundle.getString("startDate")
            tvStartDate.text = startDate
        }

        setFragmentResultListener("endDate") { requestKey, bundle ->
            val endDate = bundle.getString("endDate")
            tvEndDate.text = endDate
        }
    }
}