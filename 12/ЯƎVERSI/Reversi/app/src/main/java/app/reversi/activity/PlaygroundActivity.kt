package app.reversi.activity

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import app.reversi.view.PlaygroundView
import app.reversi.R
import app.reversi.listener.StateListener
import app.reversi.model.Cell
import app.reversi.model.Game
import app.reversi.utils.Constants
import app.reversi.utils.Table
import com.google.firebase.database.*

class PlaygroundActivity: AppCompatActivity() {

    private var playgroundView: PlaygroundView? = null
    private var playgroundViewView: PlaygroundView? = null
    private var statusBar: TextView? = null
    private var userUid: String? = null
    private var opponentUid: String? = null
    private var giveUpBtn: Button? = null
    private var database: DatabaseReference? = null
    private var currentGameReference: DatabaseReference? = null
    private var role: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("mylog", "Playground on create")
        setContentView(R.layout.playground_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        initView()
        initFirebase()
        initGame()
        initPlayers()
        setPlaygroundListener()
        setDBListener()
    }

    private fun initView() {
        Log.d("mylog", "PlaygroundActivity, initView()")
        playgroundView = PlaygroundView(this)
        playgroundViewView = findViewById(R.id.playgroundView)
        statusBar = findViewById(R.id.pg_status_bar)
        giveUpBtn = findViewById(R.id.give_up_button)
        giveUpBtn!!.setOnClickListener {
            giveUp()
        }
    }

    private fun giveUp() {
        Log.d("mylog", "PlaygroundActivity, giveUp()")
        val winner = 1 - role!!.toLong()
        Log.d("mylog", "PlaygroundActivity, giveUp(), winner=$winner, role=$role")
        updWinnerStatus(winner)
        updDBWinner(winner)
    }

    private fun updWinnerStatus(winner: Long) {
        Log.d("mylog", "winner->$winner")
        when (winner) {
            Constants.RED -> {
                statusBar!!.text = resources.getString(R.string.winner_status, "Red")
            }
            Constants.GREEN -> {
                statusBar!!.text = resources.getString(R.string.winner_status, "Green")
            }
        }
    }

    private fun initFirebase() {
        Log.d("mylog", "PlaygroundActivity, initFirebase()")
        database = FirebaseDatabase.getInstance().reference
    }

    private fun setPlaygroundListener() {
        Log.d("mylog", "PlaygroundActivity, setPlaygroundListener()")
        playgroundView!!.setOnStateChangeListener(object: StateListener {
            override fun onStateChanged(v: View, gameState: Game) {
                Log.d("mylog", "onTurn: ${gameState.on_turn}, win: ${gameState.winner}")
                updStatus(gameState)
                updDB(gameState)
            }
        })
    }

    private fun updDB(gameState: Game) {
        Log.d("mylog", "PlaygroundActivity, udpDB()")
        currentGameReference!!.child(Table.ON_TURN).setValue(gameState.on_turn)
        currentGameReference!!.child(Table.GREENS_LEFT).setValue(gameState.greens_left)
        currentGameReference!!.child(Table.REDS_LEFT).setValue(gameState.reds_left)
        currentGameReference!!.child(Table.WINNER).setValue(gameState.winner)
        currentGameReference!!.child(Table.PLAYGROUND)
        currentGameReference!!.child(Table.PLAYGROUND).setValue(gameState.playground)
    }

    private fun updDBWinner(winner: Long) {
        Log.d("mylog", "PlaygroundActivity, updDBWinner()")
        currentGameReference!!.child(Table.WINNER).setValue(winner)
    }

