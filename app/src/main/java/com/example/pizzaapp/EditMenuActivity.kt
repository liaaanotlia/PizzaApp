package com.example.pizzaapp

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.example.pizzaapp.model.MenuModel

class EditMenuActivity : AppCompatActivity() {
    lateinit var image : ImageView
    companion object{
        val IMAGE_REQUEST_CODE = 100
        //penampung data yang akan diubah
        var idMakanan = 1
        var namaMakanan = "tes"
        var hargaMakanan = 10000
        lateinit var gambarMakanan: Bitmap
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_menu)

        getSupportActionBar()?.hide()

        //instance
        image = findViewById(R.id.imageMenu)
        val textId : EditText = findViewById(R.id.menuId)
        val textName : EditText = findViewById(R.id.menuName)
        val textHarga : EditText = findViewById(R.id.menuPrice)
        val btnAddImage : Button = findViewById(R.id.buttonAddImage)
        val btnUpdate : Button = findViewById(R.id.buttonUpdateMenu)

        //event saat button add (+) di-klik
        btnAddImage.setOnClickListener {
            pickImageGalery()
        }

        btnUpdate.setOnClickListener {
            val databaseHelper = DatabaseHelper(this)

            val id : Int = textId.text.toString().toInt()
            val name : String = textName.text.toString().trim()
            val price : Int = textHarga.text.toString().toInt()
            val bitmapDrawable : BitmapDrawable = image.drawable as BitmapDrawable
            val bitmap : Bitmap = bitmapDrawable.bitmap

            val menuModel = MenuModel(id,name,price,bitmap)
            databaseHelper.editMenu(menuModel)
        }
    }
}