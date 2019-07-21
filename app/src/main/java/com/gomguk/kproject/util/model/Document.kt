package com.gomguk.kproject.util.model

data class Document(val authors: List<String>,
                    val contents: String,
                    val isbn: String,
                    val price: Int,
                    val publisher: String,
                    val thumbnail: String,
                    val title: String,
                    val url: String)