import java.io.File

fun inc(input: List<Int>) = (input zip (input.drop(1))).count { it.first < it.second }
fun join2(input1: List<Int>, input2: List<Int>) = (input1 zip input2).map { it.first + it.second }
fun join3(input1: List<Int>, input2: List<Int>, input3: List<Int>) = join2(join2(input1, input2), input3)


fun inc1(input : List<Int>) : Int {
    var count = 0
    for(i in 0 until input.size-1)
        if (input[i+1] > input[i]) count++
    return count
}
//partA:1548
//partB:1589

fun main() {
    val input = File("day01.txt").readLines().map{it.toInt()}
    println("partA:${inc(input)}")
    println("partB:${inc(join3(input, input.drop(1), input.drop(2)))}")
}
