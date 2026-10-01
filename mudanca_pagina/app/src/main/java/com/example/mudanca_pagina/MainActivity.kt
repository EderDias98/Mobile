package com.example.mudanca_pagina

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mudanca_pagina.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        sp = getSharedPreferences("CHAVE_ACESSO", Context.MODE_PRIVATE)

        binding.btnGuardar.setOnClickListener(this)
        val boasVindas = sp.getString("boas_vindas", "")
        if (!boasVindas.isNullOrEmpty()) {
            startActivity(Intent(this, FrasesActivity::class.java))
            finish()
        }

    }




    override fun onClick(view: View) {

        if(view.id == R.id.btn_guardar){

            sp.edit().putString("boas_vindas", binding.nome.text.toString()).apply()
            startActivity(Intent(this, FrasesActivity::class.java))
            finish()

        }


    }
}
