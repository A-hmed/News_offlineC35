package com.route.news_offlinec35

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.route.news_offlinec35.api.ApiManager
import com.route.news_offlinec35.model.ArticlesItem
import com.route.news_offlinec35.model.ArticlesResponse
import com.route.news_offlinec35.model.SourcesItem
import com.route.news_offlinec35.model.SourcesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var tabLayout: TabLayout
    lateinit var progressBar: ProgressBar
    var adapter : ArticlesAdapter = ArticlesAdapter()
    lateinit var articlesRecyclerView: RecyclerView
    //var art:List<ArticlesItem?>? = null
    var sources:List<SourcesItem?>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        getSources()


    }

    private fun initViews() {
        tabLayout = findViewById(R.id.tab_layout)
        progressBar = findViewById(R.id.progress_bar)
        articlesRecyclerView = findViewById(R.id.articles_recycler_view)
        articlesRecyclerView.adapter = adapter
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                // val source = sources!!.get(tab!!.position)
                val source:SourcesItem = tab!!.tag as SourcesItem
                loadArticles(source.id!!)
                articlesRecyclerView.layoutManager!!.scrollToPosition(0)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                val source:SourcesItem = tab!!.tag as SourcesItem
                loadArticles(source.id!!)
                articlesRecyclerView.layoutManager!!.scrollToPosition(0)
            }
        })
    }

    private fun loadArticles(sourceId:String) {
        progressBar.isVisible = true
     ApiManager.getApis().getArticles(
         apiKey = Constants.API_KEY,
         sourceId = sourceId
     ).enqueue(object :Callback<ArticlesResponse>{
         override fun onResponse(
             call: Call<ArticlesResponse>,
             response: Response<ArticlesResponse>
         ) {
             progressBar.isVisible = false
             adapter.items = response.body()!!.articles
             adapter.notifyDataSetChanged()
         }

         override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
             Log.e("onFailure",t.localizedMessage)
             progressBar.isVisible = false
             Toast.makeText(baseContext, "Failed to load news",Toast.LENGTH_LONG).show()
         }
     })
    }

    fun getSources(){
     ApiManager.getApis().getSources(Constants.API_KEY).enqueue(
         object: Callback<SourcesResponse>{
             override fun onResponse(
                 call: Call<SourcesResponse>,
                 response: Response<SourcesResponse>
             ) {
                 progressBar.isVisible = false
                sources = response.body()!!.sources
                 showTabs()
             }

             override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                 progressBar.isVisible = false
                 Toast.makeText(baseContext, "Failed to load Sources",Toast.LENGTH_LONG).show()
                 Log.e("onFailure",t.localizedMessage)
             }
         }
     )
    }

    fun showTabs(){
        for(i in 0 until sources!!.size){
            val tab = tabLayout.newTab()
            tab.text = sources!!.get(i)!!.name
            tab.tag =  sources!!.get(i)
            tabLayout.addTab(tab)
        }
    }
}
