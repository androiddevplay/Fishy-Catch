package com.profycatc.catchyf.openFrames

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.activity.addCallback
import androidx.lifecycle.lifecycleScope
import com.profycatc.catchyf.MainActivity
import com.profycatc.catchyf.R
import com.profycatc.catchyf.databinding.FragmentLoadFrameBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class LoadFrame : Fragment() {

    private val loadBinder:FragmentLoadFrameBinding by lazy {
        FragmentLoadFrameBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return loadBinder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startFishyLoad()
    }
    private fun startFishyLoad(){
        requireActivity().onBackPressedDispatcher.addCallback {
            Log.i("Fishy Load","Fishy Game on Load")
        }
        loadBinder.fishyLogoL.startAnimation(AnimationUtils.loadAnimation(
            requireActivity(),R.anim.vis_gone_anim
        ))
        lifecycleScope.launch {
            delay(3015)
            MainActivity.goToFrMenu(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        loadBinder.fishyLogoL.clearAnimation()
    }

}