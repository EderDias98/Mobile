package org.example

fun lerProdutos(){
    val produtos = mutableListOf<Produto>()

    while(true){
        val nome = readlnOrNull() ?: break
        val estoque = readlnOrNull() ?: break
        val descricao = readlnOrNull()


        val produto = Produto(nome,estoque.toInt(),descricao)
        produtos.add(produto)



    }

    produtos.forEach {it.print()}

}



//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    // 1. Lê a primeira linha da entrada, converte para inteiro ou usa 0 caso seja nulo
    val k = readlnOrNull()?.toIntOrNull() ?: 0

    // 2. Lê a segunda linha da entrada que contém o texto a ser analisado
    val texto = readlnOrNull() ?: ""

    // 3. Verifica se o texto está vazio ou se k é menor/igual a zero para evitar erros
    if (texto.isBlank() || k <= 0) return

    // 4. Cria uma Expressão Regular (Regex) que encontra qualquer caractere que NÃO seja letra, número ou espaço
    val regexPontuacao = "[^a-zA-Z0-9áéíóúâêîôûàèìòùãõçÁÉÍÓÚÂÊÎÔÛÀÈÌÒÙÃÕÇ\\s-]".toRegex()

    // 5. Remove a pontuação substituindo por vazio e depois remove espaços extras nas pontas
    val textoLimpo = texto.replace(regexPontuacao, "").trim()

    // 6. Divide o texto em uma lista de palavras usando espaços em branco (um ou mais) como separador
    val palavras = textoLimpo.split("\\s+".toRegex())

    // 7. Agrupa as palavras pelo seu valor em minúsculo e conta quantas vezes cada uma aparece
    val frequencia = palavras
        .filter { it.isNotEmpty() } // Remove elementos vazios que possam ter sobrado
        .groupBy { it.lowercase() } // Agrupa transformando tudo em minúsculo (ignora case)
        .mapValues { it.value.size } // Substitui a lista de palavras agrupadas pelo tamanho dela (a contagem)

    // 8. Cria um mapa auxiliar para preservar a grafia (maiúsculas/minúsculas) da primeira vez que a palavra apareceu
    val grafiaOriginal = palavras
        .filter { it.isNotEmpty() }
        .associateBy { it.lowercase() } // A chave é em minúsculo, o valor é a palavra original

    // 9. Ordena o resultado: primeiro pelo número de repetições (decrescente) e pega as 'k' primeiras
    val topK = frequencia.entries
        .sortedByDescending { it.value } // Ordena do maior para o menor número de ocorrências
        .take(k) // Limita o resultado para a quantidade 'k' solicitada

    // 10. Percorre a lista final e imprime no formato exigido pelo exercício
    for (item in topK) {
        // Recupera a grafia original usando a chave minúscula
        val palavraFormatada = grafiaOriginal[item.key] ?: item.key
        println("$palavraFormatada: ${item.value}")
    }
}
