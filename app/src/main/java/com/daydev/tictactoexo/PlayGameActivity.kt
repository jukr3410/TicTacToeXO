package com.daydev.tictactoexo

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class PlayGameActivity : AppCompatActivity() {


    private var tableSize: Int? = null
    private var consecutivePoint: Int? = null
    private lateinit var game: Game
    lateinit var table: GridView
    lateinit var textData: TextView
    lateinit var textPlayer: TextView
    lateinit var btnReset: Button

    val map = LinkedHashMap<Int,String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playgame)

        table = findViewById(R.id.datagrid)
        btnReset = findViewById(R.id.button_reset)
        textPlayer = findViewById(R.id.textView_label_player)

        textPlayer.text = "Player 1 (X) Turn"

        tableSize = intent.getStringExtra("tableSizeSelected")!!.toInt()
        consecutivePoint = intent.getStringExtra("consecutivePointSelected")!!.toInt()
        game = Game(tableSize!!,consecutivePoint!!)

        drawTable()

        table.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                // Get the GridView selected/clicked item text
                textData = view.findViewById(R.id.textdata)
                val selectedItem = parent.getItemAtPosition(position).toString()
                var current = textData.text.toString()


                if (current == "" && game.playerTurn == 1){
                    game.table[map[position]!!.take(1).toInt()][map[position]!!.takeLast(1).toInt()] = game.player1
                    textData.text = game.player1
                    textPlayer.text = "Player 2 (O) Turn"
                    game.nextTurn()

                }else if(current == "" && game.playerTurn == 2){
                    game.table[map[position]!!.take(1).toInt()][map[position]!!.takeLast(1).toInt()] = game.player2
                    textData.text = game.player2
                    textPlayer.text = "Player 1 (X) Turn"
                    game.nextTurn()

                }else{
                    Toast.makeText(this@PlayGameActivity,"Can't choose", Toast.LENGTH_SHORT).show()
                }


                if (game.isGameOver()){
                    //Toast.makeText(this@PlayGameActivity,game.winner(), Toast.LENGTH_SHORT).show()
                    textPlayer.text = game.winner()
                    btnReset.visibility = View.VISIBLE
                    table.isEnabled = false
                }


            }
        }

        findViewById<TextView>(R.id.textView_objectiveNumber).text = "${game.consecutivePoint}, Table ${game.tableSize} x ${game.tableSize}"

        findViewById<TextView>(R.id.button_reset).setOnClickListener{
            game.resetTable()
            drawTable()
            btnReset.visibility = View.INVISIBLE
            textPlayer.text = "Player 1 (X) Turn"
            table.isEnabled = true
        }

    }

    private fun drawTable(){
        //map grid position to array2d of gametable
        var k = 0
        for (i in game.table.indices){
            for (j in game.table.indices){
                map.put(k++,"$i,$j")
            }
        }

        var arrayAdapter = ArrayAdapter(this, R.layout.celltable, R.id.textdata, Array(tableSize!!*tableSize!!){ i -> "" } )
        table.numColumns = tableSize!!
        table.adapter = arrayAdapter
    }

}