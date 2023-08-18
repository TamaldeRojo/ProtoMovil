package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class PartOne : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_part_one)

        val num = intent.getIntExtra("INTEGER_KEY",0)
        Log.d("The Num, Part One", num.toString())

        val imageView = findViewById<ImageView>(R.id.theEx)
        when (num) {
            1 -> imageView.setImageResource(R.raw.senta)
            2 -> imageView.setImageResource(R.raw.ads)
            3 -> imageView.setImageResource(R.raw.pesa)
            4 -> imageView.setImageResource(R.raw.laga)
            5 -> imageView.setImageResource(R.raw.lata)
        }
        val exitBtn = findViewById<Button>(R.id.exitBtn)
        exitBtn.setOnClickListener{
            val myInt = Intent(this@PartOne,Menu::class.java)
            startActivity(myInt)
        }

        val startBtn = findViewById<Button>(R.id.startBtn)
        startBtn.setOnClickListener{
            val myInt = Intent(this@PartOne,PartTwo::class.java).also{
                it.putExtra("INTEGER_KEY",num)
                startActivity(it)
            }
        }
    }
}