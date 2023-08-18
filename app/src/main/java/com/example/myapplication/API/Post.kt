package com.example.myapplication.API

import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class Post {
    fun doPostRequest(url: String, loginRequest: RequestModel, callback: (String) -> Unit) {
        val client = OkHttpClient()

        val json = Gson().toJson(loginRequest)
        val requestBody = json.toRequestBody("application/json".toMediaTypeOrNull())

        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                callback("Error: ${e.message}")
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                if (response.isSuccessful) {
                    val responseBody = response.body?.string() ?: "Response body is empty."
                    callback(responseBody)
                } else {
                    callback("Error: ${response.code}")
                }
            }
        })

    }
}