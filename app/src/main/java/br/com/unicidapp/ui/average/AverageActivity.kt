package br.com.unicidapp.ui.average

import androidx.databinding.DataBindingUtil
import br.com.unicidapp.R
import br.com.unicidapp.databinding.ActivityAverageBinding
import br.com.unicidapp.utils.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class  AverageActivity : BaseActivity() {

    private lateinit var binding: ActivityAverageBinding
    private lateinit var adapter: AverageAdapter
    override val viewModel: AverageViewModel by viewModel()

    override fun onCreate() {
        setContentView(R.layout.activity_average)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_average)
        setupAdapters()
    }

    override fun setupScreen() {
        TODO("Not yet implemented")
    }

    override fun subscribeUi() {
        TODO("Not yet implemented")
    }

    private fun setupAdapters() {
        adapter = AverageAdapter()
        binding.rvAverageList.adapter = adapter
    }
}