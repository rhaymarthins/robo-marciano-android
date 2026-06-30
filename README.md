# Robo Marciano Android

Aplicativo Android desenvolvido em Kotlin para a disciplina de Programação Mobile.
Interface gráfica para o Robô Marciano, um robô com respostas limitadas e personalizadas.

---

## Telas do aplicativo

### Tela principal

Campo de texto para digitar a mensagem e botão para enviar ao robô.
Também possui botão de acesso ao histórico de comandos.

### Tela de resposta

Exibe a resposta do robô com uma imagem de fundo temática para cada tipo de resposta.

### Tela de histórico

Lista com todos os comandos enviados ao robô. Ao clicar em qualquer item, a mensagem é reenviada e o robô responde novamente.

---

## Funcionalidades

- Respostas do robô baseadas no tipo de mensagem enviada
- Imagem de fundo diferente para cada tipo de resposta
- Operações matemáticas via texto
- Ação personalizada: curiosidades científicas ao digitar "agir"
- Histórico de comandos com reenvio por clique

---

## Comandos disponíveis

| O que digitar          | Resposta do Marciano                |
| ---------------------- | ----------------------------------- |
| Qualquer pergunta?     | Certamente                          |
| PALAVRA EM MAIUSCULO   | Opa! Calma ai!                      |
| GRITANDO COM PERGUNTA? | Relaxa, eu sei o que estou fazendo! |
| frase com eu           | A responsabilidade eh sua           |
| agir                   | E pra ja! + curiosidade do dia      |
| some 10 5              | Essa eu sei: 15                     |
| subtraia 20 8          | Essa eu sei: 12                     |
| multiplique 4 5        | Essa eu sei: 20                     |
| divida 100 4           | Essa eu sei: 25                     |
| (enviar sem digitar)   | Nao me incomode                     |
| qualquer outra coisa   | Tudo bem, como quiser               |

---

## Estrutura do projeto

```
app/src/main/
  java/com/raymartins/robomarciano/
    robot/
      Marciano.kt              # Classe base com respostas simples
      MarcianoMatematico.kt    # Herda Marciano, adiciona operacoes matematicas
      AcaoPersonalizada.kt     # Interface para acoes personalizadas
      MarcianoPremiun.kt       # Herda tudo e usa a interface
    MainActivity.kt            # Tela principal de entrada de mensagem
    RespostaActivity.kt        # Tela de resposta do robo
    HistoricoActivity.kt       # Tela de historico de comandos
    HistoricoComandos.kt       # Singleton que armazena os comandos enviados
  res/
    layout/
      activity_main.xml        # Layout da tela principal
      activity_resposta.xml    # Layout da tela de resposta
      activity_historico.xml   # Layout da tela de historico
    drawable/
      bg_pergunta.webp         # Fundo para respostas de pergunta
      bg_grito.webp            # Fundo para respostas de grito
      bg_grito_pergunta.webp   # Fundo para grito com pergunta
      bg_eu.webp               # Fundo para respostas com eu
      bg_vazio.webp            # Fundo para mensagem vazia
      bg_outro.webp            # Fundo padrao
      bg_matematica.webp       # Fundo para operacoes matematicas
      agir.webp                # Fundo para o comando agir
```

---

## Conceitos utilizados

- **Heranca**: tres versoes do robo, cada uma estendendo a anterior
- **Interface**: define o contrato para a acao personalizada
- **Classe anonima**: implementa a interface diretamente no codigo
- **Singleton**: objeto unico para guardar o historico entre as telas
- **Intent**: navegacao entre telas passando dados
- **ListView com adapter customizado**: lista clicavel com estilo personalizado
- **FrameLayout**: sobreposicao de imagem de fundo com conteudo

---

## Requisitos

- Android Studio
- Kotlin
- Minimo: Android 7.0 (API 24)
