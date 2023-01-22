package com.example.invitation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.invitation.data.model.User
import com.example.invitation.databinding.ActivityCancelBinding
import com.example.invitation.presentation.viewmodel.InvitationViewModel
import com.example.invitation.presentation.viewmodel.InvitationViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CancelActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCancelBinding
    @Inject
    lateinit var factory: InvitationViewModelFactory
    lateinit var viewModel: InvitationViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCancelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, factory)[InvitationViewModel::class.java]
        binding.apply {
            btnBack.setOnClickListener {
                val intent = Intent(this@CancelActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            btnCancel.setOnClickListener {
                val obj = intent.getParcelableExtra<User>("object") as User
                Log.i("Invite","$obj already present.")
                viewModel.cancelInvite(user = obj)
                Toast.makeText(
                    this@CancelActivity,
                    "Invite Successfully Cancelled.",
                    Toast.LENGTH_LONG
                )
                    .show()
                val intent = Intent(this@CancelActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

}

