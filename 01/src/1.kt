// person of Adam's Family
data class APerson(val first  : String,
                   val name   : String,
                   val age    : Int? = null,
                   val father : APerson?,
                   val mother : APerson?
                   )

val father = APerson("Gomez", "Addams", 156, null, null)
val mother = APerson("Morticia", "Addams", 136, null, null)
val daugther  = APerson("Wednesday", "Addams", 46, father, mother)
val son  = APerson("Pugsley", "Addams", 36, father, mother)
val family = listOf(
        father,
        mother,
        daugther,
        son,
        APerson("Fester", "Addams", 174, null, null),
        APerson("Pubert", "Addams", null, null, null)
        ) // uncle

fun main() {
    val oldest = family.maxBy { it.age ?: 0 }
    println("The oldest is: $oldest")
    println(family.map { it.first })
    println(family.filter { (it.age ?: 0) > 100 } )
    println(family.all { (it.age ?: 0) < 100 } )
    println(family.all { it.name == "Dracula" } )
    println(family.groupBy { it.father } )
    println(family.filter { it.age == (family.maxBy { person: APerson -> person.age ?: 0 } ?: 0) } )
    println(family.filter { it.age == (family.maxBy { it.age ?: 0 } ?: 0) } )
}
