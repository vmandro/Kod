package app.reversi.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import app.reversi.R
import app.reversi.listener.StateListener
import app.reversi.model.Cell
import app.reversi.model.Game
import app.reversi.utils.Constants


class PlaygroundView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    View(context, attrs, defStyleAttr) {

    private var minSize: Float = 0.toFloat()
    private var cellSize: Float = 0.toFloat()
    private val paint: Paint = Paint()
    private val rect: Rect = Rect()
    private var greenDisk: Bitmap? = null
    private var redDisk: Bitmap? = null

    companion object {
        var onStateChangeListener: StateListener? = null
        var role: Long? = Constants.NONE
        val game = Game()
    }

    init {
        Log.d("mylog", "PlaygroundView, init")
        setWillNotDraw(false)
        initPlayersFigures()
    }

    fun setRole(r: Long) {
        Log.d("mylog", "PlaygroundView, setRole()")
        role = r
    }

    fun setOnStateChangeListener(l: StateListener) {
        onStateChangeListener = l
        onStateChangeListener!!.onStateChanged(this, game)
    }

    fun updGame(game0: Game) {
        Log.d("mylog", "Playground view, updGame, updating view..")
        game.playground = game0.playground
        game.on_turn = game0.on_turn
        game.greens_left = game0.greens_left
        game.reds_left = game0.reds_left
        game.winner = game0.winner
        Log.d("mylog", "Playground view, updGame, updated view..")
        if (!arePossibleMoves()) {
            if (game.greens_left > game.reds_left) game.winner = Constants.GREEN
            else game.winner = Constants.RED
            onStateChangeListener!!.onStateChanged(this, game)
            Log.d("mylog", "Playground view, no possible moves")
        }
    }

    private fun arePossibleMoves(): Boolean {
        for (i in 0 until game.size) {
            for (j in 0 until game.size) {
                if (game.playground[i][j].value == Constants.NONE) {
                    if (check(i, j, false))
                        return true
                }
            }
        }
        return false
    }

    override fun onTouchEvent(e: MotionEvent): Boolean {
        if (game.winner != Constants.NONE) {
            Toast.makeText(context, "The player ${game.winner} already won!", Toast.LENGTH_SHORT).show()
            return false
        }
        if (game.on_turn != role) {
            Toast.makeText(context, "The opponent is on turn", Toast.LENGTH_SHORT).show()
            return false
        }
        val action = e.action
        if (action == MotionEvent.ACTION_DOWN) {
            val iX = (e.x / cellSize).toInt()
            val iY = (e.y / cellSize).toInt()
            if (iX >= game.size || iY >= game.size)
                return true

            if (game.playground[iY][iX].value == Constants.NONE) {
                val valid = check(iX, iY, true)
                if (valid) {
//                    Toast.makeText(context,"Valid", Toast.LENGTH_SHORT).show()
                    invalidate()
                    switchOnTurn()
                    Log.d("mylog", "greens: ${game.greens_left}, reds: ${game.reds_left}")

                    onStateChangeListener!!.onStateChanged(this, game)
                } else {
                    Toast.makeText(context,"Not Valid", Toast.LENGTH_SHORT).show()
                }

                if (game.greens_left <= 0) {
                    Toast.makeText(context, "Green won!", Toast.LENGTH_SHORT).show()
                    onStateChangeListener!!.onStateChanged(this, game)
                }
                if (game.reds_left <= 0) {
                    Toast.makeText(context, "Red won!", Toast.LENGTH_SHORT).show()
                    onStateChangeListener!!.onStateChanged(this, game)
                }
            } else
                Toast.makeText(
                    context, "Not Valid",
                    Toast.LENGTH_LONG
                ).show()

        }
        return true
    }

    override fun onDraw(canvas: Canvas) {
        Log.d("mylog", "Playground view, onDraw")
        minSize = (Math.min(width, height) - 2).toFloat()
        cellSize = minSize / game.size

        canvas.drawColor(Color.WHITE)
        paint.color = Color.BLACK
        paint.strokeWidth = 1f
        for (i in 1..game.size) {
            canvas.drawLine(i * cellSize, 0f, i * cellSize, minSize, paint)
            canvas.drawLine(0f, i * cellSize, minSize, i * cellSize, paint)
        }

        for (y in 0 until game.size) {
            for (x in 0 until game.size) {
                rect.left = (x * cellSize + 2).toInt()
                rect.top = (y * cellSize + 2).toInt()
                rect.right = ((x + 1) * cellSize - 2).toInt()
                rect.bottom = ((y + 1) * cellSize - 2).toInt()
                if (game.playground[y][x].value != Constants.NONE)
                    canvas.drawBitmap(
                        if (game.playground[y][x].value == Constants.GREEN) greenDisk!! else redDisk!!,
                        null, rect, paint
                    )
            }
        }
    }

