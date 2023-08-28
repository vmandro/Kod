fun main(args: Array<String>) {
    val fcia = { x:Int, y : Int -> println("sucet $x+$y"); x+y}
    val proc = { x:Int, y : Int -> println("sucet $x+$y")}

    println(fcia(12,7))
    proc(13,9)
    println({ x:Int -> x+1 }(2))
    ; // inak neopochopi ze nejde o block, ale lambda konstantu
    { x:Int -> println(x)}(4)
    // preto jasnejsi zapis
    run {{ x:Int -> println(x)}(4)}
    val delta = 5
    println(
            listOf(1,2,3)
                 .map { it + delta}   // x -> x + delta
                 .filter {it % 2 == 0}
    )
    val numbers = mapOf(0 to "zero", 1 to "one")
    for((father, persons) in family.groupBy { it.father })
        println("${persons.size} ma otca $father")

    println(listOf("a", "aba", "b", "ba", "abba").groupBy { it.length })
    println(listOf("a", "aba", "b", "ba", "abba").flatMap { it.toList() })

    val books = listOf(
            Book("Thursday Next", listOf("Jasper Fforde")),
            Book("Mort", listOf("Terry Pratchett")),
            Book("Good Omens", listOf("Terry Pratchett", "Neil Gaiman")))
    println(books.flatMap { it.authors }.toSet())

    listOf(1, 2, 3, 4)
            .asSequence()
            //.stream()
                .map { print("map($it) "); it * it }
                .filter { print("filter($it) "); it % 2 == 0 }
            .toList()

    val nats = generateSequence(1) { it + 1 }
    println(nats.takeWhile { it <= 100 }.sum())
    println(nats.takeWhile { it <= 10 }.reduce({ x:Int, y : Int -> x*y}))



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

}
class Book(val title: String, val authors: List<String>)

