package com.gomguk.kproject.content

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.gomguk.kproject.util.model.Document
import com.gomguk.kproject.util.view.loadImage
import kotlinx.android.synthetic.main.activity_content.*
import android.support.v4.app.NavUtils
import com.gomguk.kproject.R
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class ContentActivity : AppCompatActivity(), ContentContract.View {
    private val presenter: ContentContract.Presenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val data = presenter.getData(intent)

        setView(data)
    }

    private fun setView(data: Document) {
        titleText.text = data.title
        publisherText.text = data.publisher
        contentText.text = data.contents

        data.thumbnail?.let {
            image.loadImage(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}