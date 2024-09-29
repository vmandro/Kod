enum class F {KAMEN, NOZNICE, PAPIER, JASTER, SPOK}

fun random() : F = F.values().random()

val sheldonRules = listOf(
    Pair(F.NOZNICE, F.PAPIER),
    Pair(F.PAPIER, F.KAMEN),
    Pair(F.KAMEN, F.JASTER),
    Pair(F.JASTER, F.SPOK),
    Pair(F.SPOK, F.NOZNICE),
    Pair(F.NOZNICE, F.JASTER),
    Pair(F.JASTER, F.PAPIER),
    Pair(F.PAPIER, F.SPOK),
    Pair(F.SPOK, F.KAMEN),
    Pair(F.KAMEN, F.NOZNICE)
)
fun main() {
    var wins = 0
    var draws = 0
    for (i in 1..1000000) {
        val x = random()
        val y = random()
        if (x == y) draws++
        //println("$x - $y ... ${rules.contains(Pair(x,y))}")
        else if (sheldonRules.contains(Pair(x,y))) wins++
    }
    println("$wins, $draws")
}
