package com.profycatc.catchyf.playFrames

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.profycatc.catchyf.MainActivity
import com.profycatc.catchyf.databinding.FragmentBgFrameBinding


class BgFrame : Fragment() {

    private val bindBgFr: FragmentBgFrameBinding by lazy {
        FragmentBgFrameBinding.inflate(layoutInflater)
    }

    companion object Const {
        const val ALPHA_OFF = 0.52f
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return bindBgFr.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        settleBgFr()
        changeBgIn(1)
    }

    private fun settleBgFr() {
        requireActivity().onBackPressedDispatcher.addCallback {
            MainActivity.goToFrMenu(requireContext())
        }
        bindBgFr.bgBackBtn.setOnClickListener {
            MainActivity.goToFrMenu(requireContext())
        }
        bindBgFr.bgBtn1.setOnClickListener { changeBgIn(1) }
        bindBgFr.bgBtn2.setOnClickListener { changeBgIn(2) }
        bindBgFr.bgBtn3.setOnClickListener { changeBgIn(3) }

    }

    private fun changeBgIn(valBgIn: Int) {
        MainActivity.bgNum = valBgIn
        when (valBgIn) {
            1 -> {
                bindBgFr.bgBtn1.alpha = 1.0f
                bindBgFr.bgBtn2.alpha = ALPHA_OFF
                bindBgFr.bgBtn3.alpha = ALPHA_OFF
            }

            2 -> {
                bindBgFr.bgBtn1.alpha = ALPHA_OFF
                bindBgFr.bgBtn2.alpha = 1.0f
                bindBgFr.bgBtn3.alpha = ALPHA_OFF
            }

            else -> {
                bindBgFr.bgBtn1.alpha = ALPHA_OFF
                bindBgFr.bgBtn2.alpha = ALPHA_OFF
                bindBgFr.bgBtn3.alpha = 1.0f
            }
        }
    }
}