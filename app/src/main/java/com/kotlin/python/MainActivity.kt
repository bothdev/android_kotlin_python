package com.kotlin.python

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)

    }

    fun submitText(view: View) {
        if (!Python.isStarted()) {
            Python.start(AndroidPlatform(this))
        }

        val text = editText.text.toString()

        // Pass the text to Python using Chaquopy
        val result = Python.getInstance().getModule("py_code").callAttr("my_function", text)

        // Display the result in an alert dialog box
        val builder = AlertDialog.Builder(this)
        builder.setMessage(result.toString())
        builder.setTitle("Result")
        builder.setPositiveButton("OK", null)
        builder.create().show()
    }

}