    private fun initPlayers() {
        Log.d("mylog", "PlaygroundActivity, initPlayers()")
        role = intent.getStringExtra("Role")
        playgroundView!!.setRole(role!!.toLong())
        Log.d("mylog", "Playground activity, initPlayers, role $role")
        if (role == "0") {
            userUid = intent.getStringExtra("UserUid")
            opponentUid = intent.getStringExtra("OpponentUid")
            Log.d("mylog", "userUid = $userUid, opponentUid = $opponentUid")
            saveGameToDB()
        } else if (role == "1") {
            val gameId = intent.getStringExtra("GameId")
            Log.d("mylog", "player 1, game id: $gameId")
            currentGameReference = database!!.child(Table.GAMES).child(gameId)
            currentGameReference!!.addListenerForSingleValueEvent(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot?) {
                    userUid = snapshot!!.child(Table.PLAYER_1_UID).toString()
                    Log.d("mylog", "player 1, user id: $userUid")
                    opponentUid = snapshot.child(Table.PLAYER_2_UID).toString()
                    Log.d("mylog", "player 1, opp id: $opponentUid")
                    statusBar!!.text = resources.getString(R.string.player2_connected)
                }
                override fun onCancelled(p0: DatabaseError?) {
                    Log.d("mylog", "Error on Playground activity, initPlayers(): $p0.toString()")
                }
            })
        }
    }

    private fun setDBListener() {
        Log.d("mylog", "PlaygroundActivity, setDBListener()")
        currentGameReference!!.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot?) {
                Log.d("mylog", "Playground activity, setDBListener, getting and setting game state..")
                updPlaygroundAsSnapshot(snapshot!!.child(Table.PLAYGROUND))
                PlaygroundView.game.on_turn = snapshot.child(Table.ON_TURN).value as Long
                PlaygroundView.game.greens_left = snapshot.child(Table.GREENS_LEFT).value as Long
                PlaygroundView.game.reds_left = snapshot.child(Table.REDS_LEFT).value as Long
                PlaygroundView.game.winner = snapshot.child(Table.WINNER).value as Long
                if (PlaygroundView.game.winner != Constants.NONE) {
                    Log.d("mylog", "Playground activity, setDBListener, winner is ${PlaygroundView.game.winner}")
                    val winner = PlaygroundView.game.winner
                    updWinnerStatus(winner)
                } else {
                    Log.d("mylog", "Playground activity, setDBListener, playground updating")
                    playgroundView!!.updGame(PlaygroundView.game)
                    updStatus(PlaygroundView.game)
                    playgroundViewView!!.invalidate()
                }
            }
            override fun onCancelled(p0: DatabaseError?) {
                Log.d("mylog", "Error on Playground activity, setDBListener(): $p0.toString()")
            }
        })
    }

    private fun updPlaygroundAsSnapshot(data: DataSnapshot) {
        Log.d("mylog", "PlaygroundActivity, updPlaygroundAsSnapshot()")
        val data2dList = data.value as List<List<HashMap<String, Long>>>
        for (i in 0 until data2dList.size)
            for (j in 0 until data2dList[i].size) {
                val x = data2dList[i][j]["x"]!!.toInt()
                val y = data2dList[i][j]["y"]!!.toInt()
                val value = data2dList[i][j]["value"]!!.toLong()
                PlaygroundView.game.playground[y][x].x = x
                PlaygroundView.game.playground[y][x].y = y
                PlaygroundView.game.playground[y][x].value = value
            }
    }

    private fun updStatus(gameState: Game) {
        Log.d("mylog", "PlaygroundActivity, updStatus()")
        statusBar!!.text = resources.getString(R.string.game_status, gameState.on_turn.toString(),
            gameState.winner.toString(), gameState.greens_left.toString(), gameState.reds_left.toString(), role)
    }

    private fun initGame() {
        Log.d("mylog", "PlaygroundActivity, initGame()")
        PlaygroundView.game.size = 8
        PlaygroundView.game.greens_left = 2
        PlaygroundView.game.reds_left = 2
        PlaygroundView.game.on_turn = 0 // 0 -> GREEN, 1 -> RED
        initPlayground()
        updStatus(PlaygroundView.game)
//        initWinningPlayground()
    }

    private fun initPlayground() {
        Log.d("mylog", "PlaygroundView, initPlayground()")
        PlaygroundView.game.playground = List(PlaygroundView.game.size) { List(PlaygroundView.game.size) { Cell(0, 0, -1) } }
        for (x in 0 until PlaygroundView.game.size)
            for (y in 0 until PlaygroundView.game.size) {
                PlaygroundView.game.playground[y][x].x = y
                PlaygroundView.game.playground[y][x].y = x
                PlaygroundView.game.playground[y][x].value = -1
            }

        PlaygroundView.game.playground[PlaygroundView.game.size/2 - 1][PlaygroundView.game.size/2 - 1].value = 1 - PlaygroundView.game.on_turn
        PlaygroundView.game.playground[PlaygroundView.game.size/2 - 1][PlaygroundView.game.size/2].value = PlaygroundView.game.on_turn
        PlaygroundView.game.playground[PlaygroundView.game.size/2][PlaygroundView.game.size/2].value = 1 - PlaygroundView.game.on_turn
        PlaygroundView.game.playground[PlaygroundView.game.size/2][PlaygroundView.game.size/2 - 1].value = PlaygroundView.game.on_turn
    }

    private fun initWinningPlayground() {
        PlaygroundView.game.playground = List(PlaygroundView.game.size) { List(PlaygroundView.game.size) { Cell(0, 0, -1) } }
        for (x in 0 until PlaygroundView.game.size)
            for (y in 0 until PlaygroundView.game.size) {
                PlaygroundView.game.playground[y][x].x = y
                PlaygroundView.game.playground[y][x].y = x
                PlaygroundView.game.playground[y][x].value = 1
            }
    }

    private fun saveGameToDB() {
        Log.d("mylog", "PlaygroundActivity, saveGameToDB()")
        PlaygroundView.game.id = database!!.child(Table.GAMES).push().key
        currentGameReference = database!!.child(Table.GAMES).child(PlaygroundView.game.id)
        currentGameReference!!.setValue(PlaygroundView.game)

        database!!.child(Table.PLAYERS).child(userUid).child(Table.CURRENT_GAME_ID).setValue(PlaygroundView.game.id)
        database!!.child(Table.PLAYERS).child(opponentUid).child(Table.CURRENT_GAME_ID).setValue(PlaygroundView.game.id)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d("mylog", "PlaygroundActivity, onOptionsItemSelected()")
        val id = item.itemId
        if (id == android.R.id.home) {
            giveUp()
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        giveUp()
        super.onBackPressed()
    }
}