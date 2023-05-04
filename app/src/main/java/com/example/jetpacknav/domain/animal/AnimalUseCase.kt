package com.example.jetpacknav.domain.animal

import com.example.jetpacknav.data.local.CatDB
import com.example.jetpacknav.data.local.model.Animal
import com.example.jetpacknav.data.remote.AnimalRepository

object AnimalUseCase {

    private val animalRepository = AnimalRepository
    private val localRepository = CatDB()

    suspend fun getAnimal() : String{
        return animalRepository.getAnimal()?.text ?: String()
    }

    fun getCat() : ArrayList<Animal>{
        val listOfCats = ArrayList<Animal>()
        listOfCats.add(localRepository.cat)
        listOfCats.add(localRepository.cat2)
        listOfCats.add(localRepository.cat3)
        return listOfCats
    }
}