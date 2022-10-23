package com.kodego.inventorymanagement.app.juntilla

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kodego.inventorymanagement.app.juntilla.databinding.ActivityProductDetailBinding

class ProductDetailActivity : AppCompatActivity() {
    lateinit var binding : ActivityProductDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //data from home activity
        var itemName:String? = intent.getStringExtra("itemName")
        var itemDescription:String? = intent.getStringExtra("itemDescription")
        var imageItem:Int = intent.getIntExtra("itemImage", 0)
        var quantity:Int = intent.getIntExtra("quantity", 0)

        binding.imgItem2.setImageResource(imageItem)
        binding.txtItemName2.text = itemName
        binding.txtDescription2.text = itemDescription
        binding.txtQuantity.text = quantity.toString()

    }
}