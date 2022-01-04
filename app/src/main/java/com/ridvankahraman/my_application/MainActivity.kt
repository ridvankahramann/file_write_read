package com.ridvankahraman.my_application

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.view.View
import android.widget.TextView
import android.widget.Toast
import java.io.*
import java.lang.Exception
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var btnSave = findViewById<Button>(R.id.btnSave)
        var btnGet = findViewById<Button>(R.id.btnGet)
        var data = findViewById<View>(R.id.data) as TextView
        var name = findViewById<View>(R.id.name) as TextView
        btnSave.setOnClickListener {
            var data = data.text.toString()
            var name = name.text.toString()
            val fileOutputStream: FileOutputStream

            try {
                fileOutputStream = openFileOutput(name,Context.MODE_PRIVATE)
                fileOutputStream.write(data.toByteArray())
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
            }catch (e:FileNotFoundException){
                e.printStackTrace()
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
        btnGet.setOnClickListener {

            var name = name.text.toString()
            if(name.toString() != null && name.trim() != ""){
                val fileInputStream: FileInputStream
                fileInputStream = openFileInput(name)
                val inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
                val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
                val stringBuilder: StringBuilder = StringBuilder()
                var text: String? = null
                while ({text = bufferedReader.readLine(); text}() != null){
                    stringBuilder.append(text)
                }
                data.setText(stringBuilder.toString()).toString()
            }
        }
    }
}