package com.setung.android.newsapp

import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.setung.android.newsapp.Fragment.Adpater.PagerAdapter
import com.setung.android.newsapp.Fragment.AllFragment
import com.setung.android.newsapp.Model.NewsData
import com.setung.android.newsapp.Retrofit.NewsRetrofit
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var sharedPref : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPref = this?.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            MetaData.NEWS_COUNTRY= sharedPref?.getString("lan","kr") ?:"kr"
        }


        setViewPager()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu,menu)

        val menuItem = menu?.findItem(R.id.menu_language)
        menuItem!!.setOnMenuItemClickListener {

            val lan = arrayOf("South Korea","United States","China","Japan","United Kingdom")
            val lan2 = arrayOf("kr","us","cn","jp","gb")



            val builder = AlertDialog.Builder(this)
            builder.setTitle("Language")
            builder.setItems(lan) { dialogInterface, i ->

                MetaData.NEWS_COUNTRY=lan2[i]
                sharedPref?.edit()?.putString("lan",lan2[i])?.commit()

                Toast.makeText(this,lan[i],Toast.LENGTH_SHORT).show()

                AllFragment.getNewsData()
            }
            builder.create().show()

            true
        }

        return super.onCreateOptionsMenu(menu)
    }

    fun setViewPager() {
        viewPager.adapter = PagerAdapter(this)
        val tabLayoutMediator = TabLayoutMediator(
            tabLayout,
            viewPager,
            TabLayoutMediator.TabConfigurationStrategy() { tab: TabLayout.Tab, position: Int ->

                when (position) {
                    0 -> {
                        tab.text = "All"
                        tab.setIcon(R.drawable.ic_confirm)
                    }

                    1 -> {
                        tab.text = "Business"
                        tab.setIcon(R.drawable.ic_business)
                    }
                    2 -> {
                        tab.text = "Entertainment"
                        tab.setIcon(R.drawable.ic_entertainment)
                    }
                    3 -> {
                        tab.text = "Health"
                        tab.setIcon(R.drawable.ic_health)
                    }
                    4 -> {
                        tab.text = "Science"
                        tab.setIcon(R.drawable.ic_science)
                    }
                    5 -> {
                        tab.text = "Sports"
                        tab.setIcon(R.drawable.ic_sports)
                    }
                    6 -> {
                        tab.text = "Technology"
                        tab.setIcon(R.drawable.ic_technology)
                    }
                }
            })
        tabLayoutMediator.attach()
    }
}
