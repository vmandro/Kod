import java.math.BigInteger

val table = mutableMapOf<Int, BigInteger>()   // HashMap

fun fibo(n: Int): BigInteger = table.getOrPut(n) {
    if (n <= 2)
        BigInteger.ONE
    else
        fibo(n - 1) + fibo(n - 2)
}

fun main() {
    println(fibo(1024))
}