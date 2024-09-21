fun main() {
    val N = 10
    val M = 5000
    val x = (1..6).map { "$it".repeat(it) }.joinToString(separator = "")
    val m = emptyMap<Int,Int>().toMutableMap()
    repeat (M) {
        //val s = (1..N).map { (1..6).random() }.sum()
        val s = (1..N).map { x[(0 until x.length).random()].digitToInt() }.sum()
        m.putIfAbsent(s, 0)
        m.computeIfPresent(s, {_,v ->  v+1})
    }
    (N..6*N).forEach {
        println("$it\t${"*".repeat(m[it]?:0)}")
    }
    println(x)
}