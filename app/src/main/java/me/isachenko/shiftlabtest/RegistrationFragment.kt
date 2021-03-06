package me.isachenko.shiftlabtest

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.material.textfield.TextInputEditText
import me.isachenko.shiftlabtest.databinding.FragmentRegistrationBinding
import java.util.*

class RegistrationFragment : Fragment(), DatePickerDialog.OnDateSetListener {

    private lateinit var binding: FragmentRegistrationBinding
    private lateinit var viewModel: RegistrationViewModel

    private lateinit var nameEditText: TextInputEditText
    private lateinit var surnameEditText: TextInputEditText
    private lateinit var dateOfBirthEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var repeatPasswordEditText: TextInputEditText

    private lateinit var dateErrorMessage: String
    private lateinit var nameSurnameErrorMessage: String
    private lateinit var passwordErrorMessage: String
    private lateinit var repeatPasswordErrorMessage: String

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
        initErrorMessages()
        initEditText()
        setListeners()

        binding.registerButton.setOnClickListener {
            val action =
                RegistrationFragmentDirections.actionRegistrationFragmentToGreetingsFragment(
                    nameEditText.text.toString(), surnameEditText.text.toString()
                )
            view.findNavController().navigate(action)
        }
    }

    private fun showDatePicker() {
        val instance = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            this.requireContext(),
            this,
            instance.get(Calendar.YEAR),
            instance.get(Calendar.MONTH),
            instance.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
        datePickerDialog.show()
    }

    private fun initEditText() {
        nameEditText = binding.nameEditText
        surnameEditText = binding.surnameEditText
        dateOfBirthEditText = binding.dateOfBirthEditText
        passwordEditText = binding.passwordEditText
        repeatPasswordEditText = binding.repeatPasswordEditText
    }

    private fun initErrorMessages() {
        dateErrorMessage = getString(R.string.date_error)
        nameSurnameErrorMessage = getString(R.string.name_surname_error)
        passwordErrorMessage = getString(R.string.password_error)
        repeatPasswordErrorMessage = getString(R.string.repeat_password_error)
    }

    private fun setListeners() {
        val watcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                binding.registerButton.isEnabled = viewModel.isAllCorrect()
            }
        }

        nameEditText.addTextChangedListener { checkName() }
        surnameEditText.addTextChangedListener { checkSurname() }
        dateOfBirthEditText.addTextChangedListener { }
        passwordEditText.addTextChangedListener { checkPassword() }
        repeatPasswordEditText.addTextChangedListener { checkRepeatPassword() }

        nameEditText.addTextChangedListener(watcher)
        surnameEditText.addTextChangedListener(watcher)
        dateOfBirthEditText.addTextChangedListener(watcher)
        passwordEditText.addTextChangedListener(watcher)
        repeatPasswordEditText.addTextChangedListener(watcher)

        dateOfBirthEditText.setOnClickListener {
            showDatePicker()
        }
    }

    private fun checkDate() {
        binding.dateOfBirth.error =
            if (viewModel.isDateOfBirthCorrect(dateOfBirthEditText.text.toString())) {
                null
            } else {
                dateErrorMessage
            }
    }

    private fun checkName() {
        binding.name.error =
            if (viewModel.isNameCorrect(nameEditText.text.toString())) {
                null
            } else {
                nameSurnameErrorMessage
            }
    }

    private fun checkSurname() {
        binding.surname.error =
            if (viewModel.isSurnameCorrect(surnameEditText.text.toString())) {
                null
            } else {
                nameSurnameErrorMessage
            }
    }

    private fun checkPassword() {
        binding.password.error =
            if (viewModel.isPasswordCorrect(passwordEditText.text.toString())) {
                null
            } else {
                passwordErrorMessage
            }
    }

    private fun checkRepeatPassword() {
        binding.repeatPassword.error =
            if (viewModel.isRepeatPasswordCorrect(
                    passwordEditText.text.toString(),
                    repeatPasswordEditText.text.toString()
                )
            ) {
                null
            } else {
                repeatPasswordErrorMessage
            }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val template = getString(R.string.date_template)
        val dayString = if (dayOfMonth < 10) {
            "0$dayOfMonth"
        } else {
            dayOfMonth.toString()
        }
        val monthString = if (month < 10) {
            "0$month"
        } else {
            month.toString()
        }
        dateOfBirthEditText.setText(
            String.format(
                template,
                dayString,
                monthString,
                year.toString()
            )
        )
        checkDate()
    }
}