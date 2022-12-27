package com.example.a2_fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.a2_fragments.databinding.FragmentThirdBinding
import java.lang.ClassCastException

class ThirdFragment : Fragment() {

    private var _binding: FragmentThirdBinding? = null

    private val binding get() = requireNotNull(_binding)

    private var listener: MainActivityInteraction? = null
    private var isOperationSelected = false
    var operationCode = -1
    var textView: TextView? = null
    var firstNumber = 0
    var secondNumber = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as MainActivityInteraction
        } catch (castException: ClassCastException) {
            /** The activity does not implement the listener.  */
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)

        (requireActivity() as? MainActivityInteraction)?.openThirdBtn()

        firstNumber = listener?.firstNumber!!
        secondNumber = listener?.secondNumber!!

        operationCode = listener?.operationCode!!

        textView = binding.tvSelect

        binding.btn3.setOnClickListener {
            val fragment = FourthFragment()

            if (listener != null) {
                listener!!.setOperation(operationCode)
                fragmentManager?.beginTransaction()?.replace(R.id.container, fragment)
                    ?.addToBackStack("fourth")?.commit()
            }
        }

        binding.sum.setOnClickListener {
            operationCode = Constants.plusCode
            onOperate()
        }

        binding.minus.setOnClickListener {
            operationCode = Constants.minusCode
            onOperate()
        }

        binding.divide.setOnClickListener {
            operationCode = Constants.delCode
            onOperate()
        }

        binding.multiply.setOnClickListener {
            operationCode = Constants.umnCode
            onOperate()
        }

        binding.btnCancel3.setOnClickListener {
            (requireActivity() as? MainActivityInteraction)?.hideThirdBtn()
            activity?.onBackPressed()
        }
        onOperate()
        return binding.root
    }

    fun onOperate() {
        if (operationCode == -1) {
            isOperationSelected = false
            textView!!.text = "$firstNumber ? $secondNumber"
        } else if (operationCode == Constants.plusCode) {
            isOperationSelected = true
            textView!!.text = "$firstNumber + $secondNumber"
        } else if (operationCode == Constants.minusCode) {
            isOperationSelected = true
            textView!!.text = "$firstNumber - $secondNumber"
        } else if (operationCode == Constants.delCode) {
            isOperationSelected = true
            textView!!.text = "$firstNumber / $secondNumber"
        } else if (operationCode == Constants.umnCode) {
            isOperationSelected = true
            textView!!.text = "$firstNumber * $secondNumber"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}