package br.com.unicidapp.ui.home

import android.content.Context
import android.content.Intent
import androidx.databinding.DataBindingUtil
import br.com.domain.entity.User
import br.com.unicidapp.R
import br.com.unicidapp.databinding.ActivityHomeBinding
import br.com.unicidapp.ui.construction.ConstructionActivity
import br.com.unicidapp.ui.average.AverageActivity
import br.com.unicidapp.ui.login.LoginActivity
import br.com.unicidapp.utils.base.BaseActivity
import br.com.unicidapp.utils.extensions.bind
import br.com.unicidapp.utils.extensions.shouldShowInvisible
import br.com.unicidapp.utils.extensions.shouldStartShimmer
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity() {

    override val viewModel: HomeViewModel by viewModel()
    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapter: HomeAdapter

    override fun onCreate() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.viewModel = viewModel
        setupAdapter()
    }

    override fun onBackPressed() {
        moveTaskToBack(false)
    }

    override fun setupScreen() {}

    override fun subscribeUi() {
        bind(viewModel.localMenuOptions, adapter::submitList)
        bind(viewModel.boletimClick) { AverageActivity.start(this) }
        bind(viewModel.userInfo, ::setUserName)
        bind(viewModel.isSignOut) { if (it) LoginActivity.start(this) }
        bind(viewModel.loading, ::setupShimmers)
        bind(viewModel.newsClick) { if (it) ConstructionActivity.start(this) }
        bind(viewModel.contactClick) { if (it) ConstructionActivity.start(this) }
    }

    private fun setupAdapter() {
        adapter = HomeAdapter(
            viewModel::onHomeMenuClick
        )
        binding.rvMenu.adapter = adapter
    }

    private fun setUserName(user: User) {
        binding.tvName.text = user.name
        binding.tvRgm.text = user.rgm

    }

    private fun setupShimmers(isLoading: Boolean) {
        binding.slLoading.shouldStartShimmer(isLoading)
        binding.tvName.shouldShowInvisible(!isLoading)
        binding.tvRgm.shouldShowInvisible(!isLoading)
        binding.ivProfile.shouldShowInvisible(!isLoading)
    }

    companion object {

        fun start(context: Context) {
            context.startActivity(Intent(context, HomeActivity::class.java))
        }
    }
}