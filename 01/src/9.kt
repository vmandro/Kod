sealed class Expression {
    data class Num(val value: Int) : Expression()
    data class Variable(val variable: String) : Expression()
    data class Op(val operator: Operator, val left: Expression, val right: Expression) : Expression()

    fun derive(variable : String): Expression {
        if (this is Num) {     // typeof
            return Num(0)
        } else if (this is Variable) {
            return if (this.variable == variable) Num(1) else Num(0)
        } else if (this is Op) {
            when(this.operator) {
                Operator.Plus -> return Op(Operator.Plus, left.derive(variable), right.derive(variable))
                Operator.Times -> return Op(Operator.Plus,
                        Op(Operator.Times, left.derive(variable), right),
                        Op(Operator.Times, right.derive(variable), left) )
            }
        }
        throw IllegalArgumentException("Unknown expression")
    }
    fun simplify(): Expression {
        when (this) {
            is Op -> {
                when (operator) {
                    Operator.Plus -> {
                        return if (left is Num && right is Num) Num(left.value + right.value)
                        else if (left == Num(0)) right.simplify()
                        else if (right == Num(0)) left.simplify()
                        else copy(left = left.simplify(), right = right.simplify())
                    }
                    Operator.Times -> {
                        return if (left is Num && right is Num) Num(left.value * right.value)
                        else if (left == Num(1)) right.simplify()
                        else if (right == Num(1)) left.simplify()
                        else copy(left = left.simplify(), right = right.simplify())
                    }
                    else -> throw IllegalArgumentException("Unknown expression2")
                }
            }
            else -> return this
        }
        return this
    }

}

fun main(args: Array<String>) {
    val expr =  Expression.Op(Operator.Plus,
            Expression.Op(Operator.Times,
                    Expression.Variable("x"),
                    Expression.Variable("x")),
            Expression.Num(1))
    println("x+x+1 dx = ${expr.derive("x")}")
    println("simplified" + expr.derive("x").simplify())
}

