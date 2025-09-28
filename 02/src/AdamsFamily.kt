data class Person(val first  : String,
                  val name   : String,
                  val age    : Int? = null,
                  val father : Person?,
                  val mother : Person?)

val father = Person("Gomez", "Addams", 156, null, null)
val mother = Person("Morticia", "Addams", 136, null, null)
val daugther  = Person("Wednesday", "Addams", 46, father, mother)
val son  = Person("Pugsley", "Addams", 36, father, mother)

val family = listOf( father, mother, daugther, son,
        Person("Fester", "Addams", 174, null, null),
        Person("Pubert", "Addams", null, null, null)
        ) // uncle

fun Person.isSibling(x : Person): Boolean =
    this.father == x.father || this.mother == x.mother

fun Person.children() : List<Person> =
    family.filter { it.father == this || it.mother == this }

fun Person.parents() : List<Person> =
    listOf(father, mother).filterNotNull()

fun Person.grandPapas(): List<Person?> =
    listOf(father?.father, mother?.father)

fun Person.grandPapas1(): List<Person> =
    listOf(
        if (father != null) father.father else null,
        if (mother != null) mother.father else null
    ).filterNotNull()


fun main(args: Array<String>) {
    val oldest = family.maxBy { it.age ?: 0 }
    println("The oldest is: $oldest")

    println("Number of teenagers: ${family.count{it.age ?:0 < 100}} ")
    println("Teenagers: ${family.filter{it.age ?:0 < 100}} ")

    println(family.map { it.first })  // mapToObj
//    println(family.filter { it.age?:0 > 100 } )
//    println(family.all { it.age?:0 < 100 } )
//    println(family.any { it.name == "Dracula" } )
    println(family.groupBy { it.father }.keys )
    println(family.groupBy { it.father }[null] )
    println(family.groupBy { it.father }[father] )
//    println(family.filter { it.age == family.maxBy { person: Person -> person.age?:0 }?:0 } )

}
