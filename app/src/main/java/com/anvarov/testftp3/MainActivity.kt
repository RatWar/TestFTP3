package com.anvarov.testftp3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val infoos = findViewById<TextView>(R.id.infoos)
        var t1=FTPthread()
        @Suppress("DEPRECATION") val path = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_DOWNLOADS)
        val fileName = "deleted.json"
        val fileWrite = File(path, fileName)
        t1.urisArr = fileWrite
        t1.num = 1
        t1.infoos = infoos
        t1.start()
    }
}