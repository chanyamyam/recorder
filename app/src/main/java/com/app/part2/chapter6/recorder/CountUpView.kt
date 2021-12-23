package com.app.part2.chapter6.recorder

import android.content.Context
import android.os.SystemClock
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class CountUpView(
    context:Context,
    attrs: AttributeSet? = null
): AppCompatTextView(context,attrs) {

    private var startTimestamp: Long = 0L

    private val countUpAction: Runnable = object: Runnable {
        override fun run() {
            val currentTimeStamp = SystemClock.elapsedRealtime()

            currentTimeStamp - startTimestamp
            val countTimeSeconds = ((currentTimeStamp - startTimestamp)/1000L).toInt()
            updateCountTime(countTimeSeconds)

            handler?.postDelayed(this,1000L)
        }
    }

    fun startCountUp() {
        startTimestamp = SystemClock.elapsedRealtime()
        handler?.post(countUpAction)
    }
    fun stopCountUp() {
        handler?.removeCallbacks(countUpAction)
    }
    private fun updateCountTime(countTimeSeconds: Int) {
        val minutes = countTimeSeconds / 60
        val seconds = countTimeSeconds % 60

        text = "%02d:%02d".format(minutes,seconds)
    }
}