//https://www.facebook.com/103011471377982/photos/a.103029011376228/103028974709565/?type=3


fun main() {
    println(
        (0..9).flatMap { i ->
            (0..9).flatMap { j ->
                (0..9).flatMap { k -> listOf("$i$j$k")}}}
            .filter{
                    verify("682", it) == "10" &&
                    //verify("614", it) == "01" &&
                    verify("645", it) == "01" &&
                    verify("206", it) == "02" &&
                    verify("738", it) == "00" &&
                    verify("780", it) == "01"}
    )
}
fun verify(g : String, code:String) : String {
    val correctWellPlaced = (0..2).count { g[it] == code[it] }
    val somewherePlaced =
        (0..2).count { i -> g[i] != code[i] && (0..2).any { j -> i != j && g[i] == code[j] } }
    return "$correctWellPlaced$somewherePlaced"
}
