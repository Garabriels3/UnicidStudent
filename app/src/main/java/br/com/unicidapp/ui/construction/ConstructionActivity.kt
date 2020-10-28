package br.com.unicidapp.ui.construction

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.unicidapp.R
import br.com.unicidapp.databinding.ActivityConstructionBinding
import br.com.unicidapp.utils.extensions.hideKeyboard
import br.com.unicidapp.utils.extensions.setupToolbar

class ConstructionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConstructionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_construction)
        setupToolbar(binding.toolbar, true, title = getString(R.string.under_construction))
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

    companion object {

        fun start(context: Context) {
            context.startActivity(Intent(context, ConstructionActivity::class.java))
        }
    }
}