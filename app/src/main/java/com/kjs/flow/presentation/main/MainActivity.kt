package com.kjs.flow.presentation.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.kjs.flow.R
import com.kjs.flow.databinding.ActivityMainBinding
import com.kjs.flow.presentation.common.CommonActivity
import com.kjs.flow.presentation.history.HistoryActivity
import com.kjs.flow.presentation.main.adapter.MovieAdapter
import com.kjs.flow.util.decoration.RecyclerViewVerticalSpaceDecoration
import com.kjs.model.movie.MovieModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : CommonActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<MainViewModel>()

    private val adapter by lazy {
        MovieAdapter(
            object : MovieAdapter.MovieListener {
                override fun moveToDetail(item: MovieModel) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.webLink))
                    startActivity(intent)
                }
            }
        )
    }

    private val historyActivityRegister = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            it.data?.getStringExtra(HistoryActivity.SEARCH_KEYWORD)?.let { keyword ->
                binding.keywordEditText.setText(keyword)
                binding.keywordEditText.setSelection(keyword.length)
                viewModel.searchMovie(keyword, getString(R.string.error_input_search_keyword))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()
        bindViews()
    }

    override fun initViews() {
        initEditText()
        initButtons()
        initRecyclerView()
    }

    private fun initEditText() = with(binding) {
        keywordEditText.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(
                textView: TextView?,
                actionId: Int,
                keyEvent: KeyEvent?
            ): Boolean {
                when (actionId) {
                    EditorInfo.IME_ACTION_SEARCH -> {
                        viewModel.searchMovie(
                            keywordEditText.text.toString(),
                            getString(R.string.error_input_search_keyword)
                        )
                        return true
                    }
                }

                return false
            }
        })
    }

    private fun initButtons() = with(binding) {
        searchButton.setOnClickListener {
            viewModel.searchMovie(
                keywordEditText.text.toString(),
                getString(R.string.error_input_search_keyword)
            )
        }

        historyButton.setOnClickListener {
            val intent = Intent(this@MainActivity, HistoryActivity::class.java)
            historyActivityRegister.launch(intent)
        }
    }

    private fun initRecyclerView() = with(binding) {
        recyclerView.adapter = adapter
        recyclerView.itemAnimator = null
        recyclerView.addItemDecoration(RecyclerViewVerticalSpaceDecoration(10))

        adapter.addLoadStateListener {
            val refreshState = it.refresh
            if (refreshState is LoadState.Error) {
                Toast.makeText(
                    this@MainActivity,
                    "${refreshState.error.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun bindViews() {
        lifecycleScope.launchWhenStarted {
            viewModel.movieList.collectLatest {
                adapter.submitData(it)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.error.collectLatest {
                if (it.isEmpty())
                    return@collectLatest

                Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        viewModel.clearHolder()
    }

}