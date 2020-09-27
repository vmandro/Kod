package ContravariantneZviera

abstract class Zviera(val size : Int = 0) { }
data class Macka(val krasa : Int) : Zviera(1) { }
data class Pes(val dravost : Int) : Zviera(2) { }

interface Compare<in T> {       // alias comparable
    fun compare(z1: T, z2: T): Int
}

val MackaCompare : Compare<Macka> = object: Compare<Macka> {
    override fun compare(m1: Macka, m2: Macka): Int {
        println("macky$m1 a $m2 si porovnavaju  ${m1.krasa} a ${m2.krasa}")
        return m1.krasa - m2.krasa
    }
}
// Macka <: Zviera
// Compare[Zviera] <: Compare[Macka]
// preto toto nejde
// val ZvieraCompare: Compare<Zviera> = MackaCompare

val ZvieraCompare: Compare<Zviera> = object: Compare<Zviera> {
    override
    fun compare(z1: Zviera, z2: Zviera): Int {
        println("zviera $z1 a $z2 si porovnavaju ${z1.size} a ${z2.size}")
        return z1.size - z2.size
    }
}

fun main(args : Array<String>) {
    println(MackaCompare.compare(Macka(5), Macka(2)))
    println(ZvieraCompare.compare(Macka(5), Pes(12)))
    println(ZvieraCompare.compare(Pes(15), Pes(11)))
}
