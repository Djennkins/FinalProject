package com.example.jetpacknav.presentation.firstFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpacknav.data.local.CatDB
import com.example.jetpacknav.data.local.model.Animal
import com.example.jetpacknav.domain.animal.AnimalUseCase
import kotlinx.coroutines.launch

class FirstViewModel :ViewModel() {
    private val animalUseCase = AnimalUseCase

    val catCollection = MutableLiveData<ArrayList<Animal>>()

    fun getCat() {
        viewModelScope.launch {
            val v = animalUseCase.getCat()
            catCollection.postValue(v)
        }
    }
}