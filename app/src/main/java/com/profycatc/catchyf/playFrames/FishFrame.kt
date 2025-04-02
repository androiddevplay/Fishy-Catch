package com.profycatc.catchyf.playFrames

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import com.profycatc.catchyf.MainActivity
import com.profycatc.catchyf.R
import com.profycatc.catchyf.databinding.FragmentFishFrameBinding

class FishFrame : Fragment() {

    private val bindFishesF:FragmentFishFrameBinding by lazy {
        FragmentFishFrameBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return bindFishesF.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        settleFishFr()
        chooseFishToPlay(2)
    }
    private fun settleFishFr(){
        bindFishesF.fishChoose1.setOnClickListener { chooseFishToPlay(1) }
        bindFishesF.fishChoose2.setOnClickListener { chooseFishToPlay(2) }
        bindFishesF.fishChoose3.setOnClickListener { chooseFishToPlay(3) }
        requireActivity().onBackPressedDispatcher.addCallback {
            MainActivity.startNextFr(requireContext(),LevelsFrame())
        }
        bindFishesF.fromFishPlayBtn.setOnClickListener {
            MainActivity.startNextFr(requireContext(),PlayFrame())
        }
        bindFishesF.fishBackBtn.setOnClickListener {
            MainActivity.startNextFr(requireContext(),LevelsFrame())
        }
    }
    private fun chooseFishToPlay(fishValPl:Int){
        MainActivity.fishNum=fishValPl
        when (fishValPl) {
            1 -> {
                bindFishesF.fishChoose1.alpha = 1.0f
                bindFishesF.fishChoose2.alpha = BgFrame.ALPHA_OFF
                bindFishesF.fishChoose3.alpha = BgFrame.ALPHA_OFF
            }

            2 -> {
                bindFishesF.fishChoose1.alpha = BgFrame.ALPHA_OFF
                bindFishesF.fishChoose2.alpha = 1.0f
                bindFishesF.fishChoose3.alpha = BgFrame.ALPHA_OFF
            }

            else -> {
                bindFishesF.fishChoose1.alpha = BgFrame.ALPHA_OFF
                bindFishesF.fishChoose2.alpha = BgFrame.ALPHA_OFF
                bindFishesF.fishChoose3.alpha = 1.0f
            }
        }
    }
}