import java.io.File

fun main() {
    val M = 10
    // M++ ::M je konstanta
    var m = 10
    m++
    println("m=$m, m^2=${m * m}")
    val list: List<String> = listOf("Peter", "Pavol", "Michal", "Agata")
    println("list.size= ${list.size}")
    // list.add("Xenia") - nejde, lebo to je imutable list
    val mlist = mutableListOf("Peter", "Pavol", "Michal", "Agata")
    mlist.add("Xenia")
    println("mlist.size= ${mlist.size}")
    // q: ??? ale ved mlist je definovane ako konstanta, ako je mozne ju zmenit
    // a: konstanta je pointer/referencia mlist, ale nie cela datova struktura, ergo preto toto nepojde
    // mlist = list.toMutableList()

    val set = setOf(2, 3, 5, 7, 11)
    val mset = setOf(2, 3, 5, 7, 11).toMutableSet()

    val map = mapOf(1 to "jedna", 2 to "dva", 3 to "tri")
    val mmap = mutableMapOf(1 to "one", 2 to "two", 3 to "three")

    //--------------------
    // shocking
    val list1 = list
    list1.plus("Xenia")
    println(list1)  //  a nic ???
    val list2 = list.plus("Xenia")
    println("list1=$list1")  //  plus je funkcia, ktora nemofikuje povodny zoznam
    println("list2=$list2")

    // polia
    val pole = arrayOf(1,2,3,4,5,6,7,8,9,10)
    val vektor = Array(10) {0}
    val vektor1 = Array(10) { it+1 }
    println(vektor1.toList())
    val matica = Array(10) {Array(10) {0} }

    //-- zacianame trochu chapat mutable collections ?

    println(list2.filter { it.matches("[A-P].*".toRegex()) })
    println(list2.map { it.toUpperCase() }.sorted())

    println(list2.drop(2))
    println(list2.take(2))

    println(list2.sortedBy { it.length })
    // val x: Map<Int, List<String>> = list2.groupBy { it.length }
    println(list2.groupBy { it.length })
    //val x: Map<Int, String> = list2.associateBy { it.length }
    println(list2.associateBy { it.length })
    println(list2.partition { it.length<5 })

    val intRange: IntRange = 1..8
    val seq = generateSequence(1) { it*10  }.take(4)
    for (N in seq) {
    //for (N in listOf(1, 10, 100, 1000, 10000, 100000)) {
        //val N = 100
        val triangle = (1..N).toList().flatMap { (1..it).toList() }
        println(triangle.size)
    }

    // sequence vs collection
    val collection = (-100..100)
        .filter {it % 2 == 0}
        .map { it * 2 }
        //.map { it/it }
        .take(10)
    println(collection)

    val sequence = (-100..100).asSequence()
        .filter {it % 2 == 0}
        .map { it * 2 }
        .map { it/it }
        .take(10)
    println(sequence.toList())
    println(slovaAB__(5))
    println(slova("abc",5))
    println(permutacie("abcd"))
    println(5.square())
    println(123456.cifSucet())
    println(listOf("a", "b", "c", "d", "e").middle())
    println(podmnoziny(listOf(1,2,3,4,5)).map {it.sorted()}.sortedBy { it.size })
    longestWord("englishDictionary.txt")
    mostlyUsedWord("shakespeare.txt")
}

// praca s textom
fun longestWord(fileName : String) {
    val lines = File(fileName).readLines().map { it.trim() }  // slovo na riadku
    val maxLen = lines.map { it.length }.max()
    println(lines.filter { it.length == maxLen})
}

// praca s textom
fun mostlyUsedWord(fileName : String) {
    val words = File(fileName).readLines()
        .flatMap { it.split("\\W".toRegex())
                     .filter { it.isNotEmpty()}
                     .map {it.toUpperCase()} }
    val freq = mutableMapOf<String, Int>()
    for(w in words) {
        freq.compute(w, { _, c -> 1+(c?:0)})
    }
    val max = freq.values.max()
    println(max)
    println(freq.filter { it.value == max })
}

// extension functions
// int na druhu
fun Int.square() = this*this

// ciferny sucet prirodzeneho cisla
fun Int.cifSucet() = toString().toCharArray().map { it.toInt() - 48}.sum()

// pocet pismen v slove
fun String.pocetPismen() = filter { it.isLetter()}

// pocet velkych pismen v slove
fun String.pocetVelkychPismen() = filter { it in 'A'..'Z'}

// stredny prvok zoznamu
fun <E> List<E>.middle():E = this[this.size/2]

// stred pola
fun <E> Array<E>.middle():E = this[this.size/2]

//-----------------
// slova dlzky n nad abecedou {a,b}
// old school style...
fun slovaAB(n:Int) : List<String> {
    if (n == 0)
        return listOf("")
    else {
        val kratsie = slovaAB(n-1)
        val dlhsie = mutableListOf<String>()
        for (w in kratsie) {
            dlhsie.add(w + "a")
            dlhsie.add(w + "b")
        }
        return dlhsie
    }
}

fun slovaAB_(n:Int) : List<String> {
    if (n == 0)
        return listOf("")
    else {
        val kratsie = slovaAB_(n-1)
        return kratsie.flatMap { listOf(it+"a", it+"b") }
    }
}

fun slovaAB__(n:Int) : List<String> =
    if (n == 0)
        listOf("")
    else
        slovaAB__(n-1).flatMap { w -> listOf(w+"a", w+"b") }

//-----------------
// slova dlzky n nad abecedou abeceda

//fun slova(abeceda : List<Char>, n : Int) : List<String> =
fun slova(abeceda : String, n : Int) : List<String> =
    if (n == 0)
        listOf("")
    else
        slova(abeceda, n-1).flatMap { w -> abeceda.map { ch -> w + ch} }

//------------------------------- elegantne, ale takto to nekodujte, ked pojdete na interview...
fun permutacie(abeceda : String) : List<String> =
    slova(abeceda, abeceda.length).filter { it.toSet().size == abeceda.length}

//---------
fun <T> podmnoziny(mnozina : List<T>) : List<List<T>> =
    if (mnozina.isEmpty())
        listOf(emptyList())
    else {
        val mensie = podmnoziny(mnozina.drop(1))  // 2^(n-1)
        mensie.map { it.plus(mnozina.first()) }.plus(mensie)
    }