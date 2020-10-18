package br.com.unicidapp.ui.average

import android.content.Context
import android.content.Intent
import androidx.databinding.DataBindingUtil
import br.com.unicidapp.R
import br.com.unicidapp.databinding.ActivityAverageBinding
import br.com.unicidapp.ui.average.addAverage.AddAverageActivity
import br.com.unicidapp.utils.base.BaseActivity
import br.com.unicidapp.utils.extensions.bind
import br.com.unicidapp.utils.extensions.setupToolbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class AverageActivity : BaseActivity() {

    private lateinit var binding: ActivityAverageBinding
    private lateinit var adapter: AverageAdapter
    override val viewModel: AverageViewModel by viewModel()

    override fun onCreate() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_average)
        binding.viewModel = viewModel
        setupAdapters()
    }

    override fun setupScreen() {
        setupToolbar(
            binding.toolbar,
            showHome = true,
            title = getString(R.string.boletim_title_bar)
        )
    }

    override fun subscribeUi() {
        bind(viewModel.goToAddAverage) { AddAverageActivity.start(this) }
    }

    private fun setupAdapters() {
        adapter = AverageAdapter()
        binding.rvAverageList.adapter = adapter
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, AverageActivity::class.java))
        }
    }
}