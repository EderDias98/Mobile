package com.example.mudanca_pagina

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mudanca_pagina.databinding.ActivityFrasesBinding

enum class Categoria {
    GATO,
    CACHORRO,
    NENHUM
}

class FrasesActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityFrasesBinding
    private lateinit var sp: SharedPreferences

    private var categoriaSelecionada: Categoria = Categoria.NENHUM

    private val frasesGato = listOf(
        "Os gatos não possuem dono, possuem mordomo.",
        "Gatos deixam marcas de patas no seu coração.",
        "Um miado é uma mensagem de puro amor.",
        "Se os gatos pudessem falar, eles não falariam."
    )

    private val frasesCachorro = listOf(
        "O cão é o único ser que te ama mais do que a si mesmo.",
        "A felicidade é um rabo abanando.",
        "Quem tem um cachorro nunca está sozinho.",
        "Um cão não julga, apenas ama."
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFrasesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        sp = getSharedPreferences("CHAVE_ACESSO", Context.MODE_PRIVATE)
        val nomeSalvo = sp.getString("boas_vindas", "Visitante")

        binding.boasVindas.text = "Olá, $nomeSalvo"
        categoriaSelecionada = Categoria.NENHUM

        binding.gato.setOnClickListener(this)
        binding.cachorro.setOnClickListener(this)
        binding.frase.setOnClickListener(this)
        binding.btnNovaFrase.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.gato) {
            binding.gato.imageTintList = ContextCompat.getColorStateList(this, R.color.amarelo)
            binding.cachorro.imageTintList = ContextCompat.getColorStateList(this, R.color.white)
            categoriaSelecionada = Categoria.GATO

        } else if (view.id == R.id.cachorro) {
            binding.cachorro.imageTintList = ContextCompat.getColorStateList(this, R.color.amarelo)
            binding.gato.imageTintList = ContextCompat.getColorStateList(this, R.color.white)
            categoriaSelecionada = Categoria.CACHORRO

        } else if (view.id == R.id.btn_nova_frase) {
            gerarNovaFrase()
        }
    }

    private fun gerarNovaFrase() {
        val frase = when (categoriaSelecionada) {
            Categoria.GATO -> frasesGato.random()
            Categoria.CACHORRO -> frasesCachorro.random()
            Categoria.NENHUM -> "Selecione a categoria Gato ou Cachorro!"
        }
        binding.frase.text = frase
    }
}
