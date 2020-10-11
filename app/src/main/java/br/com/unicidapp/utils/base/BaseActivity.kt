package br.com.unicidapp.utils.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    abstract val viewModel: BaseViewModel

    abstract fun onCreate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
        onCreate()
        setupScreen()
        subscribeUi()
    }

    /** Setup de views que nao dependem de ViewModels*/
    abstract fun setupScreen()

    /** LiveDatas*/
    abstract fun subscribeUi()

    override fun onDestroy() {
        lifecycle.removeObserver(viewModel)
        super.onDestroy()
    }
}