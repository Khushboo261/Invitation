package com.example.invitation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.invitation.databinding.ActivityCongratulationsBinding
import com.example.invitation.databinding.ActivityMainBinding

class CongratulationsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCongratulationsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCongratulationsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            Glide.with(imageView.context)
                .load("https://img.freepik.com/premium-vector/congratulations-celebrations-flags-balloons-vector-clipart_616819-117.jpg?w=2000")
                .into(imageView)
        }
    }
}