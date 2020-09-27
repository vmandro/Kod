// String, String?, Int, Int?, Any
// Int je subtype Number,
// Int nie je subtype Int

fun test(i : Int) {
      val n : Number = i    // nadtyp dostane hodnotu podtypu
}

// Int je podtyp Int ?, nie naopak
// A je podtyp A?, nie naopak

// covariancia A je podtyp B, tak T[A] je podtyp T[B]
interface Producer<out T> {      // out T znamena, ze generic chce byt covarianty, v scale to bolo Producer[+T]
    fun produce() : T
}

open class Zviera {
    open fun pozdrav() { }
}
class Macka : Zviera() {
    override fun pozdrav() { println("mnau") }
}
class Pes : Zviera() {
    override fun pozdrav() { println("haf") }
}

class Stado<T : Zviera>() {
    var lst: MutableList<T> = mutableListOf()
    val size: Int get() = lst.size
    operator fun get(i: Int): T { return lst.get(i); }   // T je v out pozicii
    operator fun set(i: Int, v: T) { lst.set(i, v) }
    fun append(v: T) { lst.add(v) }
}

fun pozdravitVsetky(zvery : Stado<Zviera>) {
    for (i in 0 until zvery.size)
        zvery[i].pozdrav()
}

fun pozdravitMacky(macky : Stado<Macka>) {
    for (i in 0 until macky.size)
        macky[i].pozdrav()
    //pozdravitVsetky(macky)                  // toto nejde lebo macky : Stado<Macka> a to nie je podtyp Stado<Zviera>
    pozdravitVsetky(macky as Stado<Zviera>)   // toto nejde lebo macky : Stado<Macka> a to nie je podtyp Stado<Zviera>
    pozdravitVsetky(macky)                  // toto uz ide, lebo kompilator uveril, ze macky : Stado<Zviera>
}
fun main(args : Array<String>) {
    val stado = Stado<Macka>()
    stado.append(Macka())
    stado.append(Macka())
    stado[1] = Macka()  // ilustracia operatora set
    val m = stado[0]    // ilustracia operatora get
    pozdravitMacky(stado)
    // stado[1] = Pes()  // nejde, lebo Macka nie je podtrieda Pes
    // stado.append(Pes())  // nejde, lebo Macka nie je podtrieda Pes
    //pozdravitVsetky(stado)  // nejde, lebo Stado<Macka> nie je podtrieda Stado<zviera>
    pozdravitVsetky(stado as Stado<Zviera>)  // ale presvedcime kompilator
    stado[1] = Pes()  // a uz nam veri
    stado.append(Pes())  // oklamali sme ho
    pozdravitVsetky(stado /* toto on uz vie ... as Stado<Zviera>*/)
    pozdravitMacky(stado)
 }
