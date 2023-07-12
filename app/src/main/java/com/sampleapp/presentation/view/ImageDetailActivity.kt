package com.sampleapp.presentation.view

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.sampleapp.R
import com.sampleapp.data.models.ImageModel
import com.sampleapp.databinding.ActivityImageDetailBinding
import com.sampleapp.presentation.utils.Constants
import com.squareup.picasso.Picasso

class ImageDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_detail)
        binding = ActivityImageDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialise()
        getDataFromBundle()
    }

    private fun initialise() {
        binding.toolbarLayout.ivBack.setOnClickListener { onBackPressed() }
    }


    private fun getDataFromBundle() {
        val bundle: Bundle? = intent.extras
        val imageModel: ImageModel? = bundle?.getParcelable(Constants.MODEL)
        Picasso.with(this).load(imageModel?.url).placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher).into(binding.ivMainImage)
        binding.tvTitle.text = imageModel?.title
    }


}

