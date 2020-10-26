package br.com.unicidapp.utils.extensions

import android.view.View
import com.facebook.shimmer.ShimmerFrameLayout

fun View.shouldShowView(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

fun View.shouldShowInvisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.INVISIBLE
}

fun ShimmerFrameLayout.shouldStartShimmer(isStart: Boolean) {
    shouldShowView(isStart)
    if (isStart) {
        startShimmer()
    } else {
        stopShimmer()
    }
}