package me.isachenko.shiftlabtest

import androidx.lifecycle.ViewModel

class RegistrationViewModel : ViewModel() {

    private val nameRegex = Regex("^[a-zA-Zа-яА-Я]{1,30}")
    private val passwordRegex = Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,30}$")

    private var isNameCorrect = false
    private var isSurnameCorrect = false
    private var isDateOfBirthCorrect = true
    private var isPasswordCorrect = false
    private var isRepeatPasswordCorrect = false

    fun isRepeatPasswordCorrect(password: String, repeatPassword: String): Boolean {
        isRepeatPasswordCorrect = (isPasswordCorrect(password) && password == repeatPassword)
        return isRepeatPasswordCorrect
    }

    fun isPasswordCorrect(password: String): Boolean {
        isPasswordCorrect = passwordRegex.matches(password)
        return isPasswordCorrect
    }

    fun isNameCorrect(name: String): Boolean {
        isNameCorrect = nameRegex.matches(name)
        return isNameCorrect
    }

    fun isSurnameCorrect(name: String): Boolean {
        isSurnameCorrect = nameRegex.matches(name)
        return isSurnameCorrect
    }

    fun isAllCorrect() =
        isNameCorrect && isSurnameCorrect && isDateOfBirthCorrect && isPasswordCorrect && isRepeatPasswordCorrect
}