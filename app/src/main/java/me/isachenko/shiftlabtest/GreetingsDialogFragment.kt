package me.isachenko.shiftlabtest

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment

class GreetingsDialogFragment : AppCompatDialogFragment() {

    //TODO extract strings to xml
    override fun onCreateDialog(savedInstanceState: Bundle?): AlertDialog {
        val name = arguments?.getString("name")
        val surname = arguments?.getString("surname")
        val alertDialog = AlertDialog.Builder(context)
            .setMessage("Hello, $surname $name!")
            .setPositiveButton("Hey!") { _: DialogInterface, _: Int ->
                dialog?.dismiss()
            }.create()

        return alertDialog
    }
}
