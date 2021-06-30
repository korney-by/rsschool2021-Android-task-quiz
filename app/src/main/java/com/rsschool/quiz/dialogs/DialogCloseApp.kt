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
            builder.setMessage(R.string.close_app_question)
                .setCancelable(true)
                .setPositiveButton(R.string.button_exit) { _, _ -> exitProcess(0) }
                .setNegativeButton(R.string.button_cancel, null)
            builder.create()

        } ?: throw IllegalStateException("Activity cannot be null")
    }
}