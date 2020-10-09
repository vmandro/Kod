//fun main(args:Array<String>) {
//    println(
//        (0..9).flatMap { i ->
//            (0..9).flatMap { j ->
//                (0..9).flatMap { k -> listOf("" + i + j + k)}}}
//            .filter{
//                verify("682", it) == "10" &&
//                        verify("614", it) == "01" &&
//                        verify("206", it) == "02" &&
//                        verify("738", it) == "00" &&
//                        verify("780", it) == "01"}
//    )
//}
//fun verify(g : String, code:String) : String {
//    val correctWellPlaced = (0..2).count {g[it] == code[it]}
//    var wellPlaced =
//        (0..2).count { i -> g[i] != code[i] && (0..2).any{ j->i != j && g[i] == code[j] }}
//    return ""+correctWellPlaced + wellPlaced
//}