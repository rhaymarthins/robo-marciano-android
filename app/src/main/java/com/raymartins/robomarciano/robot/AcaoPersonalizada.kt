package com.raymartins.robomarciano.robot

// Interface que define o contrato para qualquer acao personalizada do robo

interface AcaoPersonalizada {
    fun executar(): String
}