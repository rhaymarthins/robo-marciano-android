package com.raymartins.robomarciano

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.raymartins.robomarciano.robot.AcaoPersonalizada
import com.raymartins.robomarciano.robot.MarcianoPremiun

class MainActivity : AppCompatActivity() {

    private val curiosidades = listOf(
        "Polvos tem tres coracoes e sangue azul.",
        "A Lua se afasta da Terra 3,8 cm por ano.",
        "Mel nunca estraga se guardado corretamente.",
        "O ser humano e o unico animal que ruboriza."
    )
    private var indiceCuriosidade = 0

    // Acao personalizada: conta uma curiosidade diferente a cada vez
    private val acaoCuriosidade = object : AcaoPersonalizada {
        override fun executar(): String {
            val texto = curiosidades[indiceCuriosidade % curiosidades.size]
            indiceCuriosidade++
            return "Curiosidade do dia: $texto"
        }
    }

    // Robo criado uma unica vez e reaproveitado durante toda a sessao
    private val marciano = MarcianoPremiun(acaoCuriosidade)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editMensagem = findViewById<EditText>(R.id.editMensagem)
        val btnEnviar = findViewById<Button>(R.id.btnEnviar)

        btnEnviar.setOnClickListener {
            val mensagem = editMensagem.text.toString()

            // Verifica se eh operacao matematica com numeros
            val partes = mensagem.trim().split(" ")
            val operacoes = listOf("some", "subtraia", "multiplique", "divida")

            val resposta = if (partes[0].lowercase() in operacoes && partes.size >= 3) {
                val palavrasIgnoradas = listOf("e", "mais", "com", "de", "por", "menos")
                val numeros = partes.drop(1)
                    .filter { it.lowercase() !in palavrasIgnoradas }
                    .mapNotNull { it.toDoubleOrNull() }
                    .toDoubleArray()
                marciano.responda(partes[0], *numeros)
            } else {
                marciano.responda(mensagem)
            }

            // Escolhe a imagem de fundo baseada na resposta do robo
            val imagemFundo = when {
                resposta.startsWith("Certamente") -> R.drawable.bg_pergunta
                resposta.startsWith("Opa") -> R.drawable.bg_grito
                resposta.startsWith("Relaxa") -> R.drawable.bg_grito_pergunta
                resposta.startsWith("A responsabilidade") -> R.drawable.bg_eu
                resposta.startsWith("Nao me incomode") -> R.drawable.bg_vazio
                resposta.startsWith("Essa eu sei") -> R.drawable.bg_matematica
                resposta.startsWith("E pra ja") -> R.drawable.agir
                else -> R.drawable.bg_outro
            }

            // Abre a tela de resposta passando o texto e a imagem
            val intent = Intent(this, RespostaActivity::class.java)
            intent.putExtra("resposta", resposta)
            intent.putExtra("imagemFundo", imagemFundo)
            startActivity(intent)

            // Limpa o campo para a proxima mensagem
            editMensagem.text.clear()
        }
    }
}