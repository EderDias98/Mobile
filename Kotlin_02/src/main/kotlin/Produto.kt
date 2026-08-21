package org.example

class Produto(
    val nome: String,
    var estoque: Int?,
    descricao: String? = null // Valor padrão se ninguém passar nada
) {
    // A lógica de validação roda direto na criação da propriedade
    val descricao: String? = if (descricao.isNullOrEmpty()) "Sem descrição" else descricao

    fun print(){
        println("Produto: ${this.nome}")
        println("Quantidade em estoque: ${this.estoque}")
        println("Descrição: ${this.descricao}")
        println()
    }


}