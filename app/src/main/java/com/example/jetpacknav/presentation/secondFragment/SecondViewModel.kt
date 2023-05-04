package com.example.jetpacknav.presentation.secondFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpacknav.domain.animal.AnimalUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okio.Timeout

class SecondViewModel : ViewModel() {
    private val animalUseCase = AnimalUseCase

    val fact = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()

    fun getFact() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val v = animalUseCase.getAnimal()
            //delay(500)
            fact.postValue(v)
            isLoading.postValue(false)
        }
    }
}