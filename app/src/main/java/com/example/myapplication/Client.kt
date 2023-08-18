package com.example.myapplication

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.Socket

class Client{

    // Member variable to hold the socket instance
    private var socket: Socket? = null

    //val viewModel: MyViewModel by viewModels()

    suspend fun conn(): Socket {
        // Set the server host and port
        val serverHost = "192.168.0.7"
        val serverPort = 5050

        // Connect to the server
        socket = withContext(Dispatchers.IO) {
           Socket(serverHost, serverPort)
        }
         return socket as Socket

    }

    suspend fun sendEmail(conn: Socket,data: String){
        val writer = OutputStreamWriter(withContext(Dispatchers.IO) {
            conn.getOutputStream()
        })
        withContext(Dispatchers.IO) {
            writer.write(data)
            writer.flush()
            writer.close()
            conn.close()
        }

    }

    suspend fun sendNum(num : Int){
        if (socket == null || socket!!.isClosed) {
            Log.d("error","en el if bro")
            println(socket.toString())
            // Handle the case when the socket is not initialized or closed
            return
        }
        //writer.println(message)
        withContext(Dispatchers.IO) {
            socket!!.getOutputStream().write(num.toString().toByteArray())
        }
    }

    suspend fun getNum(conn: Socket): Int {

        val writer = OutputStreamWriter(withContext(Dispatchers.IO) {
            conn.getOutputStream()
        })
        withContext(Dispatchers.IO) {
            writer.write("go")
        }

        Log.d("[+]","Primero")
        val reader = InputStreamReader(withContext(Dispatchers.IO) {
            conn.getInputStream()
        })
        val bufferedReader = BufferedReader(reader)
        Log.d("[+]","Segundo")


        val data = withContext(Dispatchers.IO) {
            bufferedReader.readLine()
        }


        Log.d("[+]","Tercero")
        val number = data.toInt()
        println("Received number from client: $number")
        return number
    }


}

