package com.kodego.inventorymanagement.app.juntilla

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.kodego.inventorymanagement.app.juntilla.databinding.ActivityHomeScreenBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding : ActivityHomeScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //data source
        var productList = mutableListOf<Products>(
            Products(R.drawable.beverage_waterbottle, "Water Bottle", "This Beverage drink of Water Bottle to refresh your thirst"),
            Products(R.drawable.beverage_wine, "Wine", "A Wine"),
            Products(R.drawable.milk_icon, "Milk", "A healthy drink of Milk"),
            Products(R.drawable.coffee_drink, "Coffee", "Delicious Coffee to start your day"),
            Products(R.drawable.milk_tea, "Milk Tea", "Teens Favorite parting time drink with friends"),
            Products(R.drawable.burger, "Yumburger", "A Delicious burger for snack"),
            Products(R.drawable.pizza, "Pizza Pepperoni", "Who doesn't like pizza?"),
            Products(R.drawable.fried_chicken, "Fried Chicken", "Much like of KFC's Fried Chicken"),
            Products(R.drawable.french_fries, "French Fries", "Crispy French Fries"),
            Products(R.drawable.packge, "Amazon Package", "This is an unclaimed package")
        )

        //pass data source to adapter
        val adapter = ProductAdapter(productList)
        adapter.onItemClick = {
            val intent = Intent(this, ProductDetailActivity::class.java)
            intent.putExtra("itemName",it.itemName)
            intent.putExtra("itemDescription",it.itemDescription)
            intent.putExtra("itemImage",it.imageName)
            startActivity(intent)
        }

        binding.myRecycler.adapter = adapter
        binding.myRecycler.layoutManager = GridLayoutManager(this, 2)

        //get data from another screen
//        var name: String? = intent.getStringExtra("nameID")
//
//        binding.tvWelcome.text = "Welcome $name"
    }
}