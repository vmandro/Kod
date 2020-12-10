package app.reversi.activity

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import app.reversi.R
import app.reversi.utils.Table
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.connect_activity.*

class ConnectActivity: AppCompatActivity() {

    private var connectBtn: Button? = null
    private var pendingSw: Switch? = null
    private var database: DatabaseReference? = null
    private var currentUid: String? = null
    private var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("mylog", "Connect Activity on create")
        setContentView(R.layout.connect_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        initFirebase()
        initUser()
        initView()
        resetConnection()
    }

    private fun initUser() {
        currentUid = intent.getStringExtra("UserId")
        Log.d("mylog", currentUid)
    }

    private fun initView() {
        connectBtn = findViewById(R.id.connectButton)
        connectBtn!!.setOnClickListener {
            connectionRequest()
        }

        pendingSw = findViewById(R.id.pendingSwith)
        pendingSw!!.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                setAvailability(true)
            } else {
                setAvailability(false)
            }
        }
    }

    private fun resetConnection() {
        Log.d("mylog", "ConnectActivity, resetConnection, currUid = $currentUid")
        database!!.child(Table.PLAYERS).child(currentUid).child(Table.CURRENT_GAME_ID).setValue("")
        setAvailability(false)
    }

    private fun setAvailability(boolean: Boolean) {
        database!!.child(Table.PLAYERS).child(currentUid).child(Table.AVAILABLE).setValue(boolean)
        if (boolean) {
            initGameInitListener()
        }
    }

    private fun initFirebase() {
        database = FirebaseDatabase.getInstance().reference
        auth = FirebaseAuth.getInstance()
    }

    private fun connectionRequest() {
        val nick = connectNickTextView.text.toString()
        if (nick == "") {
            connectFailToast("Email value is required")
            return
        }

        var found = false

        database!!.child(Table.PLAYERS).addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val players = dataSnapshot.children
                for (player in players) {
                    Log.d("mylog", player.key + player.child(Table.NICK))
                    if (player.child(Table.NICK).value.toString() == nick) {
                        if (player.child(Table.UID).value == currentUid) {
                            connectFailToast("You cannot connect to yourself")
                        }
                        if (player.child(Table.AVAILABLE).value != true) {
                            connectFailToast("The player is not available")
                        }
                        setAvailability(false)
                        connectSuccessToast()
                        found = true

                        val i = Intent(applicationContext, PlaygroundActivity::class.java)
                        i.putExtra("Role", "0")
                        i.putExtra("UserUid", currentUid)
                        i.putExtra("OpponentUid", player.child(Table.UID).value.toString())
                        startActivity(i)
                    }
                }
                if (!found) connectFailToast("The player with this email does not exist")
            }

            override fun onCancelled(p0: DatabaseError?) {
                connectFailToast("Could not find the player")
            }
        })
    }

    private fun initGameInitListener() {
        Log.d("mylog", "listener init")
        Log.d("mylog", "currentUid = $currentUid")
        val connRequestReference =
            database!!.child(Table.PLAYERS).child(currentUid).child(Table.CURRENT_GAME_ID)

        val gameListener = object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot?) {
                Log.d("mylog", "snapshot: $snapshot")
                if (! (snapshot!!.value.toString().isEmpty() || snapshot.value == null || snapshot.value.toString() == "")) {
                    setAvailability(false)
                    connectSuccessToast()
                    Log.d("mylog", "listener, intent")
                    val i = Intent(applicationContext, PlaygroundActivity::class.java)
                    i.putExtra("Role", "1")
                    i.putExtra("UserUid", currentUid)
                    i.putExtra("OpponentUid", "")
                    i.putExtra("GameId", snapshot.value.toString())
                    Log.d("mylog", "listener, starting activity")
                    startActivity(i)
                }
            }
            override fun onCancelled(p0: DatabaseError?) {
                Log.d("mylog", p0.toString())
            }
        }

        connRequestReference.addValueEventListener(gameListener)
    }

    private fun connectFailToast(msg: String? = "") {
        Toast.makeText(this, "Connection failed. \n$msg", Toast.LENGTH_SHORT).show()
    }

    private fun connectSuccessToast() {
        Toast.makeText(this, "Connection succeed.", Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            setAvailability(false)
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}