package uz.alpha.messenger.presentation.ui.dialogs

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.alpha.messenger.R
import uz.alpha.messenger.databinding.DialogDeleteTaskBinding
class DeleteDialog:DialogFragment(R.layout.dialog_delete_task){


    private  val binding by viewBinding(DialogDeleteTaskBinding::bind)

    private var deleteListener: (() -> Unit)? = null

    fun setDeleteListener(block:()->Unit){
        deleteListener = block
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE,R.style.DialogStyle2)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnNo.setOnClickListener {
                dismiss()
            }

            btnYes.setOnClickListener {
                deleteListener?.invoke()
                dismiss()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val params = dialog!!.window!!.attributes
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.attributes = params
    }



}