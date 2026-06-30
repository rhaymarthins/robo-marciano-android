package com.raymartins.robomarciano.robot

// Classe que estende o Marciano basico e adiciona operacoes matematicas

open class MarcianoMatematico : Marciano() {

    // Versao com numeros para operacoes matematicas
    open fun responda(frase: String, vararg numeros: Double): String {

        return when (frase.lowercase().trim()) {

            "some" -> {
                val resultado = numeros.sum()
                "Essa eu sei: ${formatarResultado(resultado)}"
            }

            "subtraia" -> {
                val resultado = numeros.reduce { acumulador, numero -> acumulador - numero }
                "Essa eu sei: ${formatarResultado(resultado)}"
            }

            "multiplique" -> {
                val resultado = numeros.reduce { acumulador, numero -> acumulador * numero }
                "Essa eu sei: ${formatarResultado(resultado)}"
            }

            "divida" -> {
                if (numeros.size >= 2 && numeros[1] == 0.0) {
                    "Nao posso dividir por zero!"
                } else {
                    val resultado = numeros.reduce { acumulador, numero -> acumulador / numero }
                    "Essa eu sei: ${formatarResultado(resultado)}"
                }
            }

            else -> super.responda(frase)
        }
    }

    // Formata o resultado: sem casas decimais se for inteiro
    private fun formatarResultado(valor: Double): String {
        return if (valor == valor.toLong().toDouble()) {
            valor.toLong().toString()
        } else {
            String.format("%.2f", valor)
        }
    }
}