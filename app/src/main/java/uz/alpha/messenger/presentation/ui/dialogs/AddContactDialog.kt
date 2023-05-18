package uz.alpha.messenger.presentation.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import uz.alpha.messenger.R
import uz.alpha.messenger.data.room.entity.ContactEntity
import uz.alpha.messenger.databinding.DialogAddContactBinding
import uz.alpha.messenger.presentation.vm.impl.ContactViewModelImpl
import uz.alpha.messenger.utils.getCurrentDate

class AddContactDialog : BottomSheetDialogFragment() {

    private val binding by viewBinding(DialogAddContactBinding::bind)
    private val viewModel by viewModels<ContactViewModelImpl>()
    override fun getTheme() = R.style.NoBackgroundDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = View.inflate(requireContext(), R.layout.dialog_add_contact, null)
        view.setBackgroundResource(R.drawable.shapebottomsheet)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fullName = binding.txtInputFullName.text.toString()
        val phone = binding.txtInputPhoneNumber.text.toString()


        binding.btnAdd.setOnClickListener {
            if (binding.txtInputFullName.text.isNotEmpty()) {
                if (binding.txtInputPhoneNumber.text.isNotEmpty()) {
                    viewModel.insertContact(ContactEntity(0, binding.txtInputFullName.text.toString(), getCurrentDate(), binding.txtInputPhoneNumber.text.toString()    ))
                    dismiss()
                } else {
                    Toast.makeText(requireContext(), "number !!!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "full name !!!", Toast.LENGTH_SHORT).show()
            }
        }



    }

}