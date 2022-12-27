package com.example.a2_fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a2_fragments.databinding.FragmentFirstBinding
import com.example.a2_fragments.databinding.FragmentSecondBinding
import java.lang.ClassCastException

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        if (listener!!.secondNumber != null) binding.et2.setText(listener!!.secondNumber.toString())

        (requireActivity() as? MainActivityInteraction)?.openSecondBtn()

        binding.btn2.setOnClickListener {

            val fragment = ThirdFragment()

            if (listener != null) {
                listener!!.setSecondNumber(binding.et2.text.toString().toInt())

                fragmentManager?.beginTransaction()?.replace(R.id.container, fragment)?.addToBackStack("second")
                    ?.commit()

            }

        }
        binding.btnCancel2.setOnClickListener {
            (requireActivity() as? MainActivityInteraction)?.hideSecondBtn()
            activity?.onBackPressed()
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}