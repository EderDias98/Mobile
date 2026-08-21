package org.example
import kotlin.math.*
import kotlin.text.toFloat


fun Tri(){
    val num  = readln().toInt()
    var valor = 1
    for (i in 1 .. num){
        for(j in 1..i){
            print("$valor ")
            valor++
        }
        print("\n")
    }

}

fun ContemUpperCase(srt: String) : Boolean {
    for (s in srt){
        if (s.isUpperCase())
            return true

    }
    return false
}

fun ContemDigito(srt: String): Boolean{

    for (s in srt){
        if(s.isDigit()) return true
    }
    return false
}

fun ValidarSenha() {
    val senha = readln()
    if (senha.length < 8) print("SENHA INVALIDA")
    else if (!ContemUpperCase(senha)) print("SENHA INVALIDA")
    else if (!ContemDigito(senha)) print("SENHA INVALIDA")

    print("SENHA VALIDA")
}
fun Acertou(){
    var linha1 = readln()
    var linha2 = readln()

    var alvo = linha1.split(Regex("\\s+")).map {
        it.toFloat()
    }
    var tiro = linha2.split(Regex("\\s+")).map { it.toFloat() }

    var dist =  (( (alvo[0] - tiro[0])).pow(2f) + (alvo[1] - tiro[1]).pow(2f) ).pow(0.5f)
    var sumRaio = alvo[2] + tiro[2]

    if (dist <= sumRaio){
        println( "ACERTOU")

    }else {
        println("ERROU")
    }

}

fun ConverterMoeda() {

    val valor = readln().toFloat()
    val origem = readln()
    val destino = readln()
    var  valorFinal : Float

    if ( origem == "BRL" && destino == "EUR"){
        valorFinal = valor / 6.0f
    }else if(origem == "EUR" && destino == "BRL"){
        valorFinal = valor * 6.0f
    }else{
        val tableConverterUsa :  MutableMap<String, Float> = mutableMapOf("EUR" to 0.85f, "BRL" to 5.0f, "USD" to 1.0f)

        val temp = valor / tableConverterUsa.getValue(origem)
        valorFinal = temp * tableConverterUsa.getValue(destino)
    }


    print("%.2f %s".format( valorFinal, destino ))
}

fun criptografarMenssagem(){
    val valor = readln()
    val num = readln().toInt()
    val menssagem = readln()

    var mensFinal : String = ""
    if (valor == "D"){

        for(c in menssagem){
            if (c.isLetter()){
                if(c.isUpperCase()){

                    val h = (((c.code - 'A'.code) - (num % 26)) % 26 + 26) % 26

                    mensFinal += (h + 'A'.code).toChar()

                }else{
                    val h = (((c.code - 'A'.code) - (num % 26)) % 26 + 26) % 26

                    mensFinal +=  (h+ 'a'.code).toChar()

                }
            }else{
                mensFinal += c
            }
        }


    }else if(valor == "C"){
        for(c in menssagem){
            if (c.isLetter()){
                if(c.isUpperCase()){

                    val h = ((c.code - 'A'.code) + num) %  ('Z' - 'A' +1)

                    mensFinal += (h + 'A'.code).toChar()

                }else{
                    val h = ((c.code - 'a'.code) + num) %  ('z' - 'a' +1)

                    mensFinal +=  (h+ 'a'.code).toChar()

                }
            }else{
                mensFinal += c
            }
        }
    }

    println(mensFinal)


}

fun decodificar(mensagem: String, num: Int): String {
    return mensagem.map { c ->
        when {
            c.isUpperCase() -> c.deslocar(-num, 'A')
            c.isLowerCase() -> c.deslocar(-num, 'a')
            else -> c // Mantém espaços e pontuação
        }
    }.joinToString("")
}

// Função de extensão para limpar a matemática das letras
fun Char.deslocar(deslocamento: Int, base: Char): Char {
    val posicaoZero = this.code - base.code
    // Math.floorMod resolve o problema de números negativos e gigantes em uma linha só
    val novaPosicao = Math.floorMod(posicaoZero + deslocamento, 26)
    return (base.code + novaPosicao).toChar()
}

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    criptografarMenssagem()

}
