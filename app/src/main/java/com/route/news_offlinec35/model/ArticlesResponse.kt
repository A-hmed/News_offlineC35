package com.route.news_offlinec35.model

data class ArticlesResponse(
	val totalResults: Int? = null,
	val articles: List<ArticlesItem?>? = null,
	val status: String? = null
)

data class ArticlesItem(
	val publishedAt: String? = null,
	val author: String? = null,
	val urlToImage: String? = null,
	val description: String? = null,
	val source: SourcesItem? = null,
	val title: String? = null,
	val url: String? = null,
	val content: String? = null
)

