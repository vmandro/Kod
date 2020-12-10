package app.reversi.listener

import android.view.View
import app.reversi.model.Game

interface StateListener {
    fun onStateChanged(v: View, gameState: Game)
}