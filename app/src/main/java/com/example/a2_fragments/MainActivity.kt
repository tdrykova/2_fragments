package com.example.a2_fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.a2_fragments.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainActivityInteraction {

    private lateinit var binding: ActivityMainBinding

    override var firstNumber: Int? = null
    override var secondNumber: Int? = null

    override var operationCode = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .replace(R.id.container, FirstFragment::class.java, null)
            .commit()

        binding.button2.visibility = View.INVISIBLE
        binding.button3.visibility = View.INVISIBLE
        binding.button4.visibility = View.INVISIBLE

        binding.button1.setOnClickListener {
            binding.button2.visibility = View.INVISIBLE
            binding.button3.visibility = View.INVISIBLE
            binding.button4.visibility = View.INVISIBLE

            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.container, FirstFragment::class.java, null)
                .commit()
        }

        binding.button2.setOnClickListener {
            binding.button3.visibility = View.INVISIBLE
            binding.button4.visibility = View.INVISIBLE

            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.container, SecondFragment::class.java, null)
                .commit()
        }

        binding.button3.setOnClickListener {
            binding.button4.visibility = View.INVISIBLE

            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.container, ThirdFragment::class.java, null)
                .commit()
        }
    }

    override fun setFirstNumber(i: Int) {
        firstNumber = i
    }

    override fun setSecondNumber(i: Int) {
        secondNumber = i
    }

    override fun setOperation(code: Int) {
        operationCode = code
    }

    override fun getAnswer(): Float {
        return if (operationCode == Constants.plusCode) (firstNumber!! + secondNumber!!).toFloat()
        else if (operationCode == Constants.minusCode) (firstNumber!! - secondNumber!!).toFloat()
        else if (operationCode == Constants.umnCode) (firstNumber!! * secondNumber!!).toFloat()
        else firstNumber!!.toFloat() / secondNumber!!.toFloat()
    }

    override fun openSecondBtn() {
        binding.button2.visibility = View.VISIBLE
    }

    override fun hideSecondBtn() {
        binding.button2.visibility = View.INVISIBLE
    }

    override fun openThirdBtn() {
        binding.button3.visibility = View.VISIBLE
    }

    override fun hideThirdBtn() {
        binding.button3.visibility = View.INVISIBLE
    }

    override fun openFourthBtn() {
        binding.button4.visibility = View.VISIBLE
    }

    override fun hideFourthBtn() {
        binding.button4.visibility = View.INVISIBLE
    }
}