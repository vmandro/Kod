// https://www.facebook.com/103011471377982/photos/a.103029011376228/103028974709565/?type=3

fun main(args:Array<String>) {
    for(i in 0..9) {            // prva cifra
        for(j in 0..9) {        // druha cifra
            for(k in 0..9) {    // tretia cifra
                val str :String = i.toString() + j.toString() + k.toString()
                if (
                    verifyOLD("682",str) == "10" &&
                    //verifyOLD("614",str) == "01"  &&
                    verifyOLD("645",str) == "01"  &&
                    verifyOLD("206",str) == "02"  &&
                    verifyOLD("738",str) == "00"  &&
                    verifyOLD("780",str) == "01") {
                    println(str)
                }

            }
        }
    }

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