package com.setung.android.newsapp.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.setung.android.newsapp.Detail.DetailActivity
import com.setung.android.newsapp.Fragment.RecyclerView.RecyclerViewAdapter
import com.setung.android.newsapp.MetaData
import com.setung.android.newsapp.Model.NewsData
import com.setung.android.newsapp.R
import com.setung.android.newsapp.Retrofit.NewsRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllFragment(var category: String = "") : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeView: SwipeRefreshLayout
    private lateinit var viewAdapter: RecyclerViewAdapter
    private lateinit var viewManager: LinearLayoutManager

    companion object {
        lateinit var currentFragment: AllFragment
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_all, container, false)
        recyclerView = v.findViewById(R.id.recyclerView)
        swipeView = activity!!.findViewById(R.id.swipe_view)

        initRecyclerView()
        setSwipeViewRefresh()
        getNewsData()
        return v
    }

    fun getNewsData() {
        NewsRetrofit.getService().requestNews(country = MetaData.NEWS_COUNTRY, category = category)
            .enqueue(object : Callback<NewsData> {
                override fun onFailure(call: Call<NewsData>, t: Throwable) {}

                override fun onResponse(call: Call<NewsData>, response: Response<NewsData>) {
                    if (response.isSuccessful) {
                        val news = response.body()!!.articles

                        viewAdapter.myDataset = news
                        viewAdapter.notifyDataSetChanged()

                        for (n in news) {
                            Log.d("news", n.title)
                        }
                    }
                }
            })
    }

    fun initRecyclerView() {

        recyclerView.setHasFixedSize(true);

        viewManager = LinearLayoutManager(context)
        viewAdapter = RecyclerViewAdapter(View.OnClickListener {
            if (it.tag != null) {
                val position = it.tag as Int

                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("url", viewAdapter.myDataset[position].url)
                startActivity(intent)
            }
        }, View.OnLongClickListener {
            if (it.tag != null) {
                val position = it.tag as Int

                val countryList = arrayOf("Share", "Save")
                val builder = AlertDialog.Builder(context!!)
                builder.setTitle("Choose Option")
                builder.setItems(countryList) { dialogInterface, i ->

                    when (i) {
                        0 -> {
                            //공유 인텐트
                            val intent = Intent(Intent.ACTION_SEND)
                            intent.type = "text/plain"
                            intent.putExtra(Intent.EXTRA_TEXT, viewAdapter.myDataset[position].url)
                            startActivity(Intent.createChooser(intent, "Share News"))
                        }
                        1 -> {
                            Toast.makeText(context, "${countryList[i]} Saved", Toast.LENGTH_SHORT).show()

                        }
                    }


                }
                builder.create().show()


            }
            true
        })

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = viewManager
        recyclerView.adapter = viewAdapter
    }

    fun setSwipeViewRefresh() {
        swipeView.setDistanceToTriggerSync(800)
        swipeView.setOnRefreshListener {
            Toast.makeText(context, "refresh", Toast.LENGTH_SHORT).show()
            getNewsData()
            swipeView.isRefreshing = false
        }
    }


}