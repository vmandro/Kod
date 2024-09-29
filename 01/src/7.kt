fun main(args: Array<String>) {
    for(i in 0..10) println(i)
    for(i in 10 downTo 0 step 3) println(i)
    for(i in 0..10) println(i)
    for (c in 'A'..'F') println(Integer.toBinaryString(c.code))
    for (c in ' '..'z')
        if (c in 'a'..'z' || c in 'A'..'Z')
            print(c)
    for (c in ' '..'z')
        when (c) {
            in '0'..'9' -> println("digit")
            in 'a'..'z', in 'A'..'Z' -> println("letter")
        }
}