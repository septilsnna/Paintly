package com.mobcom.paintly

//<<<<<<< HEAD
//=======
//>>>>>>> cd30b6e67aabe863dbdc814099195f551a4ca641
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import maes.tech.intentanim.CustomIntent

//<<<<<<< HEAD

//=======
class MainActivity : AppCompatActivity() {
    private lateinit var button: Button
//>>>>>>> cd30b6e67aabe863dbdc814099195f551a4ca641
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing1)
        button = findViewById(R.id.landpage1_next)
        button.setOnClickListener() {
            goToPage2()
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

//<<<<<<< HEAD

//=======
    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun goToPage2() {
        val intent = Intent(this, Landing2Activity::class.java)
        startActivity(intent)
        CustomIntent.customType(this, "left-to-right")
//>>>>>>> cd30b6e67aabe863dbdc814099195f551a4ca641
    }

}