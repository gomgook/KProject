package com.gomguk.kproject.content

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.gomguk.kproject.util.model.Document
import com.gomguk.kproject.util.view.loadImage
import kotlinx.android.synthetic.main.activity_content.*
import android.support.v4.app.NavUtils
import com.gomguk.kproject.R


class ContentActivity : AppCompatActivity(), ContentContract.View {
    override lateinit var presenter: ContentPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        presenter = ContentPresenter(ContentRepository.getInstance(), this)
        val data = presenter.getData(intent)

        setView(data)
    }

    private fun setView(data: Document) {
        text.text = data.contents

        data.thumbnail?.let {
            image.loadImage(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}