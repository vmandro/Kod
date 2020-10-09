fun main(args:Array<String>) {
    var stringOrnull : String? = null
    var sa = arrayOf("Peter", null, "")
    if (args.size > 0) {
        stringOrnull = "Peter"
    }
    //println(stringOrnull.length)
    println(stringOrnull?.length)   // if ( == null) return null
    for(s in sa)
        print(s?.length)
    println(stringOrnull?:"empty")
    for(s in sa)
        print((s?:"").length)
// very bad practice
//    for(s in sa)
//        print(s!!.length)
    goo(5)
    goo("Peter")
    goo(null)
    goo(true)
}

fun foo(str : String?) {
    println(str)
    if (str != null) println(str.toUpperCase())
    println(str?.toUpperCase())  // safe call operátor
    // x?.m == if (x != null) x.m else null
}

fun stringLen(s: String?): Int = s?.length?:0 // Elvis operátor
// if (if (s == null) then null else s.length) == null then 0 else s.length

fun nonEmptystringLen(s: String?): Int {
    val sNotNull: String = s!!  	// určite nebude null,
    // ak bude tak exception kotlin.KotlinNullPointerException
    return sNotNull.length
}

fun goo(x : Any?) {
    print("string ${x as? String}")
    print(", Int ${x as? Int}")
    println(", Boolean ${x as? Boolean}")

    if (x is String) println("string $x")
    if (x is Int?) println("Int? $x")
    if (x !is Int) println("not Int $x")
    if (x is Boolean) println("Boolean ${x&&x}")
}