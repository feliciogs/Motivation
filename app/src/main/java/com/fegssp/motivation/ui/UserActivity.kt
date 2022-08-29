package com.fegssp.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.fegssp.motivation.infra.MotivationConstants
import com.fegssp.motivation.R
import com.fegssp.motivation.infra.SecurityPreferences
import com.fegssp.motivation.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        verifyUserName()
        binding.btnSaveUser.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.btn_save_user) {
            handleSave()
        }
    }

    private fun verifyUserName() {
        val userAux = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        if (userAux != ""){
           startMainActivity()
        }
    }

    private fun handleSave() {
        val name = binding.edtUserName.text.toString()
        if (name != "") {
            SecurityPreferences(this).storeString(MotivationConstants.KEY.USER_NAME, name)
            startMainActivity()
        } else {
            Toast.makeText(this, getString(R.string.validation_name_user), Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun startMainActivity(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}