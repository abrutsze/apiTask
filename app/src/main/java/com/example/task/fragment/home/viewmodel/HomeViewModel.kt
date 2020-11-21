package com.example.task.fragment.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domian.interactors.GetDataInteractor
import com.example.entity.Constants
import com.example.entity.Constants.Companion.ERROR_NULL_DATA
import com.example.entity.Constants.Companion.INPUT_DATA_EMPTY
import com.example.entity.localmodels.DataModel
import com.example.entity.Result
import com.example.task.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val getDataInteractor: GetDataInteractor
) :
    ViewModel() {

    private val _getUserDataList by lazy { SingleLiveEvent<DataModel>() }
    val getDataModelDataList: LiveData<DataModel> = _getUserDataList
    private val _loadingData by lazy { SingleLiveEvent<Boolean>() }
    val loadingData: LiveData<Boolean> = _loadingData
    private val _errorNullData by lazy { SingleLiveEvent<String>() }
    val errorNullData get() = _errorNullData

    fun getDataList(number: String) {
        _loadingData.value = true
        viewModelScope.launch(Dispatchers.IO) {
            when (val userData = getDataInteractor.getDataResponse(number)) {
                is Result.Success -> withContext(Dispatchers.Main) {
                    _getUserDataList.value = userData.data
                    _loadingData.value = false
                }
                is Result.Error -> withContext(Dispatchers.Main) {
                    when (userData.errors.errorCode) {
                        ERROR_NULL_DATA -> {
                            userData.errors.errorMessage?.apply {
                                _errorNullData.value = userData.errors.errorMessage
                                _loadingData.value = false
                            }
                        }
                        INPUT_DATA_EMPTY -> {
                            userData.errors.errorMessage?.apply {
                                _errorNullData.value = userData.errors.errorMessage
                                _loadingData.value = false
                            }
                        }
                        else -> {
                        }
                    }
                }
            }
        }
    }


}