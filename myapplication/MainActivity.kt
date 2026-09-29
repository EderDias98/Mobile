package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGuardar.setOnClickListener(this) 



    }




    override fun onClick(view: View) {

        if(view.id == R.id.btn_guardar){
            val sp: SharedPreferences =
                applicationContext.getSharedPreferences("CHAVE_ACESSO", Context.MODE_PRIVATE)

            sp.edit().putString("boas_vindas", binding.nome.text.toString()).apply()
            startActivity(Intent(this, MainActivity2::class.java))
            finish()

        }


    }
}
