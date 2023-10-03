data class Rectangle(val height: Int, var width: Int) {
    val isSquare: Boolean
        get() { return height == width }
    fun size():Int { return height * width }
    fun size_():Int  = height * width
    val area:Int
       get() { return height * width }
    var xx:Int
        get() {
            return width
        }
        set(value) { width = value}

}

fun main() {
    val rect = Rectangle(41, 43)
    println("Toto $rect je stvorec: ${rect.isSquare}")
    println("Obsah $rect je: ${rect.size()}")
    println("Area $rect je: ${rect.area}")
    rect.xx = 1990
}
