package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMenuBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Menu : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding
    val client = Client()


    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val menuExitBtn = findViewById<Button>(R.id.menuExitBtn)
        menuExitBtn.setOnClickListener{
            CoroutineScope(Dispatchers.Main).launch {
                //val con = client.conn()
                //client.sendEmail(con,"exit")
                val myInt = Intent(this@Menu, MainActivity::class.java)
                startActivity(myInt)
            }
        }

        val goBtn = findViewById<ImageView>(R.id.ex5)
        goBtn.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val num = 5

                client.conn()
                //client.sendNum(num)
                val myInt = Intent(this@Menu,PartOne::class.java).also {
                    it.putExtra("INTEGER_KEY",num)
                    startActivity(it)
                }
            }
        }

        val ex1Btn = findViewById<ImageView>(R.id.ex1)
        ex1Btn.setOnClickListener{
            CoroutineScope(Dispatchers.Main).launch {
                val num = 1
                client.conn()
                //client.sendNum(num)
                val myInt = Intent(this@Menu,PartOne::class.java).also {
                    it.putExtra("INTEGER_KEY",num)
                    startActivity(it)
                }
            }
        }
        val ex2Btn = findViewById<ImageView>(R.id.ex2)
        ex2Btn.setOnClickListener{
            CoroutineScope(Dispatchers.Main).launch {
                val num = 2
                client.conn()
                //client.sendNum(num)
                val myInt = Intent(this@Menu,PartOne::class.java).also {
                    it.putExtra("INTEGER_KEY",num)
                    startActivity(it)
                }
            }
        }
        val ex3Btn = findViewById<ImageView>(R.id.ex3)
        ex3Btn.setOnClickListener{
            CoroutineScope(Dispatchers.Main).launch {
                val num = 3
                client.conn()
                //client.sendNum(num)
                val myInt = Intent(this@Menu,PartOne::class.java).also {
                    it.putExtra("INTEGER_KEY",num)
                    startActivity(it)
                }
            }
        }
        val ex4Btn = findViewById<ImageView>(R.id.ex4)
        ex4Btn.setOnClickListener{
            CoroutineScope(Dispatchers.Main).launch {
                val num = 4

                client.conn()
                //client.sendNum(num)
                val myInt = Intent(this@Menu,PartOne::class.java).also {
                    it.putExtra("INTEGER_KEY",num)
                    startActivity(it)
                }
            }
        }
    }
}