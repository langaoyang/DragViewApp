package org.alan.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var startX = 0f
    var startY = 0f
    var measuredHeight = 0;
    var measuredWidth = 0;
    val TAG = "MainActivity"
    var mLastX : Int = 0
    var mLastY : Int = 0
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        dragView.setOnTouchListener(View.OnTouchListener { v, event ->
            if (v != dragView) {
               return@OnTouchListener false
            }
            val x = event.rawX.toInt()
            val y = event.rawY.toInt()

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    mLastX =event.rawX.toInt()
                    mLastY = event.rawY.toInt()
                    startX = event.rawX
                    startY = event.rawY
                    measuredHeight = tagetView.measuredHeight
                    measuredWidth = tagetView.measuredWidth

                }

                MotionEvent.ACTION_MOVE -> {

                    var cx = event.rawX - startX
                    var cy = event.rawY - startY
                    Log.e(TAG, "$cx     ======  $cy")

                    val deltaX: Int = x - mLastX
                    val deltaY: Int = y - mLastY
                    dragView.translationX = dragView.translationX +deltaX
                    dragView.translationY = dragView.translationY +deltaY

//                    ViewHelper.setTranslationX(this, (ViewHelper.getTranslationX(this) + deltaX))
//                    ViewHelper.setTranslationY(this, (ViewHelper.getTranslationY(this) + deltaY))

                    mLastX = x;
                    mLastY = y;
                    tagetView.layoutParams.let {
                        it.height = measuredHeight + cy.toInt()

                        it.width = measuredWidth + cx.toInt()
                        tagetView.layoutParams = it
                    }

                }

            }
            true

        })


    }
}