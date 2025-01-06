package com.example.androidhomeworks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import com.example.androidhomeworks.databinding.FragmentTicTacToeBinding


class TicTacToeFragment : Fragment() {
    private var _binding: FragmentTicTacToeBinding? = null
    private val binding get() = _binding!!
    private var turn = 0
    private lateinit var board: Array<IntArray>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTicTacToeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUp()
    }


    private fun setUp() {
        val rows = requireArguments().getInt("rows")
        val columns = requireArguments().getInt("columns")
        board = Array(rows) { IntArray(columns) }


        for (row in 0 until rows) {
            for (col in 0 until columns) {
                val button = ImageButton(requireContext()).apply {
                    layoutParams = GridLayout.LayoutParams().apply {
                        width = 0
                        height = 0
                        rowSpec = GridLayout.spec(row, 1f)
                        columnSpec = GridLayout.spec(col, 1f)
                    }
                    scaleType = ImageView.ScaleType.CENTER_INSIDE
                }

                button.setOnClickListener {
                    if (turn % 2 == 0) {
                        onButtonClick(button, R.drawable.x, row, col, 1)
                    } else {
                        onButtonClick(button, R.drawable.circle, row, col, 2)
                    }
                    turn++
                }


                binding.gameGrid.addView(button)
            }
        }


    }

    private fun onButtonClick(button: ImageButton, image: Int, row: Int, column: Int, player: Int) {
        board[row][column] = player
        button.setImageResource(image)
        button.isEnabled = false
        if (checkWinner(player)) {
            Toast.makeText(context, "player $player won", Toast.LENGTH_SHORT).show()
            for (i in 0 until binding.gameGrid.childCount) {
                val child = binding.gameGrid.getChildAt(i)
                if (child is ImageButton) {
                    child.isEnabled = false
                }
            }
        } else if (isItTie()) {
            Toast.makeText(context, "its a tie nobody won", Toast.LENGTH_SHORT).show()
        }

    }

    private fun checkWinner(player: Int): Boolean {
        for (i in board.indices) {
            if (board[i].all { it == player }) return true
            if (board.all { it[i] == player }) return true
        }

        if (board.indices.all { board[it][it] == player }) return true
        if (board.indices.all { board[it][board.size - 1 - it] == player }) return true

        return false
    }

    private fun isItTie(): Boolean {
        for (row in board) {
            if (row.contains(0)) {
                return false
            }
        }
        return true
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
