package br.com.unicidapp.ui.average

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import br.com.unicidapp.R
import br.com.unicidapp.databinding.ActivityAverageBinding
import br.com.unicidapp.ui.average.addAverage.AddAverageActivity
import br.com.unicidapp.utils.base.BaseActivity
import br.com.unicidapp.utils.extensions.bind
import br.com.unicidapp.utils.extensions.hideKeyboard
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
        bind(viewModel.goToAddAverage) { AddAverageActivity.start(this, REQUEST_CODE) }
        bind(viewModel.listAddAverage) { adapter.submitList(it) }
        bind(viewModel.goToDetailsAddAverage) { AddAverageActivity.start(this, REQUEST_CODE, it) }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                hideKeyboard()
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupAdapters() {
        adapter = AverageAdapter(viewModel::onHomeMenuClick)
        binding.rvAverageList.adapter = adapter
    }

    companion object {
        private const val REQUEST_CODE = 2

        fun start(context: Context) {
            context.startActivity(Intent(context, AverageActivity::class.java))
        }
    }
}