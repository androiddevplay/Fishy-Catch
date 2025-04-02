package com.profycatc.catchyf

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.profycatc.catchyf.openFrames.MenuFrame

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    companion object{
        var musicSt=false
        var bgNum=1
        var fishNum=1
        var levelNum=1
        fun isInternetOn(contNe:Context): Boolean {
            val connectivityManager = contNe.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = connectivityManager.activeNetworkInfo
            return activeNetwork?.isConnected == true
        }
        var ironGo=18
        var requiredToCatch=3
        var timeToCatch=600
        fun goToFrMenu(context: Context){
            (context as FragmentActivity).supportFragmentManager.beginTransaction()
                .setCustomAnimations(android.R.animator.fade_in,android.R.animator.fade_out)
                .replace(R.id.main_fishy_c,MenuFrame()).commit()
        }
        fun startNextFr(contextN: Context,frNextS:Fragment){
            (contextN as FragmentActivity).supportFragmentManager.beginTransaction()
                .setCustomAnimations(android.R.animator.fade_in,android.R.animator.fade_out)
                .replace(R.id.main_fishy_c,frNextS).commit()
        }
    }
}