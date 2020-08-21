package com.setung.android.newsapp.Activities.Storage

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.setung.android.newsapp.Activities.Detail.DetailActivity
import com.setung.android.newsapp.Model.Article
import com.setung.android.newsapp.R
import com.setung.android.newsapp.RecyclerView.RecyclerViewAdapter
import com.setung.android.newsapp.Room.AppDatabase
import com.setung.android.newsapp.Room.StorageNewsData
import kotlinx.android.synthetic.main.activity_storage.*

class StorageActivity : AppCompatActivity() {
    private lateinit var viewAdapter: RecyclerViewAdapter
    private lateinit var viewManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage)

        supportActionBar?.title = "NewsApp Storage"

        initRecyclerView()
        getStorageNewsData()

        storage_btn_clear.setOnClickListener {
            viewAdapter.apply {
                this.myDataset.clear()
                notifyDataSetChanged()

                Thread {
                    val db = AppDatabase.getDatabase(applicationContext)
                    db.storageNewsDataDao().deleteAll()
                }.start()

                Toast.makeText(applicationContext, "Clear", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun initRecyclerView() {

        storage_recycelerview.setHasFixedSize(true);

        viewManager = LinearLayoutManager(this)
        viewAdapter = RecyclerViewAdapter(View.OnClickListener {
            if (it.tag != null) {
                val position = it.tag as Int

                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("url", viewAdapter.myDataset[position].url)
                startActivity(intent)
            }
        }, View.OnLongClickListener {
            if (it.tag != null) {
                val position = it.tag as Int

                val countryList = arrayOf("Share", "Delete")
                val builder = AlertDialog.Builder(this)
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
                            Thread {
                                val db = AppDatabase.getDatabase(this)
                                db.storageNewsDataDao()
                                    .deleteArtice((viewAdapter.myDataset[position].url))

                                runOnUiThread {
                                    Toast.makeText(this, "deleted", Toast.LENGTH_SHORT).show()
                                    viewAdapter.myDataset.remove(viewAdapter.myDataset[position])
                                    viewAdapter.notifyDataSetChanged()
                                }

                            }.start()
                        }
                    }

                }
                builder.create().show()

            }
            true
        })

        storage_recycelerview.setHasFixedSize(true)
        storage_recycelerview.layoutManager = viewManager
        storage_recycelerview.adapter = viewAdapter
    }

    fun getStorageNewsData() {
        Thread {
            val db = AppDatabase.getDatabase(this)
            var list = db.storageNewsDataDao().getAll()

            list.reversed().forEach {
                var article = Article(it?.title ?: "", "", it?.url ?: "", it?.urlToImage ?: "")
                viewAdapter.myDataset.add(article)
            }

            runOnUiThread {
                viewAdapter.notifyDataSetChanged()
            }
        }.start()
    }
}