package nl.avans.drivioapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nl.avans.drivioapp.model.Statistic
import nl.avans.drivioapp.repository.StatisticsRepository
import retrofit2.Response

private const val TAG = "StatisticsViewModel"

class StatisticsViewModel : ViewModel() {
    private val statisticsRepository = StatisticsRepository()

    private val _getStatisticsResponse: MutableLiveData<List<Statistic>> = MutableLiveData()
    val getStatisticsResponse: LiveData<List<Statistic>>
        get() = _getStatisticsResponse

    private val _getStatisticByIdResponse: MutableLiveData<Response<Statistic>> = MutableLiveData()
    val getStatisticByIdResponse: LiveData<Response<Statistic>>
        get() = _getStatisticByIdResponse

    private val _postStatisticResponse: MutableLiveData<Response<Unit>> = MutableLiveData()
    val postStatisticResponse: LiveData<Response<Unit>>
        get() = _postStatisticResponse

    private val _deleteStatisticResponse: MutableLiveData<Response<Unit>> = MutableLiveData()
    val deleteStatisticResponse: LiveData<Response<Unit>>
        get() = _deleteStatisticResponse

    private val _putStatisticResponse: MutableLiveData<Response<Unit>> = MutableLiveData()
    val putStatisticResponse: LiveData<Response<Unit>>
        get() = _putStatisticResponse

    init {
        getStatistics()
    }

    fun getStatistics() {
        viewModelScope.launch {
            _getStatisticsResponse.value = statisticsRepository.getStatistics()
        }
    }

    fun getStatisticById(statisticId: Int) {
        viewModelScope.launch {
            _getStatisticByIdResponse.value = statisticsRepository.getStatisticById(statisticId)
        }
    }

    fun postStatisticsWithResponse(statistic: Statistic) {
        viewModelScope.launch {
            _postStatisticResponse.value = statisticsRepository.postStatisticWithResponse(statistic)
        }
    }

    fun deleteStatisticsWithResponse(statisticId: Int) {
        viewModelScope.launch {
            _deleteStatisticResponse.value = statisticsRepository.deleteStatisticWithResponse(statisticId)
        }
    }

    fun putStatisticsWithResponse(statistic: Statistic) {
        viewModelScope.launch {
            _putStatisticResponse.value = statisticsRepository.putStatisticWithResponse(statistic)
        }
    }
}