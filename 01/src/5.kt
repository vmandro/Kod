interface Expr
enum class Operator { Plus, Times }
data class Num(val value: Int) : Expr
data class Variable(val variable: String) : Expr
data class Op(val operator: Operator, val left: Expr, val right: Expr) : Expr

fun derive(e: Expr, variable : String): Expr {
    if (e is Num) {     // typeof
        return Num(0)
    } else if (e is Variable) {
        return if (e.variable == variable) Num(1) else Num(0)
    } else if (e is Op) {
        when(e.operator) {
            Operator.Plus -> return Op(Operator.Plus, derive(e.left, variable), derive(e.right, variable))
            Operator.Times -> return Op(Operator.Plus,
                    Op(Operator.Times, derive(e.left, variable), e.right),
                    Op(Operator.Times, derive(e.right, variable), e.left) )
        }
    }
    throw IllegalArgumentException("Unknown expression")
}

fun simplify(e: Expr): Expr {
    when (e) {
        is Op -> {
            when (e.operator) {
                Operator.Plus -> {
                    return if (e.left is Num && e.right is Num) Num(e.left.value + e.right.value)
                    else if (e.left == Num(0)) simplify(e.right)
                    else if (e.right == Num(0)) simplify(e.left)
                    else e.copy(left = simplify(e.left), right = simplify(e.right))
                }
                Operator.Times -> {
                    return if (e.left is Num && e.right is Num) Num(e.left.value * e.right.value)
                    else if (e.left == Num(1)) simplify(e.right)
                    else if (e.right == Num(1)) simplify(e.left)
                    else e.copy(left = simplify(e.left), right = simplify(e.right))
                }
                else -> throw IllegalArgumentException("Unknown expression2")
            }
        }
        else -> return e
    }
    return e
}


fun main(args: Array<String>) {
    val expr = Op(Operator.Plus, Op(Operator.Times, Variable("x"),Variable("x")), Num(1))
    println("x+x+1 dx = ${derive(expr,"x")}")
    println("simplified" + simplify(Variable("x")))
    println("simplified" + simplify(derive(expr, "x")))
}

