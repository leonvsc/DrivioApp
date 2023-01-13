package nl.avans.drivioapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import nl.avans.drivioapp.R
import nl.avans.drivioapp.databinding.FragmentStatisticDetailsBinding
import nl.avans.drivioapp.model.Statistic
import nl.avans.drivioapp.viewModel.StatisticsViewModel
import retrofit2.Response

class StatisticDetailsFragment : Fragment(R.layout.fragment_statistic_details) {

    private var _binding: FragmentStatisticDetailsBinding? = null;
    private val binding get() = _binding!!;
    private val statisticViewModel: StatisticsViewModel by viewModels()
    private lateinit var statistic: Response<Statistic>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStatisticDetailsBinding.inflate(inflater, container, false);
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvId: TextView = binding.tvId
        val tvName: TextView = binding.tvName
        val tvValue: TextView = binding.tvValue

        setFragmentResultListener("statisticKey") { requestKey, bundle ->
            val statisticId = bundle.getInt("statisticId")
            statisticViewModel.getStatisticById(statisticId)
        }

        statisticViewModel.getStatisticByIdResponse.observe(viewLifecycleOwner) {
            statistic = statisticViewModel.getStatisticByIdResponse.value!!

            tvId.text = statistic.body()?.statisticId.toString()
            tvName.text = statistic.body()?.name.toString()
            tvValue.text = statistic.body()?.value.toString()
        }
    }
}