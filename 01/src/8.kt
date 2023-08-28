
fun main(args: Array<String>) {
    val set = hashSetOf(2, 3, 5, 7, 11, 13, 17)
    val list = arrayListOf(-1, 0, 1)
    val map = hashMapOf("sedma" to 7, "osma" to 8, "dolnik" to 11, "hornik" to 12, "kral" to 13, "eso" to 15)
    println(set)
    println(set.javaClass)
    println(list)
    println(list.javaClass)
    println(map)
    println(map.javaClass)
    for(x in list)
        for(y in set)
            for((key, value) in map)
                println("$x $y $key $value")
}
