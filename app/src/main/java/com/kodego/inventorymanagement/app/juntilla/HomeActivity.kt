package com.kodego.inventorymanagement.app.juntilla

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.kodego.inventorymanagement.app.juntilla.databinding.ActivityHomeScreenBinding
import com.kodego.inventorymanagement.app.juntilla.databinding.QuantityDialogBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding : ActivityHomeScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //data source
        var productList = mutableListOf<Products>(
            Products(R.drawable.beverage_waterbottle, "Water Bottle", "This Beverage drink of Water Bottle to refresh your thirst",5),
            Products(R.drawable.beverage_wine, "Wine", "A Wine",12),
            Products(R.drawable.milk_icon, "Milk", "A healthy drink of Milk",11),
            Products(R.drawable.coffee_drink, "Coffee", "Delicious Coffee to start your day",8),
            Products(R.drawable.milk_tea, "Milk Tea", "Teens Favorite parting time drink with friends",4),
            Products(R.drawable.burger, "Yumburger", "A Delicious burger for snack",9),
            Products(R.drawable.pizza, "Pizza Pepperoni", "Who doesn't like pizza?",13),
            Products(R.drawable.fried_chicken, "Fried Chicken", "Much like of KFC's Fried Chicken",8),
            Products(R.drawable.french_fries, "French Fries", "Crispy French Fries",5),
            Products(R.drawable.packge, "Amazon Package", "This is an unclaimed package",2)
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

        adapter.onUpdateButtonClick = { item:Products, position:Int ->
            showUpdateDialog(item, position)
        }

        binding.myRecycler.adapter = adapter
        binding.myRecycler.layoutManager = GridLayoutManager(this, 2)




    }
    fun showUpdateDialog(item: Products, position: Int){
        val dialog = Dialog(this)
        val binding: QuantityDialogBinding = QuantityDialogBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)
        dialog.show()
    }
}