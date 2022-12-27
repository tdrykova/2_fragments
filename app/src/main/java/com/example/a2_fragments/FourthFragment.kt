package com.example.a2_fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a2_fragments.databinding.FragmentFourthBinding
import java.lang.ClassCastException

class FourthFragment : Fragment() {

    private var _binding: FragmentFourthBinding? = null

    private val binding get() = requireNotNull(_binding)

    private var listener: MainActivityInteraction? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as MainActivityInteraction
        } catch (castException: ClassCastException) {
            /** The activity does not implement the listener.  */
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFourthBinding.inflate(inflater, container, false)
        (requireActivity() as? MainActivityInteraction)?.openFourthBtn()
        val textViewAnswer = binding.result

        if (listener!!.operationCode == Constants.delCode) textViewAnswer.text =
            solved() + listener!!.getAnswer() else textViewAnswer.text =
            solved() + listener!!.getAnswer().toInt()

        binding.btnCancel4.setOnClickListener {
            (requireActivity() as? MainActivityInteraction)?.hideFourthBtn()
            activity?.onBackPressed()
        }

        return binding.root
    }

    private fun solved(): String {
        val operationCode = listener!!.operationCode
        val firstNumber: Int = listener?.firstNumber!!
        val secondNumber: Int = listener?.secondNumber!!
        return if (operationCode == -1) {
            "$firstNumber ? $secondNumber = "
        } else if (operationCode == Constants.plusCode) {
            "$firstNumber + $secondNumber = "
        } else if (operationCode == Constants.minusCode) {
            "$firstNumber - $secondNumber = "
        } else if (operationCode == Constants.delCode) {
            "$firstNumber / $secondNumber = "
        } else {
            "$firstNumber * $secondNumber = "
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}