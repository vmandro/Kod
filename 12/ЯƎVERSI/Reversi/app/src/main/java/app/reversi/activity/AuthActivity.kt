package app.reversi.activity

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import app.reversi.utils.Mode
import app.reversi.R
import app.reversi.utils.Table
import app.reversi.model.Player
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.auth_activity.*
import java.lang.Exception

class AuthActivity: AppCompatActivity() {

    private var mode: Mode? = null
    private var nickTIL: TextInputLayout? = null
    private var emailTV: TextView? = null
    private var authBtn: Button? = null
    private var authTV: TextView? = null

    private var database: DatabaseReference? = null
    private var auth: FirebaseAuth? = null
    private var firebaseUser: FirebaseUser? = null

    private var player = Player()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.auth_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        initFirebase()
        initView()
        initMode()
        handleAuth()
    }

    private fun initMode() {
        if (intent == null) finish()

        Log.d("mylog", intent.getSerializableExtra("Mode").toString())
        mode = (intent.getSerializableExtra("Mode") as Mode)
        when (mode) {
            Mode.SIGN_UP -> {
                authBtn!!.text = resources.getString(R.string.sign_up)
                authTV!!.text = resources.getString(R.string.sign_up)
                nickTIL!!.visibility = View.VISIBLE
            }
            Mode.SIGN_IN -> {
                authBtn!!.text = resources.getString(R.string.log_in)
                authTV!!.text = resources.getString(R.string.log_in)
                nickTIL!!.visibility = View.GONE
            }
            else -> {
                finish()
            }
        }
    }

    private fun initView() {
        nickTIL = findViewById(R.id.nickTextInputLayout)
        emailTV = findViewById(R.id.connectNickTextView)
        authBtn = findViewById(R.id.authButton)
        authTV = findViewById(R.id.authTextView)
    }

    private fun handleAuth() {
        authBtn!!.setOnClickListener {
            val nick = nickTextView.text.toString()
            val email = emailTextView.text.toString()
            val password = passwordTextView.text.toString()

            if ((mode == Mode.SIGN_UP && nick == "") || email == "" || password == "") {
                authFailToast("Values cannot be empty")
                return@setOnClickListener
            }

            when {
                mode!!.equals(Mode.SIGN_UP) -> {
                    Log.d("mylog", "auth: sign up..")
                    auth!!.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                authSuccessToast()
                                Log.d("mylog", "createUserWithEmail:success")
                                firebaseUser = auth!!.currentUser
                                saveToDB(nick, email)
                                finish()
                            } else {
                                handleFailure(task)
                            }
                        }
                }
                mode!!.equals(Mode.SIGN_IN) -> {
                    Log.d("mylog", "auth: log in..")
                    auth!!.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                authSuccessToast()
                                Log.d("mylog", "createUserWithEmail:success")
                                firebaseUser = auth!!.currentUser
                                finish()
                            } else {
                                handleFailure(task)
                            }
                        }
                }
            }
        }
    }

    private fun saveToDB(nick: String, email: String) {
        player.uid = firebaseUser!!.uid
        player.nick = nick
        player.email = email

        database!!.child(Table.PLAYERS).child(player.uid)
        database!!.child(Table.PLAYERS).child(player.uid).setValue(player)
    }

    private fun handleFailure(task: Task<AuthResult>) {
        try {
            throw task.exception!!
        } catch (e: FirebaseAuthWeakPasswordException) {
            authFailToast("Password is too weak (less than 6 symbols)")
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            authFailToast("Invalid credentials")
        } catch (e: FirebaseAuthUserCollisionException) {
            authFailToast("User already exists")
        } catch (e: Exception) {
            authFailToast("Check the parameters")
            Log.d("mylog", e.message.toString())
        }
    }

    private fun initFirebase() {
        database = FirebaseDatabase.getInstance().reference
        auth = FirebaseAuth.getInstance()
    }

    private fun authFailToast(msg: String? = "") {
        Toast.makeText(this, "Authentication failed. $msg", Toast.LENGTH_SHORT).show()
    }

    private fun authSuccessToast() {
        Toast.makeText(this, "Authentication success", Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}