package uz.alpha.messenger.presentation.ui.screens

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableSharedFlow
import uz.alpha.messenger.R
import uz.alpha.messenger.databinding.ScreenAuthBinding

class AuthScreen : Fragment(R.layout.screen_auth) {

     lateinit var firebaseAuth  :  FirebaseAuth
    private lateinit var  googleSignIn : GoogleSignInClient
    private val binding by viewBinding(ScreenAuthBinding::bind)
    val signInGoogle = MutableSharedFlow<Unit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firebaseAuth= FirebaseAuth.getInstance()



    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



//        binding.btnAuth.setOnClickListener {
//            googleSignIn()
//        }

        binding.btnAuth.setOnClickListener {
            findNavController().navigate(AuthScreenDirections.actionAuthScreenToMainScreen())
        }


    }



    fun googleSignIn() {
   val google = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.ID ))
            .requestEmail()
            .build()

        googleSignIn = GoogleSignIn.getClient(requireActivity(), google)



        val signInIntent = googleSignIn.signInIntent
        startActivityForResult(signInIntent, 1)


    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == 1) {
//            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
//            try {
//                val account = task.getResult(ApiException::class.java)!!
//                firebaseAuthWithGoogle(account.idToken!!)
//            } catch (e: ApiException) {
//                Log.d("TTT", "eeeee: ${e.message.toString()}")
//            }
//        }
//    }
//
//    private fun firebaseAuthWithGoogle(idToken: String) {
//        val credential = GoogleAuthProvider.getCredential(idToken, null)
//        firebaseAuth.signInWithCredential(credential)
//            .addOnCompleteListener(requireActivity()) { task ->
//                if (task.isSuccessful) {
//                 findNavController().navigate(AuthScreenDirections.actionAuthScreenToMainScreen())
//                } else {
//                    Toast.makeText(requireContext(), "!!!!", Toast.LENGTH_SHORT).show()
//                }
//            }
//    }
}