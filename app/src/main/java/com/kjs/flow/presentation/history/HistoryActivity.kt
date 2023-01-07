package com.kjs.flow.presentation.history

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.kjs.flow.R
import com.kjs.flow.databinding.ActivityHistoryBinding
import com.kjs.flow.presentation.common.CommonActivity
import com.kjs.flow.presentation.history.adapter.HistoryAdapter
import com.kjs.flow.util.decoration.RecyclerViewVerticalSpaceDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryActivity : CommonActivity() {
    companion object {
        const val SEARCH_KEYWORD = "SEARCH_KEYWORD"
    }

    private val binding by lazy { ActivityHistoryBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<HistoryViewModel>()

    private val adapter by lazy {
        HistoryAdapter(
            object : HistoryAdapter.HistoryListener {
                override fun selectKeyword(keyword: String) {
                    val intent = Intent().apply {
                        putExtra(SEARCH_KEYWORD, keyword)
                    }
                    setResult(RESULT_OK, intent)
                    finish()
                }
            }
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()
        bindViews()

        viewModel.getHistoryKeyword()
    }

    override fun initViews() {
        initRecyclerView()
    }

    private fun initRecyclerView() = with(binding) {
        historyRecyclerView.adapter = adapter
        historyRecyclerView.itemAnimator = null
        historyRecyclerView.addItemDecoration(RecyclerViewVerticalSpaceDecoration(10))
    }

    override fun bindViews() {
        viewModel.historyList.observe(this) {
            it?.let {
                adapter.submitList(it)
            }
        }

        viewModel.error.observe(this) { errorMsg ->
            errorMsg?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        viewModel.clearHolder()
    }
}