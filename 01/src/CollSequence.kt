fun main() {
    val sequence = (-100..100)
        .asSequence()
        .filter { it % 2 == 0 }
        .map { it * 2 }
        .map { it / it }
        .take(10)
    println(sequence.toList())

    val collection = (-100..100)
        .filter { it % 2 == 0 }
        .map { it * 2 }
        .map { it / it }
        .take(10)
    println(collection)
}