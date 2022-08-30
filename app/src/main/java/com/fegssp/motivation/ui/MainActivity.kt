package com.fegssp.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.fegssp.motivation.infra.MotivationConstants
import com.fegssp.motivation.R
import com.fegssp.motivation.data.Mock
import com.fegssp.motivation.infra.SecurityPreferences
import com.fegssp.motivation.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityMainBinding
    var category: Int = MotivationConstants.FILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        handleUserName()
        handleFilter(R.id.img_all)
        setNewPhrase()

        binding.btnNewFrase.setOnClickListener(this)
        binding.imgAll.setOnClickListener(this)
        binding.imgHappy.setOnClickListener(this)
        binding.imgSunny.setOnClickListener(this)
        binding.txtUserId.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        handleUserName()
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_new_frase ->
                Toast.makeText(this, "Teste de botão", Toast.LENGTH_SHORT).show()

            R.id.img_all,
            R.id.img_happy,
            R.id.img_sunny ->
                handleFilter(view.id)
            R.id.txt_user_id ->
                nameEdit()
        }
    }

    private fun nameEdit(){
        startActivity(Intent(this, UserActivity::class.java))
        finish()
        SecurityPreferences(this).clearStoreString(MotivationConstants.KEY.USER_NAME)
    }

    private fun handleUserName() {
        binding.txtUserId.text =
            "${this.getString(R.string.hello)}, ${SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)}!"
    }

    private fun handleFilter(id: Int) {
        binding.imgAll.setColorFilter(ContextCompat.getColor(this, R.color.black))
        binding.imgHappy.setColorFilter(ContextCompat.getColor(this, R.color.black))
        binding.imgSunny.setColorFilter(ContextCompat.getColor(this, R.color.black))

        when (id) {
            R.id.img_all -> {
                binding.imgAll.setColorFilter(ContextCompat.getColor(this, R.color.white))
                category = MotivationConstants.FILTER.ALL

            }
            R.id.img_sunny -> {
                binding.imgSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
                category = MotivationConstants.FILTER.SUNNY
            }
            R.id.img_happy -> {
                binding.imgHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
                category = MotivationConstants.FILTER.HAPPY
            }
        }
        setNewPhrase()
    }

    private fun setNewPhrase(){
        binding.txtMsgMotivation.text = Mock().getPhrase(category, Locale.getDefault().language)
    }
}