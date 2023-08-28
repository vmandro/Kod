fun foo(str : String?) {
    println(str)
    if (str != null)
        println(str.toUpperCase())
    println(str?.toUpperCase())     // safe call operator x?.m == if (x != null) x.m else null
}
fun stringLen(s: String?): Int = s?.length ?: 0     // Elvis operator
        // if (if (s == null) then null else s.length) == null then 0 else s.length

fun nonEmptystringLen(s: String?): Int {
    val sNotNull: String = s!!      // urcite nebude null, ak bude tak exception kotlin.KotlinNullPointerException
    return sNotNull.length
}


fun main(args: Array<String>) {
    foo("Kotlin")
    foo(null)
    println(son.father?.name)   // father je Person?
    println(mother.father?.name)   // mother.father == null
    println(stringLen("Kotlin"))
    println(stringLen(null))
    println(nonEmptystringLen("Kotlin"))    // xception kotlin.KotlinNullPointerException
    //println(nenEmptystringLen(null))
    val p1 = Student("Pugsley", "Addams")
    val p2 = Student("Pugsley", "Addams")
    println(p1 == p2)
    println(p1.equals("Pugsley"))

    println(listOf<String>("a", "b").get(0))
    println(setOf<String>("a", "b", "a").contains("a"))
    println(mapOf("a" to 1, "b" to 100).keys)

    println(arrayListOf<String>("a", "b").set(1, "Kotlin"))
    println(hashSetOf<String>("a", "b", "a").remove("a"))
    println(hashMapOf("a" to 1, "b" to 100).set("b", 10))
}
class Student(val first: String, val name: String) {
    override fun equals(o: Any?): Boolean {
        val oo = o as? APerson ?: return false
        return oo.first == first && oo.name == name
    }

}