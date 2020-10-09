fun main(args:Array<String>) {
    println((0..9).flatMap { i ->
        (0..9).flatMap { j ->
            (0..9).flatMap {k->
                listOf("$i$j$k")
            }
        }
    }.filter {str ->
                    verify("682",str) == "10" &&
                    verify("614",str) == "01"  &&
                    verify("206",str) == "02"  &&
                    verify("738",str) == "00"  &&
                    verify("780",str) == "01"
            }
    )
}
fun verify(g : String, code:String) : String {
    val correctPlaced = (0..2).count { i -> g[i] == code[i] }
    val somewherePlaced =
        (0..2).count { i -> g[i] != code[i] && (0..2).any {
                j -> i!=j && g[i] == code[j] } }
    return ""+correctPlaced+somewherePlaced
}

fun verifyOLD(g : String, code:String) : String {
    var correctPlaced = 0
    for(i in 0..2) {
        if (g[i] == code[i])  // tie, ktore su na vlastnom mieste
            correctPlaced++
    }
    var somewherePlaced = 0
    for(i in 0..2) {
        if (g[i] != code[i]) {  // z tych, ktore nie su na vlastnom mieste
            for (j in 0..2) {  // co ak sa vyskytuju inde
                if (i != j) {
                    if (g[i] == code[j])
                        somewherePlaced++
                }
            }
        }
    }
    return ""+correctPlaced+somewherePlaced
}