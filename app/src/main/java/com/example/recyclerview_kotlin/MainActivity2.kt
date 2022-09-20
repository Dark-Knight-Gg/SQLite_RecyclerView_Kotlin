package com.example.recyclerview_kotlin

import android.Manifest
import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.ByteArrayOutputStream
import java.io.InputStream

class MainActivity2 : AppCompatActivity() {
    private lateinit var m2_imgPicture:ImageView
    private lateinit var m2_imgCamera:ImageView
    private  lateinit var m2_imgFolder:ImageView
    private lateinit var m2_txtTilte:TextView
    private lateinit var m2_edtname :EditText
    private lateinit var m2_edtDescribe:EditText
    private  lateinit var m2_btnOk : Button
    private lateinit var m2_btnCancle :Button
    private var Code1=1;
    private var Code2 =2;
    public lateinit var dataBase: DataBase

    private  fun AnhXa(){
        m2_imgPicture=findViewById(R.id.m2_imgPicture)
       m2_imgCamera=findViewById(R.id.m2_imgCamera)
      m2_imgFolder=findViewById(R.id.m2_imgFolder)
       m2_txtTilte=findViewById(R.id.m2_txtTitle)
         m2_edtname =findViewById(R.id.m2_edtName)
         m2_edtDescribe=findViewById(R.id.m2_edtDescribe)
        m2_btnOk=findViewById(R.id.m2_btnOk)
        m2_btnCancle=findViewById(R.id.m2_btnCancle)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        AnhXa()
        m2_btnCancle.setOnClickListener(){
            intent =Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        m2_btnOk.setOnClickListener(){
            var bitmapDrawable:BitmapDrawable  = m2_imgPicture.drawable as BitmapDrawable
            var bitmap:Bitmap = bitmapDrawable.bitmap as Bitmap
            var byteArrayOutputStream:ByteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream)
            var picture:ByteArray= byteArrayOutputStream.toByteArray()
            var name = m2_edtname.text.toString()
            var describe = m2_edtDescribe.text.toString()
            dataBase.Insert(name,describe,picture)
        }
        m2_imgCamera.setOnClickListener(){
            intent =Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent,Code1)
        }
        m2_imgFolder.setOnClickListener(){
            intent = Intent(Intent.ACTION_PICK)
            intent.setType("image/*")
            startActivityForResult(intent,Code2)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data1: Intent?) {
        super.onActivityResult(requestCode, resultCode, data1)
        if(requestCode==Code1&& resultCode== RESULT_OK && data1!=null){
            m2_imgPicture.setImageBitmap(data1.extras?.get("data")as Bitmap)
        }
        if(requestCode==Code2&&resultCode== RESULT_OK && data1!=null){
            var uri:Uri? = data1.data
            var iputStream: InputStream?= uri?.let { contentResolver.openInputStream(it) }
            var bitmap:Bitmap= BitmapFactory.decodeStream(iputStream)
            m2_imgPicture.setImageBitmap(bitmap)
        }
    }
}