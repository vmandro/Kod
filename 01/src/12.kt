// generics

val <T> List<T>.tail : List<T>      // extenstion property
    get() = drop(1)

fun <T> reverse(lst : List<T>) : List<T> {
    fun <T> reverse(lst : List<T>, acc : List<T>) : List<T> {
        if (lst.isEmpty())
            return acc
        else
            return reverse(lst.tail, acc.plusElement(lst.first()))
    }
    return reverse(lst, listOf())
}

fun <T:Number> double(x : T) : Double {
    return x.toDouble()/2.0
}

fun <T:Comparable<T>> max(a:T, b: T) : T {
    return if (a > b) a else b
}
/*
fun <T:Number> sum(lst : List<T>) : T {
    var suma : Number = 0;
    for(elem in lst) {
        suma = suma + elem
    }
    return suma
}
*/
/*
fun <T> isA(a : Any) {
    if (a is T) { // can not check for instance of erase type
    }
}
*/
inline fun <reified T> isA( a : Any) {
    if (a is T) { // can not check for instance of erase type
    }
}

fun main(args : Array<String>) {
    println(reverse(listOf(1,2,3,4,5)))
    println(max("Jana", "Jano"))

}