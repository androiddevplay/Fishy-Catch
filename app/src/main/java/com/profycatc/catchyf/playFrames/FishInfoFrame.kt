package com.profycatc.catchyf.playFrames

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import com.profycatc.catchyf.MainActivity
import com.profycatc.catchyf.R
import com.profycatc.catchyf.databinding.FragmentFishInfoFrameBinding


class FishInfoFrame : Fragment() {

    private val bindInfoFi:FragmentFishInfoFrameBinding by lazy {
        FragmentFishInfoFrameBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return bindInfoFi.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        settleFishyInfo()
    }
    private fun settleFishyInfo(){
        requireActivity().onBackPressedDispatcher.addCallback {
            MainActivity.goToFrMenu(requireContext())
        }
    }
}