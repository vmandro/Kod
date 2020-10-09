fun main(args:Array<String>) {
    var stringOrnull : String? = null
    var sa: Array<String?> = arrayOf("Peter", null, "Pavol", null)
    //var sa1: Array<String> = arrayOf("Peter", "Pavol")
    if (args.size > 0) {
        stringOrnull = "Jano"
    }
    println(stringOrnull?.length)
    for (s in sa)
        println(s?.length)
    println(stringOrnull?:"empty")
    for (s in sa)
        println(s?:"epsilon")
    // very bad style
    for (s in sa)
        println((s?:"").length)

    //gooo(4)
   // gooo("xxx")
    gooo(null)
}
fun gooo(x : Any?) {
    println("String ${x as? String}")
    println("Int ${x as? Int}")
    println("Boolean ${x as? Boolean}")
    if (x is String)
        println("string $x")
    if (x is Int?)
        println("int $x")
    if (x !is Int)
        println("not int $x")
    if (x is Boolean) {
        println("not int ${x && x}")
    }
}