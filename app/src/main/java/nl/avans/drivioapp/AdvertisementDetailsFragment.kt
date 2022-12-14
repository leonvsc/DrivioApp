package nl.avans.drivioapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import nl.avans.drivioapp.databinding.FragmentAdvertisementDetailsBinding
import nl.avans.drivioapp.databinding.FragmentDiscoverBinding

class AdvertisementDetailsFragment : Fragment(R.layout.fragment_advertisement_details) {

    private var _binding: FragmentAdvertisementDetailsBinding? = null;
    private val binding get() = _binding!!;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdvertisementDetailsBinding.inflate(inflater, container, false);
        val view = binding.root;

    setFragmentResultListener("requestKey") { requestKey, bundle ->
        val result = bundle.getInt("bundleKey")
        val textView: TextView = binding.tvResult

        textView.text = result.toString()
    }

        return view;
    }

}