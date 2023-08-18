package com.example.myapplication.API
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Apinterface {
    @POST("/signup")
    fun requestLogin(@Body requestModel: RequestModel) : Call<ResponseClass>
}