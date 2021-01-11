package com.daydev.tictactoexo

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialog

class GameOverDialog (context: Context): AppCompatDialog(context){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_gameover)

        val playAgain = findViewById<TextView>(R.id.textView_playagain)
        val home = findViewById<TextView>(R.id.textView_home)

        playAgain!!.setOnClickListener{
            cancel()
        }

        home!!.setOnClickListener{

        }
    }
}