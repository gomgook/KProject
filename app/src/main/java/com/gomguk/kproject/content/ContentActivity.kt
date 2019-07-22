package com.gomguk.kproject.content

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gomguk.kproject.R
import com.gomguk.kproject.util.model.Document
import kotlinx.android.synthetic.main.activity_content.*

class ContentActivity : AppCompatActivity(), ContentContract.View {
    override lateinit var presenter: ContentPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)

        presenter = ContentPresenter(ContentRepository.getInstance(), this)
        val data = presenter.getData(intent)

        setView(data)
    }

    private fun setView(data: Document) {
        text.text = data.contents
    }
}