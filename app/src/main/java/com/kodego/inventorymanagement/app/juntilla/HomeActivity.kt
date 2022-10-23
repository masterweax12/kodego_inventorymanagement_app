package com.kodego.inventorymanagement.app.juntilla

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.kodego.inventorymanagement.app.juntilla.databinding.ActivityHomeScreenBinding
import com.kodego.inventorymanagement.app.juntilla.databinding.AddDialogBinding
import com.kodego.inventorymanagement.app.juntilla.databinding.QuantityDialogBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding : ActivityHomeScreenBinding
    lateinit var adapter : ProductAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //data source
        var productList : MutableList<Products> = mutableListOf<Products>(
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
        adapter = ProductAdapter(productList)
        adapter.onItemClick = {
            val intent = Intent(this, ProductDetailActivity::class.java)
            intent.putExtra("itemName",it.itemName)
            intent.putExtra("itemDescription",it.itemDescription)
            intent.putExtra("itemImage",it.imageName)
            intent.putExtra("quantity",it.quantity
            )
            startActivity(intent)
        }

        adapter.onUpdateButtonClick = { item:Products, position:Int ->
            showUpdateDialog(item, position)
        }

        adapter.onDeleteButtonClick = { item:Products, position:Int ->
            adapter.products.removeAt(position)
            adapter.notifyDataSetChanged()
        }

        binding.floatingActionButton.setOnClickListener(){
            showAddDialog()
        }

        binding.myRecycler.adapter = adapter
        binding.myRecycler.layoutManager = GridLayoutManager(this, 2)




    }
    fun showUpdateDialog(item: Products, position: Int){
        val dialog = Dialog(this)
        val binding: QuantityDialogBinding = QuantityDialogBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)
        dialog.show()

        binding.btnQtyUpdateDialog.setOnClickListener(){
            var newQuantity: Int = binding.etQtyDialog.text.toString().toInt()
            adapter.products[position].quantity = newQuantity
            adapter.notifyDataSetChanged()
            dialog.dismiss()
        }
    }

    fun showAddDialog(){
        val dialog = Dialog(this)
        val binding: AddDialogBinding = AddDialogBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)
        dialog.show()

        val images = arrayListOf<String>("beluga_photo",
                "beverage_waterbottle",
                "beverage_wine",
                "burger",
                "coffee_drink",
                "french_fries",
                "fried_chicken",
                "ic_baseline_lock_24",
                "ic_baseline_lock_open_24",
                "ic_baseline_perm_identity_24",
                "ic_baseline_person_24",
                "ic_baseline_person_outline_24",
                "ic_baseline_remove_red_eye_24",
                "ic_baseline_visibility_27",
                "ic_baseline_visibility_off_24",
                "ic_baseline_visibility_off_27",
                "ic_launcher_background",
                "ic_launcher_foreground",
                "milk_icon",
                "milk_tea",
                "packge",
                "pizza",
                "splash_inventory",
                "warehouse_apk",
                "warehouse_icon"
        )

        val spinnerAdapter = ArrayAdapter(applicationContext,R.layout.textview, images)
        binding.spnImage.adapter = spinnerAdapter

        binding.btnAdd.setOnClickListener(){
            var image : Int = resources.getIdentifier(binding.spnImage.selectedItem.toString(), "drawable", packageName)
            var itemName : String = binding.etAddDialogName.text.toString()
            var itemDescription : String = binding.etAddDialogDescription.text.toString()
            var quantityDialogBinding : Int = binding.etDialogQuantity.text.toString().toInt()

            adapter.products.add(Products(image, itemName, itemDescription, quantityDialogBinding))
            adapter.notifyItemInserted(adapter.itemCount+1)
            dialog.dismiss()
        }


    }
}