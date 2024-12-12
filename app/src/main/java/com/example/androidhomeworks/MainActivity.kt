package com.example.androidhomeworks

import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.androidhomeworks.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpAndesMountain()
        clickSaveToArchive()
        clickOnOverviewTab()
        clickOnDetailsTab()
        clickBookNow()
    }


    private fun setUpAndesMountain() {
        binding.ivBookingPlace.setImageResource(R.drawable.andes_mountain)
        binding.tvNameOfPlace.text = getString(R.string.andes_mountain)
        binding.tvContinentOfPlace.text = getString(R.string.south_america)
        binding.tvPrice.text = getString(R.string.price)
        binding.tvPriceAmount.text = getString(R.string.andes_price_amount)
        binding.tvTimeValue.text = getString(R.string.andes_time)
        binding.tvTemperatureValue.text = getString(R.string.temperature_in_andes)
        binding.tvRatingValue.text = getString(R.string.andes_rating)
        binding.tvPlaceDescription.text = getString(R.string.andes_overview)

    }


    private fun clickSaveToArchive() {
        binding.btnArchive.setOnClickListener {
            if (binding.btnArchive.isActivated) {
                binding.btnArchive.imageTintList = null
            } else {
                binding.btnArchive.imageTintList =
                    ContextCompat.getColorStateList(this, R.color.bright_yellow)
            }
            binding.btnArchive.isActivated = !binding.btnArchive.isActivated
        }
    }

    private fun clickBookNow() {
        binding.btnBookNow.setOnClickListener {
            Toast.makeText(this, getString(R.string.booked_toast), Toast.LENGTH_SHORT).show()
        }

    }

    private fun clickOnOverviewTab() {
        binding.tvOverview.setOnClickListener {
            binding.tvOverview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22f)
            binding.tvOverview.setTypeface(null, Typeface.BOLD)
            binding.tvDetails.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
            binding.tvDetails.setTypeface(null, Typeface.NORMAL)

            binding.tvPlaceDescription.text = getString(R.string.andes_overview)
        }
    }

    private fun clickOnDetailsTab() {
        binding.tvDetails.setOnClickListener {
            binding.tvDetails.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22f)
            binding.tvDetails.setTypeface(null, Typeface.BOLD)
            binding.tvOverview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
            binding.tvOverview.setTypeface(null, Typeface.NORMAL)

            binding.tvPlaceDescription.text = getString(R.string.andes_description)
        }
    }
}