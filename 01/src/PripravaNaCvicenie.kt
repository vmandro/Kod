//fun main(args:Array<String>) {
//  for(a in args)
//      print("$a, ")
//
//  val lst : List<String> = listOf("Peter", "Adam", "Andrea", "Jana", "Eva")
//  println(lst.count())
//  println(lst.size)
//
//  println(lst.max())
//  println(lst.min())
//  val xx: List<Int> = lst.map { str -> str.length }
//  println(lst.map { it.length } )
//  println(lst.filter { it.length < 5} )
//  println(lst.filter { it.startsWith('A')} )
//  println(lst.plus("Xenia") )
//
//  var ww = mutableListOf("a", "b")
//// var qq : MutableList<String> = emptyM
// // MutableList<String>
//  val mlst = lst.toMutableList()
//  mlst.add("Zofia")
//  mlst.sort()
//  println(mlst)
//  mlst.sortBy { it.length }
//  println(mlst)
//  val map = mlst.groupBy { it.length }
//  println(map)
//  println(map[3])
////  println(slovaAB(3))
////  println(slovaAB_(3))
////  println(slovaAB__(3))
//
//// flapMap
//    val list10 = (1..10).toList()
//    print(list10)
//    val listN = { N : Int -> (1..N).toList() }
//    println(listN(100))
//    println(list10.count())
//    println(list10.sum())
//    val ee: List<Int> = list10.flatMap { x -> list10 }
//    println(list10.flatMap { list10 }.count())
//    println(list10.flatMap { listN(it) }.count())
//    // 1,1,2,1,2,3,1,2,3,4
//    // List<Pair<Int,Int>>
//    val listPairs= list10.flatMap { x -> listN(x).map { y -> Pair(x,y) } }
//    println(listPairs)
//    for (p in listPairs) {
//        val (a,b) = p
//        println("$a, $b")
//    }
//    for ((a,b) in listPairs)
//        println("$a, $b")
//
//    val dd: Map<Int, List<Int>> = list10.groupBy { it % 3}
//    val jj: List<Int>? = dd[0]
//    // A A?
//
//    println(dd[2]?.count())
//    println(list10.reduce { acc,x -> acc+x })
//
//    println(slova(
//        //listOf("a", "b", "c")
//        "abc".toList(), 4))
//
//    println(podmnoziny(listOf(1,2,3)))
//    println(podmnoziny(listOf("a", "b", "c", "d")))
//}
////fun slovaAB(n:Int) : List<String> {
////    if (n == 0)
////        return listOf("")
////    else {
////        val kratsie = slovaAB(n-1)
////        var dlhsie = mutableListOf<String>()
////        for (w in kratsie) {
////            dlhsie.add(w + "a")
////            dlhsie.add(w + "b")
////        }
////        return dlhsie
////    }
////}
////
////fun slovaAB_(n:Int) : List<String> {
////    if (n == 0)
////        return listOf("")
////    else {
////        val kratsie = slovaAB_(n-1)
////        return kratsie.flatMap { listOf(it+"a", it+"b") }
////    }
////}
////
////fun slovaAB__(n:Int) : List<String> =
////    if (n == 0)
////        listOf("")
////    else
////        slovaAB__(n-1).flatMap { w -> listOf(w+"a", w+"b") }
//
////fun slova(abeceda : List<Char>, n : Int) : List<String> {
////    if (n == 0)
////        return listOf("")
////    else
////        return slova(abeceda, n-1).flatMap { w:String -> abeceda.map { ch:Char -> w + ch} }
////}
//
//fun slova(abeceda : List<Char>, n : Int) : List<String> =
//    if (n == 0)
//        listOf("")
//    else
//        slova(abeceda, n-1).flatMap { w -> abeceda.map { ch -> w + ch} }
//
//        // {1,2} = {} {1} {2} {1,2} 2^n  2^0 = 1
////fun podmnoziny(mnozina : List<Int>) : List<List<Int>> =
////    if (mnozina.count() == 0)
////        listOf(emptyList())
////    else {
////        val prvy = mnozina.first()   // { first, rest}
////        val mensie = podmnoziny(mnozina.drop(1))  // 2^(n-1)
////        mensie.map { it.plus(prvy) } .plus (mensie)
////    }
//
//
////fun <T> podmnoziny(mnozina : List<T>) : List<List<T>> =
////    if (mnozina.count() == 0)
////        listOf(emptyList())
////    else {
////        val mensie = podmnoziny(mnozina.drop(1))  // 2^(n-1)
////        mensie.map { it.plus(mnozina.first()) }.plus(mensie)
////    }
