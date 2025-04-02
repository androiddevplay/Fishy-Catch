package com.profycatc.catchyf.playFrames

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.profycatc.catchyf.MainActivity
import com.profycatc.catchyf.R
import com.profycatc.catchyf.databinding.FragmentPlayFrameBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random


class PlayFrame : Fragment() {

    private val bindPlayF:FragmentPlayFrameBinding by lazy {
        FragmentPlayFrameBinding.inflate(layoutInflater)
    }

    private var caughtFishV=0
    private val timerPlay:CountDownTimer by lazy {
        object :CountDownTimer(26000,100){
            override fun onTick(p0: Long) {
                if (isAdded){
                    bindPlayF.timeLevelTv.text= String.format(getString(R.string.time),p0/1000)
                }
            }

            override fun onFinish() {
                clearAllF()
                bindPlayF.winLooseScr.visibility=View.VISIBLE
                if (caughtFishV>=MainActivity.requiredToCatch){
                    bindPlayF.winLooseScr.background=ContextCompat.getDrawable(requireContext(),R.drawable.win_scr)
                }else{
                    bindPlayF.winLooseScr.background=ContextCompat.getDrawable(requireContext(),R.drawable.loose_scr)
                }
            }

        }
    }
    private fun clearAllF(){
        timerChill.cancel()
        timerGo.cancel()
        bindPlayF.fishCont1.clearAnimation()
        bindPlayF.fishCont2.clearAnimation()
        bindPlayF.fishCont3.clearAnimation()
        bindPlayF.fishImg1.clearAnimation()
        bindPlayF.fishImg2.clearAnimation()
        bindPlayF.fishImg3.clearAnimation()
    }
    private val timerGo:CountDownTimer by lazy {
        object :CountDownTimer(1800,100){
            override fun onTick(p0: Long) {}
            override fun onFinish() {
                if (isAdded){
                    timerChill.start()
                }
            }

        }
    }
    private val timerChill:CountDownTimer by lazy {
        object :CountDownTimer(500,100){
            override fun onTick(p0: Long) {}
            override fun onFinish() {
                if (isAdded){
                    timerGo.start()
                    val timeToHide=800+MainActivity.timeToCatch
                    val imgValueSet= Random.nextInt(3)+1
                    when(imgValueSet){
                        1->moveFish(bindPlayF.fishImg1,bindPlayF.fishCont1)
                        2->moveFish(bindPlayF.fishImg2,bindPlayF.fishCont2)
                        else->moveFish(bindPlayF.fishImg3,bindPlayF.fishCont3)
                    }
                    lifecycleScope.launch {
                        delay(800)
                        bindPlayF.catchBtn.visibility=View.VISIBLE
                    }
                    lifecycleScope.launch {
                        delay(timeToHide.toLong())
                        bindPlayF.catchBtn.visibility=View.INVISIBLE
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return bindPlayF.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCaughtFish()
        setRequiredFish()
        settleFishGame()
    }
    private fun setCaughtFish(){
        bindPlayF.cauFishTv.text= String.format(getString(R.string.сau),caughtFishV)
    }
    private fun setRequiredFish(){
        bindPlayF.requiredSighTv.text= String.format(getString(R.string.req),MainActivity.requiredToCatch)
    }
    private fun settleFishGame(){
        requireActivity().onBackPressedDispatcher.addCallback {
            timerPlay.cancel()
            clearAllF()
            MainActivity.goToFrMenu(requireContext())
        }
        bindPlayF.catchBtn.setOnClickListener {
            bindPlayF.catchBtn.visibility=View.INVISIBLE
            caughtFishV++
            startCa()
            setCaughtFish()
        }
        val levelIm=when(MainActivity.levelNum){
            1->R.drawable.level_1_btn
            2->R.drawable.level_2_btn
            3->R.drawable.level_3_btn
            4->R.drawable.level_4_btn
            else->R.drawable.level_5_btn
        }
        bindPlayF.playLevelImg.setImageResource(levelIm)
        val bgIm=when(MainActivity.bgNum){
            1->R.drawable.bg_scr_1
            2->R.drawable.bg_scr_2
            else->R.drawable.bg_scr_3
        }
        bindPlayF.playScrM.background=ContextCompat.getDrawable(requireContext(),bgIm)
        bindPlayF.winLooseScr.setOnClickListener {
            MainActivity.goToFrMenu(requireContext())
        }
        timerPlay.start()
        timerChill.start()
    }
    private fun startCa(){
        if (isAdded){
            if (MainActivity.musicSt){
                val playerGo=MediaPlayer.create(requireContext(),R.raw.fish_catch_reel_sound)
                playerGo.isLooping=false
                playerGo.setVolume(0.91f,0.91f)
                playerGo.start()
                playerGo.setOnCompletionListener {
                    playerGo.stop()
                    playerGo.release()
                }
            }
        }
    }
    private fun moveFish(imgFish:ImageView,relFish:RelativeLayout){
        if (isAdded){
            val imgValueR=when(MainActivity.fishNum){
                1->R.drawable.fish_just_1
                2->R.drawable.fish_just_2
                else->R.drawable.fish_just_3
            }
            imgFish.setImageResource(imgValueR)
            lifecycleScope.launch {
                delay(800)
                val imgShineF=when(MainActivity.fishNum){
                    1->R.drawable.fish_shine_1
                    2->R.drawable.fish_shine_2
                    else->R.drawable.fish_shine_3
                }
                imgFish.setImageResource(imgShineF)
            }
            relFish.visibility=View.VISIBLE
            imgFish.startAnimation(AnimationUtils.loadAnimation(requireContext(),R.anim.anim_shake_fish))
            val startMoveAnim = ValueAnimator.ofFloat(-2.0f, 2.0f)
            startMoveAnim.interpolator = LinearInterpolator()
            startMoveAnim.setDuration(1800L)

            startMoveAnim.addUpdateListener { animation: ValueAnimator ->
                val moveProgress =
                    animation.animatedValue as Float
                val moveWidth: Float = relFish.width.toFloat()
                val moveTranslationX =
                    moveWidth * moveProgress
                relFish.translationX=moveTranslationX
            }
            startMoveAnim.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    if (relFish.isVisible){
                        relFish.visibility=View.INVISIBLE
                    }
                }
            })
            startMoveAnim.start()
        }
    }
}