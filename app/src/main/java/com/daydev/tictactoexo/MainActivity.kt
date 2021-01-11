package com.daydev.tictactoexo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var spinnerTableSize: Spinner
    private lateinit var spinnerConsecutivePoint: Spinner
    private var tableSizeSelected: Int = 0
    private var consecutivePointSelected: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinnerTableSize = findViewById(R.id.spinner_tableSize)
        spinnerConsecutivePoint = findViewById(R.id.spinner_ConsecutivePoint)
        setupUI()

        spinnerTableSize.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(adapterView:  AdapterView<*>?, view: View?, position: Int, id: Long) {
                //Toast.makeText(this@MainActivity, "You selected ${adapterView?.getItemAtPosition(position).toString()}", Toast.LENGTH_SHORT).show()
                tableSizeSelected = position+3
                setConsecutivePoint()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        spinnerConsecutivePoint.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(adapterView:  AdapterView<*>?, view: View?, position: Int, id: Long) {
                //Toast.makeText(this@MainActivity, "You selected ${adapterView?.getItemAtPosition(position).toString()}", Toast.LENGTH_SHORT).show()
                consecutivePointSelected = position+3
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

    private fun setupUI() {
        val tableSizeList = mutableListOf("3x3","4x4","5x5","6x6","7x7")
        var tableAdapter = ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, tableSizeList)
        spinnerTableSize.adapter = tableAdapter
    }

    private fun setConsecutivePoint(){
        val consecutivePoint = mutableListOf<Int>().also {
            for (i in 3..tableSizeSelected!!){
                it.add(i)
            }
        }
        val consecutiveAdapter = ArrayAdapter<Int>(this@MainActivity,R.layout.support_simple_spinner_dropdown_item, consecutivePoint)
        spinnerConsecutivePoint.adapter = consecutiveAdapter
    }

    fun playGame(view: View) {
        val intent = Intent(this, PlayGameActivity::class.java)
        intent.putExtra("tableSizeSelected","$tableSizeSelected")
        intent.putExtra("consecutivePointSelected","$consecutivePointSelected")
        startActivity(intent)
    }

}