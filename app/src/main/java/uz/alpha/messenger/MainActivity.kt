package uz.alpha.messenger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavGraph
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private  val fa=FirebaseAuth.getInstance()

    private lateinit var host: NavHostFragment
    private lateinit var graph: NavGraph

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//
//
//        host = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
//        graph = host.navController.navInflater.inflate(R.navigation.nav_graph)
//
//
//        if(fa.currentUser!=null){
//            graph.setStartDestination(R.id.mainScreen)
//            host.navController.graph = graph
//        }
//        else{
//            graph.setStartDestination(R.id.authScreen)
//            host.navController.graph = graph
//        }
//


        Log.d("TTT", "onCreate: ")
    }


    override fun onStart() {
        super.onStart()

        Log.d("TTT", "onStart: ")

    }

    override fun onResume() {
        super.onResume()
        Log.d("TTT", "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d("TTT", "onPause: ")
    }

    override fun onStop() {
        super.onStop()

        Log.d("TTT", "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("TTT", "onDestroy: ")

    }
}