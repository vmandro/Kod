package Cvicenie

enum class Sex {Male, Female}
data class Person(
        val name:String,
        val id: Int,      // rodne cislo, unique key
        val sex: Sex,     // aby sme vedeli, ci je to on/ona
        val parents: List<Person> = listOf() // zoznam rodicov
) : Comparable<Person> {

    override fun compareTo(other : Person): Int {       // porovnavanie Person podla ID
        return this.id - other.id
    }
    // this is parent of y
    fun isParent(y : Person): Boolean {
        return people.any { it.id == y.id && it.parents.contains(this) }
    }
    // this is father of y
    fun isFather(y : Person): Boolean {
        return sex == Sex.Male && isParent(y)
    }
    // this is mother of y
    fun isMother(y : Person): Boolean {
        return sex == Sex.Female && isParent(y)
    }
    // this is parent of y, ina definicia
    fun isParent_(y : Person): Boolean {
        return isFather(y) || isMother(y)
    }
    // sirota
    fun isOrphan(): Boolean {
        return parents.size == 0
    }
    // have a child with this and it's a man
    fun husband() : List<Person> {
        return people.filter { it.parents.contains(this) }.flatMap { it.parents }.filter { it.sex == Sex.Male }
    }
    // have a child with this and woman
    fun wife() : List<Person> {
        return people.filter { it.parents.contains(this) }.flatMap { it.parents }.filter { it.sex == Sex.Female }
    }
    // this is a sib of y
    fun isSib(y : Person): Boolean {
            // parents().toSet() == y.parents().toSet()  // this a y su surodenci, ak maju rovnakych spolocnych rodicov
        return parents.toSet().intersect(y.parents.toSet()).isNotEmpty()  // this a y su surodenci,
                                        // maju aspon jedneho spolocneho rodica
    }
    // this is a brother of y
    fun isBrother(y : Person): Boolean {
        return sex == Sex.Male && isSib(y)
    }
    // this is a sister of y
    fun isSister(x : Person, y : Person): Boolean {
        return sex == Sex.Female && isSib(y)
    }
    // all they have a baby with this
    fun partners() : List<Person> {
        return people.filter { it.parents.contains(this)} .flatMap { it.parents }.filter { it != this }
    }
    fun father(): Person? {
        return parents.filter { it.sex == Sex.Male }.first()
    }
    fun mother(): Person? {
        return parents.filter { it.sex == Sex.Female }.first()
    }
    // all svokra of this
    fun motherInLaw() : List<Person> {
        TODO()
    }
    // all svokor of this
    fun fatherInLaw() : List<Person> {
        TODO()
    }
    // rodicia this su registrovani partneri
    fun isRegistered(): Boolean {
        TODO()
    }
    // father of father of this
    fun grandPaPa() : Person? {
        return father()?.father() //.... este tu chyba z maminej strany
    }
    // all grandparents of this
    fun grandParent() : List<Person> {
        return listOf(father()?.father(), father()?.mother(), mother()?.father(), mother()?.mother())
                .filter { id != null } as List<Person>
    }
    // all predecessors of this
    fun predecessors() : Set<Person> {
        return parents.plus(parents.flatMap { it.predecessors() }).toSet()
    }
    // predecessors and the generation level
    // all parents are level 1, grandparents level 2, etc...
    fun predecessors1(n : Int = 1) : List<Pair<Person, Int>> {
        TODO()
    }
    // if the result is n, it means that x is predecessor of this in the nth generation level
    // the result null means, x is not a predecessor of this
    fun predecessorLevel(x : Person) : Int? {
      TODO()
    }
    // x and y have the same predecessor with respect the level
    fun sameGen(x : Person, y:Person):Boolean {
      TODO()
    }
    // all childern of this
    fun children() : List<Person> {
        return people.filter { it.parents.contains(this) }
    }
    // all grandchildren of this
    fun grandChildren() : List<Person> {
        return children().flatMap { it.children()  }
    }
}
//------------------------------------ data
// origin
val adam = Person("Adam", 0, Sex.Male)
val eva  = Person("Eva", 1, Sex.Female)
// 1st gen
val jozo = Person("Jozo", 2, Sex.Male, listOf(adam, eva))
val sona = Person("Sona", 3, Sex.Female, listOf(adam, eva))
val pavel = Person("Pavel", 7, Sex.Male, listOf(adam, eva))
val paella = Person("Paella", 8, Sex.Female, listOf(adam, eva))
// 2nd gen
val fero = Person("Fero", 4, Sex.Male, listOf(jozo, sona))
val simona = Person("Simona", 5, Sex.Female, listOf(sona))
// 3rd gen
val filomena = Person("Filomena", 6, Sex.Female, listOf(jozo,simona))  // incest: jozo so svojou dcerou simonou

