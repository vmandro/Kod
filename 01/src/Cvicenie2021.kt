package cviko // len preto, aby sa nebili funkcie s inym modulom

fun main() {
    val M = 10
    //M++
    var m = 10
    m++
    println("m = $m, ${m*m}")
    val list: List<String> = listOf("Peter", "Pavel", "Eva", "Filip", "Matejko", "Xenia")
    println(list.size)
    //list.add("Zofia")
    //val mlist = mutableListOf("Peter", "Pavel", "Filip", "Matej", "Xenia")
    val mlist: MutableList<String> = list.toMutableList()
    //mlist = mutableListOf()
    mlist.add("Zofia")
    println(mlist)
    val set = setOf(2,3,5,7,11,13)
    val map = mutableMapOf(1 to "jedna", 2 to "dva", 3 to "tri")
    for (x in mlist) {}
    for (x in set) {}
    for ((key, value) in map) {}
    //--------------
    val list1 = list.plus("Zofia")
    println("list = $list")
    println("list1 = $list1")

    val pole: Array<Int> = arrayOf(1,2,3,4,5,6)
    val vektor = Array(10) {1}
    val vektor1 = Array(10) { x->x+1 }
    val vektor2 = Array(10) { it*2 }
    println(vektor2.toList())
    val matica = Array(10) { Array(10) {0} }
    //-----------------
    println(list1.map { it.toUpperCase()})
    println(list1.map { s:String -> s.toUpperCase()})
    println(list1.filter { it.startsWith("P")})
    println(list1.filter { it.matches("[A-P].*".toRegex())})
    println(list1.take(3))
    println(list1.drop(2))
    println(list1.sorted())
    println(list1.sortedBy { it.length })
    val x: Map<Int, List<String>> = list1.groupBy { it.length }
    println(x)
    val y: Map<Int, String> = list1.associateBy { it.length }
    println(y)
    println(list1.partition { it.length <= 5 })

    val ir: IntRange = 1..8
    for (i in ir) {}
    for (i in 1..10) {}
    for (i in ir.toSet()) {
        print("$i, ")
    }
    println("-----------")
    val seq = generateSequence(1) {it * 10}.take(3)
    //for (n in listOf(1,10,100,1000,10000)) {
    for (n in seq) {
        val triangle: List<Int> = (1..n).toList().flatMap { x -> (1..x).toList() }
        println(triangle.size)
    }
    //
    val collection = (-100..100)
        .filter { it % 2 == 0 }
        .map { it *2 }
        //.map { it/it } odkomentuj si a pochopis
        .take(10)
    println(collection)

    val sequence = (-100..100)
        .asSequence()
        .filter { it % 2 == 0 }
        .map { it *2 }
        .map { it/it }
        .take(10)
    println(sequence.toList())
    println(5.square())
    println(123456.cifSucet())
    println(listOf(4,5,1,9,2,3,5,6).midlle())
    println(slovaAB(4))
    println(slovaAB(5).size)
    println(slovaAB__(4))
    println(slovaAB__(5).size)

    println(slova("abc",4).size)
    println(permutacie("abcd").size)
}

fun slovaAB(n : Int) : List<String> {
    if (n == 0)
        return listOf("")
    else {  // old school
        val result = mutableListOf<String>()
        val kratsie = slovaAB(n-1)  // 2^(n-1)
        for (w in kratsie) {
            result.add(w + "a")
            result.add(w + "b")
        }
        return result
    }
}

fun slovaAB_(n : Int) : List<String> {
    if (n == 0)
        return listOf("")
    else {
        val kratsie = slovaAB_(n-1)  // 2^(n-1)
        return kratsie.flatMap { w -> listOf(w+"a", w+"b") }
    }
}


fun slovaAB__(n : Int) : List<String> =
    if (n == 0)
        listOf("")
    else
        slovaAB__(n-1).flatMap { w -> listOf(w+"a", w+"b") }


fun slova(abeceda : String, n : Int) : List<String> =
    if (n == 0)
        listOf("")
    else
        slova(abeceda, n-1).flatMap { w -> abeceda.map{ ch -> w+ ch} }

fun permutacie(abeceda: String) : List<String> =
    slova(abeceda, abeceda.length).filter { it.toSet().size == abeceda.length }

// extension function
fun Int.square() = this * this
fun Int.cifSucet() = toString().map { it.toInt() - 48 }.sum()

fun String.pocetVelkychPismen() = filter { it in 'A'..'Z' }

fun <E> List<E>.midlle() = this[size/2]
fun <E> Array<E>.midlle() = this[size/2]

//---------------------------------