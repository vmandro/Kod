object FibbonacciJAVA {
    var table: Array<Int?> = arrayOfNulls(100)

    private fun fib(n: Int): Int {
        var result = table[n]
        if (result == null) {
            result = if (n < 2) 1
            else fib(n - 2) + fib(n - 1)
            table[n] = result
        }
        return result
    }

    @JvmStatic
    fun main(args: Array<String>) {
        for (i in 0..19) println("fib(" + i + ")=" + fib(i))
    }
}
