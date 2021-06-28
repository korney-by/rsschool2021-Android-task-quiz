package com.rsschool.quiz.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.rsschool.quiz.R
import kotlin.system.exitProcess

class DialogCloseApp : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.dialog_alert)
                .setMessage(R.string.close_app_question)
                .setCancelable(true)
                .setPositiveButton(R.string.button_yes) { _, _ -> exitProcess(0) }
                .setNegativeButton(R.string.button_no, null)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}