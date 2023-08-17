package com.kevser.isearch.util

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.MediaPlayer.OnErrorListener
import android.media.MediaPlayer.OnPreparedListener
import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MusicPlayer @Inject constructor() : OnPreparedListener, OnErrorListener {
    private val mediaPlayer = MediaPlayer()
    private var isPlaying: Boolean = false
    private var currentPlayingUrl: String? = null
    private var isPrepared: Boolean = false

    companion object {
        const val TAG = "MusicPlayer"
    }

    init {
        mediaPlayer.setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build()
        )
    }

    fun playMusic(url: String?) {
        if (url.isNullOrEmpty()) {
            Log.e(TAG, "URL is empty.")
            return
        }
        if (url == currentPlayingUrl && isPrepared) {
            mediaPlayer.start()
            isPlaying = true
        } else {
            currentPlayingUrl = url
            isPrepared = false
            mediaPlayer.reset()
            mediaPlayer.setDataSource(url)
            mediaPlayer.setOnPreparedListener(this)
            mediaPlayer.setOnErrorListener(this)
            mediaPlayer.prepareAsync()
        }
    }

    override fun onPrepared(mp: MediaPlayer?) {
        mp?.start()
        isPlaying = true
        isPrepared = true
    }

    fun getCurrentUrl(): String? {
        return currentPlayingUrl
    }

    override fun onError(mp: MediaPlayer?, what: Int, extra: Int): Boolean {
        Log.e(TAG, "Error code: $what, Extra: $extra")
        return true
    }

    fun pauseMusic() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            isPlaying = false
        }
    }

    fun replayMusic() {
        mediaPlayer.seekTo(0)
        mediaPlayer.start()
        Log.d(TAG, "MusicPlayer replayed.")
    }

    fun isPlaying(): Boolean {
        return mediaPlayer.isPlaying
    }
}