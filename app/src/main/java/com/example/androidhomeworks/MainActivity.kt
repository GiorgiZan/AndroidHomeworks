package com.example.androidhomeworks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidhomeworks.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var containerOfWords = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clickSave()
        clickOutput()
        clickClear()

    }

    private fun clickSave(){
        binding.btnSave.setOnClickListener{
            if (binding.etAnagram.text!!.toString().isEmpty()){
                binding.etAnagram.error = resources.getString(R.string.anagram_field_empty)
            }
            containerOfWords.add(binding.etAnagram.text.toString().lowercase())
            binding.etAnagram.text!!.clear()
        }
    }

    private fun clickOutput(){
        binding.btnOutput.setOnClickListener{
            val anagramGroups = groupAnagrams(*containerOfWords.toTypedArray())
            val numberOfGroups = anagramGroups.size

            binding.tvAnagramsCount.text = "Number of anagram groups: $numberOfGroups"
        }
    }

    private fun clickClear(){
        binding.btnClear.setOnClickListener{
            containerOfWords.clear()
            binding.etAnagram.text!!.clear()
            binding.tvAnagramsCount.text = ""
        }
    }

    private fun groupAnagrams(vararg words: String): List<List<String>> {
        val mapOfAnagrams = mutableMapOf<String, MutableList<String>>()

        words.forEach { word ->
            val sortedKeyForMap = word.toCharArray().sorted().joinToString("")

            if (!mapOfAnagrams.containsKey(sortedKeyForMap)) {
                mapOfAnagrams[sortedKeyForMap] = mutableListOf()
            }
            mapOfAnagrams[sortedKeyForMap]!!.add(word)
        }

        return mapOfAnagrams.values.toList()
    }


}


