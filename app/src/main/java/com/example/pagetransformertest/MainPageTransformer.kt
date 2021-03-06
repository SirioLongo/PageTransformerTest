package com.example.pagetransformertest

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import java.lang.NullPointerException
import kotlin.math.abs
import kotlin.math.sign
import kotlin.math.sqrt

class MainPageTransformer : ViewPager2.PageTransformer {
    private var iconView: ImageView? = null
    private var nameView: TextView? = null
    private var panView: TextView? = null
    private var dateView: TextView? = null
    private var cvcView: TextView? = null

    override fun transformPage(page: View, position: Float) {
        iconView = page.findViewById<ImageView>(R.id.iconView)
        nameView = page.findViewById<TextView>(R.id.nameView)
        panView = page.findViewById<TextView>(R.id.panView)
        dateView = page.findViewById<TextView>(R.id.dateView)
        cvcView = page.findViewById<TextView>(R.id.cvcView)

        when {
            abs(position) <= 1 -> {
                val pageTranslation = page.width * position
                // The common scale factor will decrease as the page approaches the center of the screen
                // like a square root function(from both directions)
                val scaleFactor = sign(position) * sqrt(abs(position))

                // Each scale factor will increase as the page approaches the center (the card will become
                // bigger) like a square root function
                val scaleFactorX = 1 - 0.9f * abs(scaleFactor)
                val scaleFactorY = 1 - 0.5f * abs(scaleFactor)

                // Each card will be at 0 degrees in the center, at 180 or -180 when at the side
                val rotation = 180 * position

                counterBalancePageSlide(pageTranslation)
                applyRotationToCardImage(rotation)
                applyScalingToCardImage(scaleFactorX, scaleFactorY)
                handleCardImageBacksideTransparency()
            }
        }
    }

    private fun handleCardImageBacksideTransparency() {
        try {
            if (iconView!!.rotationY < -90 || iconView!!.rotationY > 90) {
                iconView!!.visibility = View.INVISIBLE
            } else {
                iconView!!.visibility = View.VISIBLE
            }
        } catch (npe: NullPointerException) {
            // Failed to get a reference to the iconView
        }
    }

    private fun applyRotationToCardImage(rotation: Float) {
        iconView?.rotationY = rotation
    }

    private fun counterBalancePageSlide(
        pageTranslation: Float
    ) {
        iconView?.translationX = - pageTranslation
        nameView?.translationX = - pageTranslation
        panView?.translationX = - pageTranslation
        dateView?.translationX = - pageTranslation
        cvcView?.translationX = - pageTranslation
    }

    private fun applyScalingToCardImage(scaleFactorX: Float, scaleFactorY: Float) {
        iconView?.scaleX = scaleFactorX
        iconView?.scaleY = scaleFactorY
    }
}