fun main() {
    var s : String? = null
    if (s != null)  // javista
        println(s.length)
    println(s?.length)
    println(if (s != null) s.length else null)
    println(s?:"")
    var sa: Array<String?> = arrayOf("peter", "pavel", null, "eva")
    for (a in sa) {
        println(a?.length)
//        val x: String = a!!
//        println(a!!.length)
    }
//    f1("peter")
//    f1(null)
    f2(null)
    f2(17)
    f2(true)
}

fun f1(str : String?) {
    println(str)
    println(str?.toUpperCase())
    println((str?:"nil").toUpperCase())
    println((str?.toUpperCase()?:"."))
    //println(str!!.toUpperCase())
}

fun f2(x : Any?) {
    if (x is String?) {
        println("dostal som retazec dlzky ${x?.length}")
    } else {
        val y = x as Int
        println("dostal som int $y")
    }

}
