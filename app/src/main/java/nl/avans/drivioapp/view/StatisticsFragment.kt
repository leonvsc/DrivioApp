package nl.avans.drivioapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import nl.avans.drivioapp.R
import nl.avans.drivioapp.adapter.StatisticsAdapter
import nl.avans.drivioapp.databinding.FragmentStatisticsBinding
import nl.avans.drivioapp.model.Statistic
import nl.avans.drivioapp.viewModel.StatisticsViewModel

class StatisticsFragment : Fragment(R.layout.fragment_statistics),
    StatisticsAdapter.OnItemClickListener{
    private var _binding: FragmentStatisticsBinding? = null;
    private val binding get() = _binding!!;
    private val statisticsViewModel: StatisticsViewModel by viewModels()
    private lateinit var statistic: List<Statistic>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatisticsBinding.inflate(inflater, container, false);
        val view = binding.root;
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        statisticsViewModel.getStatisticsResponse.observe(viewLifecycleOwner) {
            statistic = statisticsViewModel.getStatisticsResponse.value!!
            statistic = statistic.filter { it.user.userId == 47 }
            val recyclerView = binding.recyclerView
            recyclerView.adapter = StatisticsAdapter(statistic, this)
        }


        val swipeRefreshLayout = binding.swipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            statisticsViewModel.getStatistics()
        }
    }
    override fun onItemClick(position: Int) {
        statisticsViewModel.getStatisticsResponse.observe(viewLifecycleOwner) {
            setFragmentResult(
                "statisticId",
                bundleOf("statisticId" to statistic[position].statisticId)
            )
        }
//        replaceFragment(ReservationDetailsFragment())
    }

//    private fun replaceFragment(fragment: Fragment) {
//        val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
//        fragmentTransaction?.replace(R.id.flFragment, fragment)
//        fragmentTransaction?.commit()
//    }

}