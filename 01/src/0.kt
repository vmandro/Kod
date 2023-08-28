import java.util.stream.Stream

fun binCifSum(n : Int) : Int =
    if (n <= 0) 0
    else binCifSum(n/2) + if (n % 2 == 0) 0 else 1
    //else binCifSum(n/2) + (n % 2 == 0)

fun binCifSumClasic(n : Int) : Int {
    if (n <= 0) return 0
    else if (n % 2 == 0) return binCifSumClasic(n / 2)
    else return 1 + binCifSumClasic(n / 2)
}

fun <T>powerSet(elems : List<T>) : List<List<T>> {
    return if (elems.size == 0) listOf(listOf<T>())
           else {
             val aux = powerSet(elems.drop(1))
             aux.map { it.plus(elems.first()) } .plus (aux)
    }
}

fun <T>vars(elems : List<T>, k : Int) : List<List<T>> {
    return  if (k == 0) listOf(listOf<T>())
    else
        (0 until elems.size).flatMap {
                indx -> vars(elems.take(indx).plus(elems.drop(indx+1)) ,k-1).map { it.plus(elems.get(indx))}
        }
}

fun <T>varsWithReps(elems : List<T>, k : Int) : List<List<T>> {
    return if (k == 0) listOf(listOf<T>())
    else
        (0 until elems.size).flatMap {
                indx -> varsWithReps(elems,k-1).map { it.plus(elems.get(indx))}
        }
}


fun <T>comb(elems : List<T>, k : Int) : List<List<T>> {
    return  if (k == 0) listOf(listOf<T>())
    else if (elems.size == k) listOf(elems)
    else
        comb(elems.drop(1), k - 1).map { co -> co.plus(elems.first()) }
            .plus( comb(elems.drop(1), k) ).toList()
}

fun <T>combWithReps(elems : List<T>, k : Int) : List<List<T>> {
    return  if (k == 0) listOf(listOf<T>())
    else if (elems.size == k) listOf(elems)
    else
        combWithReps(elems, k - 1).map { co -> co.plus(elems.first()) }
            .plus( combWithReps(elems.drop(1), k) ).toList()
}

fun comb1(elems : List<String>, k : Int) : MutableList<MutableList<String>> {
    return  if (k == 0) mutableListOf(mutableListOf<String>())
    else if (elems.size == k) mutableListOf(elems.toMutableList())
    else   comb1(elems.drop(1), k - 1).map { it -> it.plus(elems.first()).toMutableList() }.toMutableList()
            .plus(comb1(elems.drop(1), k)).toMutableList()
}

fun comb2(elems : List<String>, k : Int) : Sequence<Sequence<String>> {
    return  if (k == 0) sequenceOf(sequenceOf<String>())
    else if (elems.size == k) sequenceOf(elems.asSequence())
    else
        comb2(elems.drop(1), k - 1).map { co -> co.plus(elems.first()) }
            .plus( comb2(elems.drop(1), k) )
}


//    fun perms(elems : List<String>) : List<List<String>> {
//    return
//        if (elems.size == 0) listOf(listOf<String>())
//        else
//
//            perms(elems.subList(1, elems.size)).map { it.}
//}
fun main(args:Array<String>) {
    for (n in 0..10)
        println("binCifSum $n je ${binCifSum(n)}")

    val vek = 12
    val kategoria =
        if (vek < 6) "predskolsky"
        else if (vek <= 11) "1.stupen"
        else if (vek <= 18) "2.stupen"
        else "mimo"

    val kategoria1 =
        when (vek) {
            in 0..5 -> "predskolsky"
            in 5..11 -> "1.stupen"
            in 12..18 -> "2.stupen"
            else -> "mimo"
        }
    var kategoria2 = "mimo"
    when (vek) {
        in 0..5 -> kategoria2 = "predskolsky"
        in 5..11 -> kategoria2 = "1.stupen"
        in 12..18 -> kategoria2 = "2.stupen"
    }

    for (x in 1..10) println(x)
    for (x in (1..10).toList()) println(x)
    for (x in (10 downTo 1).toList()) println(x)
    for (x in 10 downTo 1) println(x)
    for (x in 1 until 10) println(x)
    for (x in 1 until 10 step 2) println(x)
    for (x in listOf(2,3,5,7,11,13)) println(x)
    for (x in 'a'..'z') println(x)
    for ((index, value) in ('a'..'z').withIndex()) println("[$index]=$value")
    val map = mapOf(1 to "gula", 2 to "zelen", 3 to "zalud", 4 to "srdce")
    for ((key, value) in map) println("[$key]=$value")


    val a = "kot"
    val b = "lin"
    val c = (a+b).trim()
    val d = "kotlin"
    println("c==d ${c==d}, c===d ${c===d}")

    println(powerSet(listOf("a", "b", "c", "d")))
    println(combWithReps(listOf("a", "b", "c", "d"),2))

    println(vars(listOf("a", "b", "c", "d"),2))
    println(varsWithReps(listOf("a", "b", "c", "d"),2))

    println(comb(listOf("a", "b", "c", "d"),2))
    println(comb1(listOf("a", "b", "c", "d"),2))
    println(comb2(listOf("a", "b", "c", "d"),2).forEach { print(it.toList()) })
}