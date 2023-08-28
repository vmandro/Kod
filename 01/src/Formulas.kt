package Streda

import Streda.Formula.*

abstract  class Formula {
    class True(): Formula()
    class False(): Formula()
    class Variable(val variable: String) : Formula()
    class Conjunction(val left:Formula, val right:Formula) : Formula()
    class Disjunction(val left: Formula, val right:Formula) : Formula()
    class Implication(val left:Formula, val right:Formula) : Formula()
    class Equivalence(val left:Formula, val right:Formula) : Formula()
    class Negation(val formula:Formula):Formula()

    // maly priklad preliezanie, z kotre sa mozete odrazit
    // formula je uzavreta (closed), ak neobsahuje ziadnu premennu
    fun closed() : Boolean {
        return when (this) {
            is True, is False     -> true
            is Variable           -> false
            is Conjunction -> left.closed() && right.closed()
            is Disjunction -> left.closed() && right.closed()
            is Implication -> left.closed() && right.closed()
            is Equivalence -> left.closed() && right.closed()
            is Negation -> formula.closed()
            else -> throw Exception("something bad happened")
        }
    }

    fun eval(interpretation : Map<String, Boolean>) : Boolean {
        TODO()
    }

    fun negate() : Formula {
        TODO()
    }

    fun simplify() : Formula {
        TODO()
    }

    fun resolve(g: Formula): Formula {
        TODO()
    }

    fun fully_resolve(f: Formula): Formula {
        TODO()
    }
}

fun main(args : Array<String>) {
    // (A or B') => C
    val f =
            Implication(
                    Disjunction(
                            Formula.Variable("A"),
                            Negation(
                                    Formula.Variable("B")
                            )
                    ),
                    Formula.Variable("C")
            )

    println(f)
    println(f.eval(mapOf("A" to true, "B" to false, "C" to false)))
    println(f.negate())
    println(f.simplify())

    // (A and A') or (A' or A)
    val g =
            Disjunction(
                Conjunction(
                    Variable("A"),
                    Negation(Variable("A"))
                ),
                Disjunction(
                    Negation(Variable("A")),
                    Variable("A")
                )
            )

    println(g.eval(mapOf("A" to true)))
    println(g.eval(mapOf("A" to false)))
    println(g.negate())
    println(g.simplify())
    println(g.negate().simplify())

    val p =
            Disjunction(
                Variable("A"),
                Variable("B")
            )

    val q =
            Disjunction(
                Negation(Variable("B")),
                Variable("C")
            )

    println(p.resolve(q))

    val x =
            Disjunction(
                Variable("A"),
                Disjunction(
                    Variable("B"),
                    Variable("C")
                )
            )

    val y = Disjunction(
                Variable("D"),
                Disjunction(
                    Negation(Variable("C")),
                    Variable("E")
                )
            )

    println(x.fully_resolve(y))
}
