package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.myapplication.API.*
import org.json.JSONArray
import org.json.JSONObject
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import okhttp3.logging.HttpLoggingInterceptor


class MainActivity : AppCompatActivity() {
    fun jsonArrayToKotlinArray(jsonArray: JSONArray): Array<Any> {
        val kotlinArray = mutableListOf<Any>()

        for (i in 0 until jsonArray.length()) {
            val element = jsonArray.get(i)
            if (element is JSONObject) {
                // Convert JSONObject to a Kotlin Map (Dictionary)
                kotlinArray.add(jsonObjectToKotlinMap(element))
            } else {
                // Add other types directly to the array
                kotlinArray.add(element)
            }
        }

        return kotlinArray.toTypedArray()
    }

    fun jsonObjectToKotlinMap(jsonObject: JSONObject): Map<String, Any> {
        val map = mutableMapOf<String, Any>()
        val keys = jsonObject.keys()

        while (keys.hasNext()) {
            val key = keys.next()
            val value = jsonObject.get(key)

            map[key] = value
        }

        return map
    }

    val client = Menu().client

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // "context" must be an Activity, Service or Application object from your app.


        val qkBtn = findViewById<Button>(R.id.qk)
        qkBtn.setOnClickListener{
            lifecycleScope.launch {
                val conn = client.conn()
                client.sendEmail(conn,"easyTest2@email.com")
                val myInt = Intent(this@MainActivity, Menu::class.java)
                startActivity(myInt)
            }
        }

        val btnSign = findViewById<Button>(R.id.btnSign)
        btnSign.setOnClickListener {
            val txtEmail = findViewById<EditText>(R.id.editTextTextEmailAddress)
            val email = txtEmail.text.toString()
            val txtPsw = findViewById<EditText>(R.id.editTextTextPassword)
            val psw = txtPsw.text.toString()

            val obj = RequestModel(email,psw)
            val newR = Post()

            Log.d("TAG",email)
            Log.d("TAG",psw)
            lifecycleScope.launch {

                val conn = client.conn()
                client.sendEmail(conn,email)

                newR.doPostRequest("https://mainapi-istq.onrender.com/signin", obj) { response ->
                    println("Response: $response")
                    val res = JSONArray(response)
                    val kotlinArray = jsonArrayToKotlinArray(res)
                    if (kotlinArray[1] == true) {
                        val myInt = Intent(this@MainActivity, Menu::class.java)
                        startActivity(myInt)
                    } else {
                        Log.d("TAG","No bro")
                        val myInt = Intent(this@MainActivity, MainActivity::class.java)
                        startActivity(myInt)
                    }
                }
            }
        }


    }


}