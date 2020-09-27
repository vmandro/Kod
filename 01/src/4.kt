enum class Color { RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET }

enum class Colour(val r: Int, val g: Int, val b: Int) {
    WHITE(0, 0, 0), RED(255, 0, 0),  YELLOW(255, 255, 0),
    GREEN(0, 255, 0), BLUE(0, 0, 255), BLACK(255, 255, 255);
    fun rgb() = (r * 256 + g) * 256 + b
}

fun getMnemonic(c : Colour) = {
    when (c) {
        Colour.WHITE -> "Biela"
        Colour.BLACK -> "Biela"
        else         -> "Seda"
    }
}

fun mix(c1: Color, c2: Color) =
    when (setOf(c1, c2)) {
        setOf(Color.RED, Color.YELLOW) -> Color.ORANGE
        setOf(Color.YELLOW, Color.BLUE) -> Color.GREEN
        setOf(Color.BLUE, Color.VIOLET) -> Color.INDIGO
        else -> throw Exception("Dirty color")
    }

fun mixOptimized(c1: Color, c2: Color) =
    when {
        (c1 == Color.RED && c2 == Color.YELLOW)  -> Color.ORANGE
        (c1 == Color.YELLOW && c2 == Color.BLUE) -> Color.GREEN
        (c1 == Color.BLUE && c2 == Color.VIOLET) -> Color.INDIGO
        else -> throw Exception("Dirty color")
    }
