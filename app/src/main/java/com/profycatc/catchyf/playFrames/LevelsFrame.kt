package com.profycatc.catchyf.playFrames

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import com.profycatc.catchyf.MainActivity
import com.profycatc.catchyf.R
import com.profycatc.catchyf.databinding.FragmentLevelsFrameBinding
import com.profycatc.catchyf.playFrames.BgFrame.Const.ALPHA_OFF

class LevelsFrame : Fragment() {

    private val bindLevelsF: FragmentLevelsFrameBinding by lazy {
        FragmentLevelsFrameBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return bindLevelsF.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        makeLevelsFr()
        settleLevel(1)
    }

    private fun settleLevel(valLevelI: Int) {
        when (valLevelI) {
            1 -> {
                MainActivity.timeToCatch = 600
                MainActivity.requiredToCatch = 3
                MainActivity.levelNum=1
                bindLevelsF.level1Btn.alpha = 1.0f
                bindLevelsF.level2Btn.alpha = ALPHA_OFF
                bindLevelsF.level3Btn.alpha = ALPHA_OFF
                bindLevelsF.level4Btn.alpha = ALPHA_OFF
                bindLevelsF.level5Btn.alpha = ALPHA_OFF
            }

            2 -> {
                MainActivity.timeToCatch = 500
                MainActivity.requiredToCatch = 4
                MainActivity.levelNum=2
                bindLevelsF.level1Btn.alpha = ALPHA_OFF
                bindLevelsF.level2Btn.alpha = 1.0f
                bindLevelsF.level3Btn.alpha = ALPHA_OFF
                bindLevelsF.level4Btn.alpha = ALPHA_OFF
                bindLevelsF.level5Btn.alpha = ALPHA_OFF
            }

            3 -> {
                MainActivity.timeToCatch = 400
                MainActivity.requiredToCatch = 5
                MainActivity.levelNum=3
                bindLevelsF.level1Btn.alpha = ALPHA_OFF
                bindLevelsF.level2Btn.alpha = ALPHA_OFF
                bindLevelsF.level3Btn.alpha = 1.0f
                bindLevelsF.level4Btn.alpha = ALPHA_OFF
                bindLevelsF.level5Btn.alpha = ALPHA_OFF
            }

            4 -> {
                MainActivity.timeToCatch = 300
                MainActivity.requiredToCatch = 6
                MainActivity.levelNum=4
                bindLevelsF.level1Btn.alpha = ALPHA_OFF
                bindLevelsF.level2Btn.alpha = ALPHA_OFF
                bindLevelsF.level3Btn.alpha = ALPHA_OFF
                bindLevelsF.level4Btn.alpha = 1.0f
                bindLevelsF.level5Btn.alpha = ALPHA_OFF
            }

            else -> {
                MainActivity.timeToCatch = 200
                MainActivity.requiredToCatch = 7
                MainActivity.levelNum=5
                bindLevelsF.level1Btn.alpha = ALPHA_OFF
                bindLevelsF.level2Btn.alpha = ALPHA_OFF
                bindLevelsF.level3Btn.alpha = ALPHA_OFF
                bindLevelsF.level4Btn.alpha = ALPHA_OFF
                bindLevelsF.level5Btn.alpha = 1.0f
            }
        }
    }

    private fun makeLevelsFr() {
        requireActivity().onBackPressedDispatcher.addCallback {
            MainActivity.goToFrMenu(requireContext())
        }
        bindLevelsF.levelsBackBtn.setOnClickListener {
            MainActivity.goToFrMenu(requireContext())
        }
        bindLevelsF.level1Btn.setOnClickListener { settleLevel(1) }
        bindLevelsF.level2Btn.setOnClickListener { settleLevel(2) }
        bindLevelsF.level3Btn.setOnClickListener { settleLevel(3) }
        bindLevelsF.level4Btn.setOnClickListener { settleLevel(4) }
        bindLevelsF.level5Btn.setOnClickListener { settleLevel(5) }
        bindLevelsF.levelsNextBtn.setOnClickListener {
            MainActivity.startNextFr(requireContext(),FishFrame())
        }
    }
}