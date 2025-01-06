package com.example.androidhomeworks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidhomeworks.databinding.FragmentConfigurationBinding


class ConfigurationFragment : Fragment() {
    private var _binding: FragmentConfigurationBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConfigurationBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listeners()
    }


    private fun listeners() {


        binding.btnStartGame.setOnClickListener {
            val selectedDimension = binding.spinnerGameDimensions.selectedItem.toString()
            val dimensions = selectedDimension.split("x").map { it.toInt() }

            val gameFragment = TicTacToeFragment()
            val bundle = Bundle().apply {
                putInt("rows", dimensions[0])
                putInt("columns", dimensions[1])
            }

            gameFragment.arguments = bundle
            parentFragmentManager.beginTransaction()
                .replace(R.id.main, gameFragment, "TicTacToeFragment")
                .addToBackStack("TicTacToeFragment")
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}