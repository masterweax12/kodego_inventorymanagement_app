package com.kodego.inventorymanagement.app.juntilla

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.kodego.inventorymanagement.app.juntilla.databinding.ActivityViewPagerBinding

class ViewPagerActivity : AppCompatActivity() {
    lateinit var binding : ActivityViewPagerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val images = listOf(
            R.drawable.ic_baseline_lock_24,
            R.drawable.ic_baseline_lock_open_24,
            R.drawable.ic_baseline_perm_identity_24,
            R.drawable.ic_baseline_person_24,
            R.drawable.ic_baseline_person_outline_24
        )

        val adapter = ViewPagerAdapter(images)
        binding.viewPagerView.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPagerView){tab, position ->
            tab.text = "Tab ${position+1}"
        }.attach()
    }
}