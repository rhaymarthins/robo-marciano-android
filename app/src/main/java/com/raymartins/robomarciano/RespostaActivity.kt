package com.raymartins.robomarciano

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RespostaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resposta)

        val textResposta = findViewById<TextView>(R.id.textResposta)
        val btnVoltar = findViewById<Button>(R.id.btnVoltar)

        // Recebe a resposta enviada pela MainActivity
        val resposta = intent.getStringExtra("resposta") ?: "Nao entendi"
        textResposta.text = resposta

        // Fecha esta tela e volta para a anterior
        btnVoltar.setOnClickListener {
            finish()
        }
    }
}