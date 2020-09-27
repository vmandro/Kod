object fib {
    internal var table = arrayOfNulls<Int>(100)
    private fun fib(n: Int): Int {
        var result: Int? = table[n]
        if (result == null) {
            if (n < 2)
                result = 1
            else
                result = fib(n - 2) + fib(n - 1)
            table[n] = result
        }
        return result
    }

    @JvmStatic fun main(args: Array<String>) {
        for (i in 0..19)
            println("fib(" + i + ")=" + fib(i))
    }
}