val people: List<Person> = listOf( adam, eva, jozo, fero, simona, filomena )

//-----------------------------------------------------------------------------------

// k lubovolnemu typu mozeme definovat extension metody, bez toho aby sme museli vyrobit podtriedu, ktorej dame
// nove metody, novy funkcionalitu. Tzv. extension metody su sugar, skompiluju sa ako static println(self:List<Person>)
fun List<Person>.tlac(prompt : String? = null) {  // zoznam ludi budeme tlacit ako zoznam mien
    println(prompt + ":" +this.map { it.name} )
}
// id je unikatny kluc
fun isUnique() : Boolean {
    return people.map { it.id }.toSet().count() == people.count()
}
// id je unikatny kluc definovana property
val isUniqueProperty: Boolean
    get() = people.map { it.id }.toSet().count() == people.count()

// vytvor index: zobrazenie ID -> Person
fun createIndex() : Map<Int, List<Person>> {
    return people.groupBy { it.id }
}
// pomocou createIndex redefinuj este raz isUnique
fun isUnique_() : Boolean {
    return createIndex().values.toList().all { x:List<Person> -> x.size == 1 }
}
// they have a baby
fun areParents(x : Person, y : Person): Boolean {
    return people.any { it.parents.contains(x) && it.parents.contains(y) }
}

fun main(args : Array<String>) {
    println(setOf(1,2) == setOf(2,1))
    people.tlac("vsetci")
    println("isUnique as property = ${isUniqueProperty}")
    println("isUnique = ${isUnique()}")
    println("isUnique_ = ${isUnique_()}")
    println("isParent = ${adam.isParent(jozo)}")
    println("isParent = ${eva.isParent(jozo)}")
    println("isParent_ = ${adam.isParent_(jozo)}")
    println("isParent_ = ${eva.isParent_(jozo)}")

    jozo.predecessors().toList().tlac("jozo's predecessors")
    fero.predecessors().toList().tlac("fero's predecessors")

    println("fero's predecessors = ${fero.predecessors1(1).map{ x -> Pair(x.first.name, x.second) }}")
    println("filomena's predecessors = ${filomena.predecessors1(1).map{ x -> Pair(x.first.name, x.second) }}")

    println("fero's predecessors = ${fero.predecessorLevel( fero)}")
    println("fero's predecessors = ${fero.predecessorLevel( jozo)}")
    println("fero's predecessors = ${fero.predecessorLevel( sona)}")
    println("fero's predecessors = ${fero.predecessorLevel( adam)}")
    println("fero's predecessors = ${fero.predecessorLevel( eva)}")
    println("filomena's predecessors = ${filomena.predecessorLevel( eva)}")
    //println("jozo's predecessors = ${jozo.predecessorLevel( fero)}")

    println("fero's partners = ${fero.partners().map{ it.name }}")
    println("sona's partners = ${sona.partners().map{ it.name }}")
    println("adam's partners = ${adam.partners().map{ it.name }}")
    println("jozo's partners = ${jozo.partners().map{ it.name }}")

    println("jozo's children = ${jozo.children().map{ it.name }}")
    println("sona's children = ${sona.children().map{ it.name }}")

    adam.grandChildren().tlac("adam's grandchildren")
    println("jozo's MinL = ${jozo.motherInLaw().map{ it.name }}")
    // jozo's MinL = [Eva, Sona]
    // jozo ma svoju manzelku sonu za svokru, lebo ma dieta so svojou dcerou...
}
