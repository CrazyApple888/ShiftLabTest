package me.isachenko.shiftlabtest

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import me.isachenko.shiftlabtest.databinding.FragmentGreetingsBinding

class GreetingsFragment : Fragment() {

    private lateinit var binding: FragmentGreetingsBinding
    private val arguments: GreetingsFragmentArgs by navArgs()
    private var name: String = ""
    private var surname: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences =
            context?.getSharedPreferences(getString(R.string.preferences_key), Context.MODE_PRIVATE)
                ?: return
        name = sharedPreferences.getString(
            getString(R.string.saved_name_key),
            getString(R.string.default_name_key)
        )
            .toString()
        surname = sharedPreferences.getString(
            getString(R.string.saved_surname_key),
            getString(R.string.default_surname_key)
        )
            .toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGreetingsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (arguments.name.isNotEmpty() && arguments.surname.isNotEmpty()) {
            name = arguments.name
            surname = arguments.surname
        }
        binding.greetingsButton.setOnClickListener {
            val dialogFragment = GreetingsDialogFragment()
            val bundle = Bundle()
            bundle.putString("name", name)
            bundle.putString("surname", surname)
            dialogFragment.arguments = bundle

            dialogFragment.show(parentFragmentManager, "GreetingsDialog")
        }
    }

    override fun onPause() {
        super.onPause()
        val editor =
            context?.getSharedPreferences(getString(R.string.preferences_key), Context.MODE_PRIVATE)
                ?: return
        with(editor.edit()) {
            putString(getString(R.string.saved_name_key), arguments.name)
            putString(getString(R.string.saved_surname_key), arguments.surname)
            apply()
        }
    }
}