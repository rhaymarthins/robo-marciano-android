package com.raymartins.robomarciano

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.raymartins.robomarciano.robot.AcaoPersonalizada
import com.raymartins.robomarciano.robot.MarcianoPremiun

class HistoricoActivity : AppCompatActivity() {

    private val curiosidades = listOf(
        "Polvos tem tres coracoes e sangue azul.",
        "A Lua se afasta da Terra 3,8 cm por ano.",
        "Mel nunca estraga se guardado corretamente.",
        "O ser humano e o unico animal que ruboriza."
    )
    private var indiceCuriosidade = 0

    private val acaoCuriosidade = object : AcaoPersonalizada {
        override fun executar(): String {
            val texto = curiosidades[indiceCuriosidade % curiosidades.size]
            indiceCuriosidade++
            return "Curiosidade do dia: $texto"
        }
    }

    private val marciano = MarcianoPremiun(acaoCuriosidade)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historico)

        val listHistorico = findViewById<ListView>(R.id.listHistorico)
        val btnVoltar = findViewById<Button>(R.id.btnVoltarHistorico)

        // Conecta a lista ao historico usando um adapter simples
        val adapter = object : ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            HistoricoComandos.lista
        ) {
            override fun getView(position: Int, convertView: android.view.View?, parent: android.view.ViewGroup): android.view.View {
                val view = super.getView(position, convertView, parent)
                val text = view.findViewById<android.widget.TextView>(android.R.id.text1)
                text.setTextColor(android.graphics.Color.WHITE)
                text.setPadding(32, 24, 32, 24)
                return view
            }
        }
        listHistorico.adapter = adapter

        // Ao clicar num item, reenvia a mensagem ao robo
        listHistorico.setOnItemClickListener { _, _, position, _ ->
            val comando = HistoricoComandos.lista[position]

            val partes = comando.trim().split(" ")
            val operacoes = listOf("some", "subtraia", "multiplique", "divida")

            val resposta = if (partes[0].lowercase() in operacoes && partes.size >= 3) {
                val numeros = partes.drop(1).mapNotNull { it.toDoubleOrNull() }.toDoubleArray()
                marciano.responda(partes[0], *numeros)
            } else {
                marciano.responda(comando)
            }

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

            val intent = Intent(this, RespostaActivity::class.java)
            intent.putExtra("resposta", resposta)
            intent.putExtra("imagemFundo", imagemFundo)
            startActivity(intent)
        }

        btnVoltar.setOnClickListener {
            finish()
        }
    }
}