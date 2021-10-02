package me.isachenko.shiftlabtest

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import me.isachenko.shiftlabtest.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding
    private lateinit var viewModel: RegistrationViewModel

    private lateinit var nameEditText: TextInputEditText
    private lateinit var surnameEditText: TextInputEditText
    private lateinit var dateOfBirthEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var repeatPasswordEditText: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initEditText()

        nameEditText.setOnFocusChangeListener { view: View, hasFocus: Boolean ->
            checkName(view, hasFocus)
        }
        surnameEditText.setOnFocusChangeListener { view: View, hasFocus: Boolean ->
            checkSurname(view, hasFocus)
        }
        passwordEditText.setOnFocusChangeListener { view: View, hasFocus: Boolean ->
            checkPassword(view, hasFocus)
        }
        repeatPasswordEditText.setOnFocusChangeListener { view: View, hasFocus: Boolean ->
            checkRepeatPassword(view, hasFocus)
        }

        val watcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                binding.registerButton.isEnabled = viewModel.isAllCorrect()
                Log.i("ABOBA", viewModel.isAllCorrect().toString())
            }
        }

        nameEditText.addTextChangedListener(watcher)
        surnameEditText.addTextChangedListener(watcher)
        dateOfBirthEditText.addTextChangedListener(watcher)
        passwordEditText.addTextChangedListener(watcher)
        repeatPasswordEditText.addTextChangedListener(watcher)
    }

    private fun initEditText() {
        nameEditText = binding.nameEditText
        surnameEditText = binding.surnameEditText
        dateOfBirthEditText = binding.dateOfBirthEditText
        passwordEditText = binding.passwordEditText
        repeatPasswordEditText = binding.repeatPasswordEditText
    }

    private fun checkName(view: View, hasFocus: Boolean) {
        (view.parent.parent as TextInputLayout).error =
            if (viewModel.isNameCorrect((view as TextInputEditText).text.toString())) {
                null
            } else {
                if (!hasFocus) {
                    "Only letters allowed"
                } else {
                    null
                }
            }
    }

    private fun checkSurname(view: View, hasFocus: Boolean) {
        (view.parent.parent as TextInputLayout).error =
            if (viewModel.isSurnameCorrect((view as TextInputEditText).text.toString())) {
                null
            } else {
                if (!hasFocus) {
                    "Only letters allowed"
                } else {
                    null
                }
            }
    }

    private fun checkPassword(view: View, hasFocus: Boolean) {
        (view.parent.parent as TextInputLayout).error =
            if (viewModel.isPasswordCorrect((view as TextInputEditText).text.toString())) {
                null
            } else {
                if (!hasFocus) {
                    "At least 8 symbols, lower and uppercase letters and number"
                } else {
                    null
                }
            }
    }

    private fun checkRepeatPassword(view: View, hasFocus: Boolean) {
        (view.parent.parent as TextInputLayout).error =
            if (viewModel.isRepeatPasswordCorrect(
                    passwordEditText.text.toString(),
                    repeatPasswordEditText.text.toString()
                )
            ) {
                null
            } else {
                if (!hasFocus) {
                    "Passwords not the same"
                } else {
                    null
                }
            }
    }
}