package uz.alpha.messenger.presentation.ui.screens

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.alpha.messenger.BuildConfig
import uz.alpha.messenger.R
import uz.alpha.messenger.data.room.entity.ContactEntity
import uz.alpha.messenger.databinding.ScreenMainBinding
import uz.alpha.messenger.presentation.ui.adapters.ContactAdapter
import uz.alpha.messenger.presentation.ui.dialogs.AddContactDialog
import uz.alpha.messenger.presentation.ui.dialogs.DeleteDialog
import uz.alpha.messenger.presentation.ui.dialogs.UpdateDialog
import uz.alpha.messenger.presentation.vm.impl.ContactViewModelImpl

class MainScreen : Fragment(R.layout.screen_main) {

    private val binding by viewBinding(ScreenMainBinding::bind)
    private val adapter by lazy { ContactAdapter() }
    private val viewModel by viewModels<ContactViewModelImpl>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            requireActivity().finish()
        }


        binding.contactRV.adapter = adapter
        binding.contactRV.layoutManager = LinearLayoutManager(requireContext())


        /**
         * NavigationView
         * */
        binding.navigationView.setNavigationItemSelectedListener {

            val light = view.findViewById<ImageView>(R.id.btnSun)
            val dark = view.findViewById<ImageView>(R.id.btnMoon)
            val clicked=false

           light.setOnClickListener {
               light.visibility=View.INVISIBLE
               dark.visibility=View.VISIBLE
               AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
           }

            dark.setOnClickListener {
                dark.visibility=View.INVISIBLE
                light.visibility=View.VISIBLE
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }






            when (it.itemId) {
                R.id.newContact -> {
                   showDialogOne()
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                }
                R.id.allContacts -> {

                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                }
                R.id.share -> {
                    try {
                        val shareIntent = Intent(Intent.ACTION_SEND)
                        shareIntent.type = "text/plain"
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name")
                        var shareMessage = "\nLet me recommend you this application\n\n"
                        shareMessage =
                            """
                            ${shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID}
                            
                            
                            """.trimIndent()
                        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                        startActivity(Intent.createChooser(shareIntent, "choose one"))
                    } catch (e: Exception) {
                        //e.toString();
                    }
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                }
                R.id.call ->{
                    Toast.makeText(requireContext(), "call", Toast.LENGTH_SHORT).show()
                }
                R.id.settings ->{
                    Toast.makeText(requireContext(), "chat settings", Toast.LENGTH_SHORT).show()
                }
                R.id.chatInfo ->{
                    Toast.makeText(requireContext(), "chat information", Toast.LENGTH_SHORT).show()
                }
                R.id.exit_app -> {
                    requireActivity().finish()
                }
            }
            return@setNavigationItemSelectedListener true
        }


        binding.btnMenu.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }


        binding.btnAddContact.setOnClickListener {
            viewModel.addClick()
        }

        adapter.setOnDeleteClickListener {
            showDeleteDialog(it)
        }

        adapter.setOnEditListener {
            showUpdateDialog(it)
        }


        adapter.setOnListener {
            findNavController().navigate(MainScreenDirections.actionMainScreenToChatScreen())
        }


        viewModel.openAddContactFlow.onEach {
            showDialogOne()
        }.launchIn(viewLifecycleOwner.lifecycleScope)


        viewModel.getAllFlow.onEach {
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)


    }

    fun showDialogOne() {

        val dialog = AddContactDialog()

        dialog.show(childFragmentManager, "")
    }

    private fun showDeleteDialog(contact: ContactEntity) {
        val dialog = DeleteDialog()
        dialog.setDeleteListener {
            viewModel.deleteContact(contact)
        }
        dialog.show(childFragmentManager, "")
    }

    fun showUpdateDialog(contact: ContactEntity) {

        val dialog = UpdateDialog(contact)

        dialog.show(childFragmentManager, "")
    }


}