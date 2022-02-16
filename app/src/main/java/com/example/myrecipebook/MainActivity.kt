package com.example.myrecipebook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myrecipebook.databinding.ActivityMainBinding
import com.example.myrecipebook.databinding.ActivityRecipeBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}