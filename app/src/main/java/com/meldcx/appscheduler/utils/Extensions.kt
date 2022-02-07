package com.meldcx.test.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.webkit.WebView
import android.widget.Toast
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

val Context.inputMethodManager: InputMethodManager
    get() = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

val View.inputMethodManager: InputMethodManager
    get() = this.context.inputMethodManager

val View.dismissKeyboard: Boolean
    get() = this.inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)

val Context.layoutInflater: LayoutInflater
    get() = LayoutInflater.from(this)

val View.layoutInflater: LayoutInflater
    get() = this.context.layoutInflater

fun Context.toast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, text, duration).show()

fun Context.toast(resId: Int, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, resId, duration).show()

@Throws(Exception::class)
suspend fun WebView.captureScreenshot(ioDispatcher: CoroutineDispatcher): Bitmap {
    requestLayout()
    val makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
    measure(makeMeasureSpec, makeMeasureSpec)
    try {
        return withContext(ioDispatcher) {
            val config = Bitmap.Config.RGB_565
            val screenBitmap: Bitmap? =
                Bitmap.createBitmap(measuredWidth, measuredHeight, config)
            if (screenBitmap != null) {
                screenBitmap.setHasAlpha(false)
                screenBitmap.prepareToDraw()
                val canvas = Canvas(screenBitmap)
                draw(canvas)
                screenBitmap
            } else throw  NullPointerException()
        }
    } catch (exception: Exception) {
        throw  exception
    }
}

fun generateImageFilename() = "${System.currentTimeMillis()}.jpeg"

@SuppressLint("SimpleDateFormat")
fun getCurrentDateTime(): String {
    val dateFormat = SimpleDateFormat("yyyy:MM:dd HH:mm:ss")
    return dateFormat.format(Calendar.getInstance().time)
}
