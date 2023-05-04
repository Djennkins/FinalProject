package com.example.jetpacknav.data.remote

import com.example.jetpacknav.data.remote.model.AnimalApiModel

object AnimalRepository {
    private val api = ApiFactory.animalApi

    suspend fun getAnimal(): AnimalApiModel? {
        val params = HashMap<String, String>()
        params.put("animal_type", "cat")
        params.put("amount", "1")

        val response = api.getAnimal(params)
        if(response.isSuccessful){
            if(response.body() != null){
                val body = response.body()
                return body!!
            }
        }
        return null
    }
}