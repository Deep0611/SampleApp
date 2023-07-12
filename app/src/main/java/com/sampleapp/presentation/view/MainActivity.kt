package com.sampleapp.presentation.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.sampleapp.data.models.ImageModel
import com.sampleapp.databinding.ActivityMainBinding
import com.sampleapp.presentation.utils.Constants
import com.sampleapp.presentation.viewModel.ImageViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ImageAdapter
    private var jokeList = ArrayList<ImageModel>()
    private val imageViewModel: ImageViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialise()
        setAdapter()
        initObservers()
    }

    private fun initialise() {
        imageViewModel.getRemoteImages();
    }

    private fun setAdapter() {
        adapter = ImageAdapter(this, jokeList)
        adapter.setOnClickListener(object : ImageAdapter.OnClickListener {
            override fun onClick(position: Int, model: ImageModel?) {
                val intent = Intent(this@MainActivity, ImageDetailActivity::class.java)
                intent.putExtra(Constants.MODEL, jokeList[position])
                startActivity(intent)
            }
        })
        binding.rvImages.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initObservers() {
        lifecycleScope.launch(Dispatchers.Main) {
            imageViewModel.imageList.observe(this@MainActivity, Observer {
                jokeList.clear()
                jokeList.addAll(it)
                adapter.notifyDataSetChanged()
            })
        }
    }
}