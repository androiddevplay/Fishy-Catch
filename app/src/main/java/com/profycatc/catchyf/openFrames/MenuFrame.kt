package com.profycatc.catchyf.openFrames

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import com.ironsource.mediationsdk.IronSource
import com.profycatc.catchyf.FishyMusicCl
import com.profycatc.catchyf.MainActivity
import com.profycatc.catchyf.R
import com.profycatc.catchyf.databinding.FragmentMenuFrameBinding
import com.profycatc.catchyf.playFrames.BgFrame
import com.profycatc.catchyf.playFrames.FishInfoFrame
import com.profycatc.catchyf.playFrames.LevelsFrame
import com.unity3d.mediation.LevelPlay
import com.unity3d.mediation.LevelPlayAdError
import com.unity3d.mediation.LevelPlayAdInfo
import com.unity3d.mediation.LevelPlayConfiguration
import com.unity3d.mediation.LevelPlayInitError
import com.unity3d.mediation.LevelPlayInitListener
import com.unity3d.mediation.LevelPlayInitRequest
import com.unity3d.mediation.interstitial.LevelPlayInterstitialAd
import com.unity3d.mediation.interstitial.LevelPlayInterstitialAdListener


class MenuFrame : Fragment() {

    private val meniBind:FragmentMenuFrameBinding by lazy {
        FragmentMenuFrameBinding.inflate(layoutInflater)
    }
    private lateinit var fishyAd: LevelPlayInterstitialAd

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return meniBind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createMenuF()
        checkMusicSwitch()
    }
    private fun createMenuF(){
        requireActivity().onBackPressedDispatcher.addCallback {
            if (MainActivity.musicSt){
                requireActivity().stopService(FishyMusicCl.setMusic(requireContext()))
            }
            requireActivity().finishAndRemoveTask()
        }
        meniBind.musicChBtn.setOnClickListener {
            if (MainActivity.musicSt){
                requireActivity().stopService(FishyMusicCl.setMusic(requireContext()))
                MainActivity.musicSt=false
            }else{
                requireActivity().startService(FishyMusicCl.setMusic(requireContext()))
                MainActivity.musicSt=true
            }
            checkMusicSwitch()
        }
        meniBind.fishInfoBtn.setOnClickListener {
            MainActivity.startNextFr(requireContext(),FishInfoFrame())
        }
        meniBind.changeBgBtn.setOnClickListener {
            MainActivity.startNextFr(requireContext(),BgFrame())
        }
        meniBind.startBtn.setOnClickListener {
            MainActivity.startNextFr(requireContext(),LevelsFrame())
        }
        if (MainActivity.ironGo==18){
            createIronAd()
            MainActivity.ironGo=22
        }else{
            MainActivity.ironGo--
        }
    }
    private fun createIronAd(){
        if (!MainActivity.isInternetOn(requireContext())) {
            Log.i("Fishy_LOG", "No internet connection")
            return
        }
        val initRequest = LevelPlayInitRequest.Builder(getString(R.string.iron_key_main))
            .withLegacyAdFormats(listOf(LevelPlay.AdFormat.INTERSTITIAL))
            .build()

        LevelPlay.init(requireActivity(), initRequest, object : LevelPlayInitListener {
            override fun onInitSuccess(configuration: LevelPlayConfiguration) {
                Log.i("Fishy_LOG", "INIT SUCCESS")
                if (isAdded){
                    launchAd()
                }
            }

            override fun onInitFailed(error: LevelPlayInitError) {
                Log.i("Fishy_LOG", "Initialization failed: ${error.errorMessage}")
            }
        })
    }
    private fun launchAd(){
        fishyAd = LevelPlayInterstitialAd(getString(R.string.key_inter_set))
        fishyAd.setListener(object : LevelPlayInterstitialAdListener {
            override fun onAdLoaded(adInfo: LevelPlayAdInfo) {
                Log.i("Fishy_LOG", "Ad Loaded")
                if (fishyAd.isAdReady()) {
                    fishyAd.showAd(requireActivity())
                }
            }
            override fun onAdLoadFailed(error: LevelPlayAdError) {
                Log.i("Fishy_LOG", "Ad Load Failed: ${error.getErrorMessage()}")
            }
            override fun onAdDisplayed(adInfo: LevelPlayAdInfo) {}
            override fun onAdDisplayFailed(error: LevelPlayAdError, adInfo: LevelPlayAdInfo) {
                Log.i("Fishy_LOG", "Ad Display Failed: ${error.getErrorMessage()}")
            }
            override fun onAdClicked(adInfo: LevelPlayAdInfo) {}
            override fun onAdClosed(adInfo: LevelPlayAdInfo) {}
            override fun onAdInfoChanged(adInfo: LevelPlayAdInfo) {}
        })
        fishyAd.loadAd()
    }
    private fun checkMusicSwitch(){
        if (MainActivity.musicSt){
            meniBind.musicChBtn.setImageResource(R.drawable.music_on_btn)
        }else{
            meniBind.musicChBtn.setImageResource(R.drawable.music_off_btn)
        }
    }

    override fun onPause() {
        super.onPause()
        IronSource.onPause(requireActivity())
    }

    override fun onResume() {
        super.onResume()
        IronSource.onResume(requireActivity())
    }
}