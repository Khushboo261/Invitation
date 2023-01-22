package com.example.invitation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.invitation.data.model.User
import com.example.invitation.data.util.Resource
import com.example.invitation.databinding.ActivityMainBinding
import com.example.invitation.presentation.viewmodel.InvitationViewModel
import com.example.invitation.presentation.viewmodel.InvitationViewModelFactory
import com.google.gson.JsonObject
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: InvitationViewModelFactory
    lateinit var viewModel: InvitationViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, factory)[InvitationViewModel::class.java]

        binding.apply {
            btnInvite.setOnClickListener {
                getInvitation()
            }

        }
        binding.etName.setText("Khushboo")
        binding.etEmail.setText("khushbooruparelia7201@gmail.com")
        binding.etConfirmEmail.setText("khushbooruparelia7201@gmail.com")
        viewModel.data.observe(this@MainActivity, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let {
                        Log.i("Invite", "$it")
                        val name = binding.etName.text.toString()
                        val email = binding.etEmail.text.toString()
                        try {
                            Toast.makeText(
                                this@MainActivity,
                                "$name Registered",
                                Toast.LENGTH_LONG
                            )
                                .show()
                            viewModel.saveInvite(
                                user = User(
                                    name = name,
                                    email = email
                                )
                            )
                            Log.i("Invite", "$name - $email")
                            val intent = Intent(this@MainActivity, CongratulationsActivity::class.java)
                            startActivity(intent)
                            finish()
                        } catch (e: Exception) {
                            Log.i("Invite", "An error occurred $e")
                        }
                    }
                }
                is Resource.Error -> {
                    response.message?.let {
                        Toast.makeText(
                            this@MainActivity,
                            "An error occurred $it",
                            Toast.LENGTH_LONG
                        ).show()
                        Log.i("Invite", "$it")
                    }
                }
                else -> {
                    response.message?.let {
                        Toast.makeText(
                            this@MainActivity,
                            "An error occurred $it",
                            Toast.LENGTH_LONG
                        ).show()
                        Log.i("Invite", "$it")
                    }
                }
            }
        })

        viewModel.checkUserLivedata.observe(this) {
            it?.let {
                Log.i("Invite", "email already exist!")
                Toast.makeText(
                    this@MainActivity,
                    "email already exist!",
                    Toast.LENGTH_LONG
                ).show()
                val intent = Intent(this@MainActivity, CancelActivity::class.java)
                intent.putExtra("object", it)
                startActivity(intent)
                finish()
            } ?: kotlin.run { 
                binding.apply {
                    val name = etName.text.toString()
                    val email = etEmail.text.toString()
                    val jsonObject = JsonObject()
                    try {
                        jsonObject.addProperty("name", name)
                        jsonObject.addProperty("email", email)
                        viewModel.getInvite(jsonObject)
                    } catch (e: Exception) {
                        Toast.makeText(
                            this@MainActivity,
                            "No input given by the user",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    private fun getInvitation() {
        binding.apply {
            val name = etName.text.toString()
            val email = etEmail.text.toString()
            val confirmEmail = etConfirmEmail.text.toString()
            val jsonObject = JsonObject()
            try {
                jsonObject.addProperty("name", name)
                jsonObject.addProperty("email", email)
            } catch (e: Exception) {
                Toast.makeText(
                    this@MainActivity,
                    "No input given by the user",
                    Toast.LENGTH_LONG
                ).show()
            }

            if (email != confirmEmail) {
                Toast.makeText(
                    this@MainActivity,
                    "Email $email & confirm email $confirmEmail does not match!",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.checkUser(email)
                }
            }
        }
    }
}
