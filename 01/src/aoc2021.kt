import java.io.File

fun main() {
    val input = File("day01.txt").readLines().map{it.toInt()}
    println(input)
    var cnt = 0
    for (i in 0 .. input.size-2){
      if ( input[i] < input[i+1] ) cnt++
    }
    println(cnt)
    println((input zip input.drop(1)).count{ it.first < it.second})
}