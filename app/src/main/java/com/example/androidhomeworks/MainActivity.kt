package com.example.androidhomeworks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidhomeworks.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val categories = mutableListOf(
        RecyclerCategory("ALL"),
        RecyclerCategory("🎉  Party"),
        RecyclerCategory("🏕  Camping"),
        RecyclerCategory("Category 1"),
        RecyclerCategory("Category 2"),
        RecyclerCategory("Category 3")
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUp()
    }


    private fun setUp() {
        binding.categoriesRecyclerView.adapter = CategoriesAdapter(categories) { selectedCategory ->
            filterClothesByCategory(selectedCategory)
        }

        binding.clotheRecyclerView.adapter = ClothesAdapter(ClothesList.clothes)
    }

    private fun filterClothesByCategory(selectedCategory: String) {
        val filteredSelectedCategory =
            selectedCategory.filter { it.isLetter() || it.isDigit() || it.isWhitespace() }
                .trimStart()
        val filteredClothes = if (filteredSelectedCategory == "ALL") {
            ClothesList.clothes
        } else {
            ClothesList.clothes.filter { it.categoryType.trim() == filteredSelectedCategory }
        }

        binding.clotheRecyclerView.adapter = ClothesAdapter(filteredClothes.toMutableList())
    }
}

