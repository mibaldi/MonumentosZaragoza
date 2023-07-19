package com.mibaldi.monumentoszaragoza.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import androidx.activity.viewModels
import com.mibaldi.monumentoszaragoza.databinding.ActivityDetailBinding
import com.mibaldi.monumentoszaragoza.ui.common.launchAndCollect
import dagger.hilt.android.AndroidEntryPoint
import com.mibaldi.monumentoszaragoza.ui.common.errorToString

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private val viewModel: DetailViewModel by viewModels()
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.monumentoDetailToolbar)
        binding.monumentoDetailToolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
            finish()
        }
        val monumentoId = intent.extras?.getInt("monumentoId")?: 0
        if (monumentoId != -1) viewModel.getMonumento(monumentoId)
        launchAndCollect(viewModel.state){
            binding.error = it.error?.let(::errorToString)
            binding.loading = it.loading
            if (it.monumento != null) {
                binding.monumento = it.monumento
                val spannedText = Html.fromHtml(it.monumento.description, Html.FROM_HTML_MODE_COMPACT)
                binding.monumentoDescription.text =spannedText
                binding.monumentoDescription.movementMethod = android.text.method.LinkMovementMethod.getInstance()
            }
        }
    }
}