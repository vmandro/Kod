package app.reversi.activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import app.reversi.utils.Mode
import app.reversi.R
import app.reversi.utils.Constants
import app.reversi.utils.Table
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    private var playBtn: Button? = null
    private var authInLayout: View? = null
    private var authOutLayout: View? = null
    private var logInBtn: Button? = null
    private var signUpBtn: Button? = null
    private var logOutBtn: Button? = null
    private var currentUserTV: TextView? = null

    private var database: DatabaseReference? = null
    private var auth: FirebaseAuth? = null
    private var firebaseUser: FirebaseUser? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

        initFirebase()
        initView()
        updateUser()
        setupPermissions()
    }

    override fun onStart() {
        super.onStart()
        setupAuthView()
    }

    private fun initView() {
        playBtn = findViewById(R.id.playButton)
        playBtn?.setOnClickListener {
            val i = Intent(applicationContext, ConnectActivity::class.java)
            i.putExtra("UserId", firebaseUser!!.uid)
            startActivityForResult(i, Constants.CONNECTION_REQUEST)
        }

        authInLayout = findViewById(R.id.authInLayout)
        authOutLayout = findViewById(R.id.authOutLayout)

        signUpBtn = findViewById(R.id.signUpButton)
        signUpBtn!!.setOnClickListener {
            val i = Intent(applicationContext, AuthActivity::class.java)
            i.putExtra("Mode", Mode.SIGN_UP)
            startActivity(i)
        }

        logInBtn = findViewById(R.id.logInButton)
        logInBtn!!.setOnClickListener {

            val i = Intent(applicationContext, AuthActivity::class.java)
            i.putExtra("Mode", Mode.SIGN_IN)
            startActivity(i)
        }

        logOutBtn = findViewById(R.id.logOutButton)
        logOutBtn!!.setOnClickListener {
            auth!!.signOut()
            Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()
            setupAuthView()
        }
    }

    private fun setupAuthView() {
        updateUser()
        // Check if user is signed in (non-null) and update UI accordingly.
        currentUserTV = findViewById(R.id.currentUserTextView)
        if (firebaseUser == null) {
            currentUserTV!!.text = resources.getString(R.string.welcome_new)
            authInLayout!!.visibility = View.VISIBLE
            authOutLayout!!.visibility = View.GONE
            playBtn!!.visibility = View.GONE
        } else {
//            initGameInitListener()
            currentUserTV!!.text = resources.getString(R.string.welcome_signed_in, firebaseUser!!.uid)
            authInLayout!!.visibility = View.GONE
            authOutLayout!!.visibility = View.VISIBLE
            playBtn!!.visibility = View.VISIBLE
        }
    }

    private fun initFirebase() {
        database = FirebaseDatabase.getInstance().reference
        auth = FirebaseAuth.getInstance()
    }

    private fun updateUser() {
        firebaseUser = auth!!.currentUser
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun setupPermissions() {
        Log.d("mylog", "Permissions: askPermission started")
        if (applicationContext.checkSelfPermission(Manifest.permission.INTERNET) !=
            PackageManager.PERMISSION_GRANTED) {
            Log.d("mylog", "Permissions: shows an explanation for why permission is needed")
            if (/*false && */ shouldShowRequestPermissionRationale(Manifest.permission.INTERNET)) {
                Log.d("mylog", "Permissions: explain")
                val builder = AlertDialog.Builder(this)
                builder.setPositiveButton(
                    android.R.string.ok
                ) { _, _ ->
                    requestPermissions(
                        arrayOf(Manifest.permission.INTERNET),
                        Constants.INTERNET_PERMISSION_CODE
                    )
                }
                builder.create().show()
            } else {
                Log.d("mylog", "Permissions: request")
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.INTERNET),
                    Constants.INTERNET_PERMISSION_CODE
                )
            }
        } else {
            Log.d("mylog", "Permissions: INTERNET granted")
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            Constants.INTERNET_PERMISSION_CODE -> {
                for (i in grantResults.indices) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        Log.d("mylog", "GRANTED")
                    } else {  // denied
                        Log.d("mylog", "DENIED")
                    }
                }
                return
            }
        }
    }

    override fun onResume() {
        super.onResume()
        setupAuthView()
    }
}
