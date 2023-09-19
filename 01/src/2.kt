fun fib(n: Int): Int {
    return if (n < 2) 1 else fib(n-1) + fib(n-2)
}

fun fib1(n: Int): Int {
    fun fib(n: Int, a : Int = 0, b : Int = 1): Int {
        return if (n < 0) a else fib(n-1, b, a+b)
    }
    return fib(n)
}

fun main() {
//    fun main(args: Array<String>) {
    val lst = listOf(1,2,3,4,5,6,7,8,9,10)
    println(lst.map { n -> fib(n) })
    println(lst.map { fib1(it) })
    lst.forEach { println("fib($it) = ${fib1(it)}")}
    for(i in 1..11) println("fib($i) = ${fib1(i)}" )
    println("Maximum je ${lst.map { fib(it) }.max()}")
}