    private fun initPlayersFigures() {
        Log.d("mylog", "PlaygroundView, initPlayersFigures()")
        redDisk = BitmapFactory.decodeResource(resources, R.drawable.red_player)
        greenDisk = BitmapFactory.decodeResource(resources, R.drawable.green_player)
    }

    private fun flip(cellsToFlip: MutableList<Cell>) {
        for (cell: Cell in cellsToFlip) {
            cell.value = 1 - cell.value

            if (game.on_turn == Constants.GREEN) {game.greens_left++; game.reds_left--}
            else {game.reds_left++; game.greens_left--}
        }
    }

    private fun turn(x: Int, y: Int) {
        game.playground[y][x].value = game.on_turn

        if (game.on_turn == Constants.GREEN) game.greens_left++
        else game.reds_left++
    }

    private fun switchOnTurn() {
        game.on_turn = 1 - game.on_turn
    }

    private fun check(x: Int, y: Int, flip: Boolean): Boolean {
        val opposite = 1 - game.on_turn
        var valid = false

        run {
            // right
            var step = 1
            val toFlip: MutableList<Cell> = ArrayList()
            while (x + step < game.size && game.playground[y][x + step].value == opposite) {
                if (flip) toFlip.add(game.playground[y][x + step])
                step++
            }
            if (step > 1 && x + step < game.size && game.playground[y][x + step].value != Constants.NONE) {
                if (flip) turn(x, y)
                if (flip) flip(toFlip)
                valid = true
            }
        }
        run {
            // right up
            var step = 1
            val toFlip: MutableList<Cell> = ArrayList()
            while (y - step > 0 && x + step < game.size && game.playground[y - step][x + step].value == opposite) {
                if (flip) toFlip.add(game.playground[y - step][x + step])
                step++
            }
            if (step > 1 && y - step > 0 && x + step < game.size && game.playground[y - step][x + step].value != Constants.NONE) {
                if (flip) turn(x, y)
                if (flip) flip(toFlip)
                valid = true
            }
        }
        run {
            // up
            var step = 1
            val toFlip: MutableList<Cell> = ArrayList()
            while (y - step > 0 && game.playground[y - step][x].value == opposite) {
                if (flip) toFlip.add(game.playground[y - step][x])
                step++
            }
            if (step > 1 && y - step > 0 && game.playground[y - step][x].value != Constants.NONE) {
                if (flip) turn(x, y)
                if (flip) flip(toFlip)
                valid = true
            }
        }
        run {
            // left up
            var step = 1
            val toFlip: MutableList<Cell> = ArrayList()
            while (y - step > 0 && x - step > 0 && game.playground[y - step][x - step].value == opposite) {
                if (flip) toFlip.add(game.playground[y - step][x - step])
                step++
            }
            if (step > 1 && y - step > 0 && x - step > 0 && game.playground[y - step][x - step].value != Constants.NONE) {
                if (flip) turn(x, y)
                if (flip) flip(toFlip)
                valid = true
            }
        }
        run {
            // left
            var step = 1
            val toFlip: MutableList<Cell> = ArrayList()
            while (x - step > 0 && game.playground[y][x - step].value == opposite) {
                if (flip) toFlip.add(game.playground[y][x - step])
                step++
            }
            if (step > 1 && x - step > 0 && game.playground[y][x - step].value != Constants.NONE) {
                if (flip) turn(x, y)
                if (flip) flip(toFlip)
                valid = true
            }
        }
        run {
            // left down
            var step = 1
            val toFlip: MutableList<Cell> = ArrayList()
            while (y + step < game.size && x - step > 0 && game.playground[y + step][x - step].value == opposite) {
                if (flip) toFlip.add(game.playground[y + step][x - step])
                step++
            }
            if (step > 1 && y + step < game.size && x - step > 0 && game.playground[y + step][x - step].value != Constants.NONE) {
                if (flip) turn(x, y)
                if (flip) flip(toFlip)
                valid = true
            }
        }
        run {
            // down
            var step = 1
            val toFlip: MutableList<Cell> = ArrayList()
            while (y + step < game.size && game.playground[y + step][x].value == opposite) {
                if (flip) toFlip.add(game.playground[y + step][x])
                step++
            }
            if (step > 1 && y + step < game.size && game.playground[y + step][x].value != Constants.NONE) {
                if (flip) turn(x, y)
                if (flip) flip(toFlip)
                valid = true
            }
        }
        run {
            // right down
            var step = 1
            val toFlip: MutableList<Cell> = ArrayList()
            while (y + step < game.size && x + step < game.size && game.playground[y + step][x + step].value == opposite) {
                if (flip) toFlip.add(game.playground[y + step][x + step])
                step++
            }
            if (step > 1 && y + step < game.size && x + step < game.size && game.playground[y + step][x + step].value != Constants.NONE) {
                if (flip) turn(x, y)
                if (flip) flip(toFlip)
                valid = true
            }
        }
        return valid
    }





}