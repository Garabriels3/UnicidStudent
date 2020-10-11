package br.com.unicidapp.ui.addAverege

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import br.com.unicidapp.R
import br.com.unicidapp.databinding.ActivityAddAverageBinding

class AddAverageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddAverageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_average)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_average)
    }
}