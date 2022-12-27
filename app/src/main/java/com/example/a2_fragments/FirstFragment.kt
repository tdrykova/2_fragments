package com.example.a2_fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a2_fragments.databinding.FragmentFirstBinding
import java.lang.ClassCastException

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

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
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        if (listener!!.firstNumber != null) binding.et1.setText(listener!!.firstNumber.toString())

        binding.btn1.setOnClickListener {

            val fragment = SecondFragment()

            if (listener != null) {
                listener!!.setFirstNumber(binding.et1.text.toString().toInt())

                fragmentManager?.beginTransaction()?.replace(R.id.container, fragment)?.addToBackStack("first")
                    ?.commit()
            }

        }

        binding.btnCancel.setOnClickListener {
            activity?.onBackPressed()
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}