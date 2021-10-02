package me.isachenko.shiftlabtest

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment

class GreetingsDialogFragment : AppCompatDialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): AlertDialog {
        val name = arguments?.getString("name")
        val surname = arguments?.getString("surname")
        val template = getString(R.string.hello_message_template)
        val buttonText = getString(R.string.dialog_button_text)
        val alertDialog = AlertDialog.Builder(context)
            .setMessage(String.format(template, surname, name))
            .setPositiveButton(buttonText) { _: DialogInterface, _: Int ->
                dialog?.dismiss()
            }.create()

        return alertDialog
    }
}
