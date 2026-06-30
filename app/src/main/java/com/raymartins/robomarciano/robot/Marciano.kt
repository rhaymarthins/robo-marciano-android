package com.raymartins.robomarciano.robot

// Classe base do robo Marciano com respostas simples

open class Marciano {

    // Funcao principal que recebe o que o usuario disse e retorna a resposta do robo
    open fun responda(frase: String): String {

        // Verifica se a frase esta vazia ou so tem espacos em branco
        if (frase.isBlank()) {
            return "Nao me incomode"
        }

        // Verifica se a frase eh uma pergunta E tem alguma palavra toda em maiuscula
        if (ehPergunta(frase) && temGrito(frase)) {
            return "Relaxa, eu sei o que estou fazendo!"
        }

        // Verifica se a frase eh apenas uma pergunta (termina com ?)
        if (ehPergunta(frase)) {
            return "Certamente"
        }

        // Verifica se alguma palavra esta toda em maiuscula (grito)
        if (temGrito(frase)) {
            return "Opa! Calma ai!"
        }

        // Verifica se a frase contem a palavra "eu" (ignora maiusculas/minusculas)
        if (frase.contains("eu", ignoreCase = true)) {
            return "A responsabilidade eh sua"
        }

        // Resposta padrao para qualquer outra frase
        return "Tudo bem, como quiser"
    }

    // Verifica se a frase termina com ponto de interrogacao
    protected fun ehPergunta(frase: String): Boolean {
        return frase.trimEnd().endsWith("?")
    }

    // Verifica se alguma palavra da frase esta totalmente em maiuscula
    protected fun temGrito(frase: String): Boolean {
        return frase.split(" ").any { palavra ->
            palavra.isNotEmpty() && palavra.all { letra -> letra.isLetter() && letra.isUpperCase() }
        }
    }
}