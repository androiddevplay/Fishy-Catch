package com.profycatc.catchyf

import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import java.lang.UnsupportedOperationException

class FishyMusicCl:Service() {
    override fun onBind(p0: Intent?): IBinder? {
        throw UnsupportedOperationException("Fishy Music Fail")
    }

    override fun onCreate() {
        super.onCreate()
        playerFi=MediaPlayer.create(this,R.raw.fishy_back_sound)
        playerFi.setVolume(0.64f,0.64f)
        playerFi.isLooping=true
    }
    companion object{
        fun setMusic(contextMu:Context):Intent{
            return Intent(contextMu,FishyMusicCl::class.java)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        playerFi.stop()
        playerFi.release()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        playerFi.start()
        return START_STICKY;
    }
    private lateinit var playerFi:MediaPlayer
}