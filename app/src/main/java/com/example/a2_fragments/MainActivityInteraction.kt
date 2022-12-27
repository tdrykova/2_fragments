package com.example.a2_fragments

interface MainActivityInteraction {

    var firstNumber: Int?
    var secondNumber: Int?

    fun setFirstNumber(i: Int)
    fun setSecondNumber(i: Int)

    val operationCode: Int
    fun setOperation(code: Int)

    fun getAnswer(): Float

    fun openSecondBtn()
    fun hideSecondBtn()

    fun openThirdBtn()
    fun hideThirdBtn()

    fun openFourthBtn()
    fun hideFourthBtn()
}