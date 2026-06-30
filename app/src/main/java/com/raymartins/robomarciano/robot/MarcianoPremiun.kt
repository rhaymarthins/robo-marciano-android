package com.raymartins.robomarciano.robot
// Classe premium que herda MarcianoMatematico e aceita uma acao personalizada

class MarcianoPremiun(private val acaoPersonalizada: AcaoPersonalizada) : MarcianoMatematico() {

    // Sobrescreve a versao com numeros para interceptar o comando "agir"
    override fun responda(frase: String, vararg numeros: Double): String {
        if (frase.contains("agir", ignoreCase = true)) {
            val resultadoAcao = acaoPersonalizada.executar()
            return "E pra ja! $resultadoAcao"
        }
        return super.responda(frase, *numeros)
    }

    // Sobrescreve a versao simples (sem numeros) da classe pai Marciano
    override fun responda(frase: String): String {
        return responda(frase, *doubleArrayOf())
    }
}