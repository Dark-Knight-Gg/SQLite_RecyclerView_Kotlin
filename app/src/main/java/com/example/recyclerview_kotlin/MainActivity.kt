package com.example.recyclerview_kotlin

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private var listJob = ArrayList<Job>()
    private lateinit var jobAdadpter:JobAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var txtTitle:TextView
    private lateinit var btnAdd :Button
    public lateinit var dataBase: DataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtTitle = findViewById(R.id.txtTitle)
        btnAdd =findViewById(R.id.btnAdd)
        dataBase = DataBase(this)
        dataBase.Query("CREATE TABLE IF NOT EXISTS Job(Id INTEGER PRIMARY KEY AUTOINCREMENT,Name NVARCHAR(200), Describe NVARCHAR(400),Picture BOB)")
        recyclerView =findViewById(R.id.recyclerview)
        recyclerView.layoutManager=LinearLayoutManager(this)
        jobAdadpter =JobAdapter(this,listJob)
        recyclerView.adapter =jobAdadpter
        btnAdd.setOnClickListener () {
            intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
        var intent =intent
        var name= intent.getStringExtra("Name")
        var describe=intent.getStringExtra("Describe")
        var picture =intent.getIntExtra("Picture",0)
    }
    public fun Delete(index:Int){
        var dialog:Dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.delete_custom)
        var dialog_btnNo :Button = dialog.findViewById(R.id.dialog_btnNo)
        var dialog_btnYes:Button= dialog.findViewById(R.id.dialog_btnYes)
        dialog_btnNo.setOnClickListener(){
            dialog.dismiss()
        }
        dialog_btnYes.setOnClickListener(){
            listJob.removeAt(index)
            jobAdadpter.notifyDataSetChanged()
            dialog.dismiss()
        }
        dialog.show()
    }

}