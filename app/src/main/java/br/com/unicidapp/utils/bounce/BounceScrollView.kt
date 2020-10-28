package br.com.unicidapp.utils.bounce

import android.content.Context
import android.util.AttributeSet
import android.widget.ScrollView
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper

class BounceScrollView(context: Context, attrs: AttributeSet) : ScrollView(context, attrs) {

    override fun onFinishInflate() {
        super.onFinishInflate()
        OverScrollDecoratorHelper.setUpOverScroll(this)
    }
}