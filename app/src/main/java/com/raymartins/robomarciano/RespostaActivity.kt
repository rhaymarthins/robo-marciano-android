package com.raymartins.robomarciano

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RespostaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resposta)

        val textResposta = findViewById<TextView>(R.id.textResposta)
        val btnVoltar = findViewById<Button>(R.id.btnVoltar)
        val imageFundo = findViewById<ImageView>(R.id.imageFundo)

        // Recebe a resposta e a imagem enviadas pela MainActivity
        val resposta = intent.getStringExtra("resposta") ?: "Nao entendi"
        val imagemId = intent.getIntExtra("imagemFundo", R.drawable.bg_outro)

        textResposta.text = resposta
        imageFundo.setImageResource(imagemId)

        btnVoltar.setOnClickListener {
            finish()
        }
    }
}