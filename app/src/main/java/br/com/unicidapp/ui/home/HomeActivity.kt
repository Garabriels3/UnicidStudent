package br.com.unicidapp.ui.home

import android.content.Context
import android.content.Intent
import androidx.databinding.DataBindingUtil
import br.com.unicidapp.R
import br.com.unicidapp.databinding.ActivityHomeBinding
import br.com.unicidapp.ui.average.AverageActivity
import br.com.unicidapp.utils.base.BaseActivity
import br.com.unicidapp.utils.extensions.bind
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity() {

    override val viewModel: HomeViewModel by viewModel()
    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapter: HomeAdapter

    override fun onCreate() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        setupAdapter()
    }

    override fun setupScreen() {
    }

    override fun subscribeUi() {
        bind(viewModel.localMenuOptions, adapter::submitList)
        bind(viewModel.boletimClick) { AverageActivity.start(this) }
    }

    private fun setupAdapter() {
        adapter = HomeAdapter(
            viewModel::onHomeMenuClick
        )
        binding.rvMenu.adapter = adapter
    }

    companion object {

        fun start(context: Context) {
            context.startActivity(Intent(context, HomeActivity::class.java))
        }
    }
}