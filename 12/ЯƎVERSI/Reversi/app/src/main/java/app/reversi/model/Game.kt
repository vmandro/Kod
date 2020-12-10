package app.reversi.model


class Game {

    var id = ""
    var size = 8
    var playground = List(size) { List(size) { Cell(0, 0, -1) } }
    var on_turn: Long = 0
    var winner: Long = -1
    var greens_left: Long = 0
    var reds_left: Long = 0

}