package CovariantneZviera

open class Zviera {
    open fun pozdrav() { }
}
class Macka : Zviera() {
    override fun pozdrav() { println("mnau") }
}
class Pes : Zviera() {
    override fun pozdrav() { println("haf") }
}

class Stado<out T : Zviera>(val lst : List<T>) {
    //var lst2: MutableList<T> = mutableListOf()
    val size: Int get() = lst.size
    operator fun get(i: Int): T { return lst.get(i); }   // T je v out pozicii
    //operator fun set(i: Int, v: T) { lst.set(i, v) }
    //fun append(v: T) { lst.add(v) }
}
fun pozdravitVsetky(zvery : Stado<Zviera>) {
    for (i in 0 until zvery.size)
        zvery[i].pozdrav()
}

fun vitMacky(macky : Stado<Macka>) {
    for (i in 0 until macky.size)
        macky[i].pozdrav()
    pozdravitVsetky(macky)                  // toto ide lebo macky : Stado<Macka> a to je podtyp Stado<Zviera>
}

fun main(args : Array<String>) {
    val stado = Stado<Macka>(listOf(Macka(), Macka()))
    pozdravitVsetky(stado)
}
