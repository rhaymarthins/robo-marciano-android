package com.raymartins.robomarciano

// Objeto singleton: existe uma unica instancia durante toda a execucao do app
object HistoricoComandos {
    val lista = mutableListOf<String>()

    fun adicionar(comando: String) {
        // Evita duplicatas consecutivas
        if (lista.isEmpty() || lista.last() != comando) {
            lista.add(0, comando) // adiciona sempre no topo
        }
    }
}