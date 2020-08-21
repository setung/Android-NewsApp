package com.setung.android.newsapp.Activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.setung.android.newsapp.Activities.Fragment.Adpater.PagerAdapter
import com.setung.android.newsapp.MetaData
import com.setung.android.newsapp.R
import com.setung.android.newsapp.Activities.Storage.StorageActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var sharedPref: SharedPreferences
    var backClickTime: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getPreferencesData()

        setViewPager()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        val menuCountry = menu?.findItem(R.id.menu_language)
        menuCountry!!.setOnMenuItemClickListener {

            val countryList =
                arrayOf("South Korea", "United States", "China", "Japan", "United Kingdom")
            val languageList = arrayOf("kr", "us", "cn", "jp", "gb")

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Language")
            builder.setItems(countryList) { dialogInterface, i ->

                MetaData.NEWS_COUNTRY = languageList[i]
                sharedPref?.edit()?.putString("lan", languageList[i])?.commit()

                Toast.makeText(this, countryList[i], Toast.LENGTH_SHORT).show()

                // viewPager를 굳이 새로 만드는 이유
                // 국적 변경시 어중간한 위치에 있는 프래그먼트의 리사이클러뷰는 뉴스기사가 안바뀜
                // 약간 어색하지만 뷰페이져를 변경된 국적의 기사로 초기화 하는게 깔끔함.
                setViewPager()
                supportActionBar?.title = "NewsApp (${countryList[i]})"
            }
            builder.create().show()

            true
        }

        val menuStorage = menu?.findItem(R.id.menu_storage)
        menuStorage!!.setOnMenuItemClickListener {

            val intent = Intent(this, StorageActivity::class.java)
            startActivity(intent)

            true
        }

        return super.onCreateOptionsMenu(menu)
    }

    fun setViewPager() {
        viewPager.adapter = PagerAdapter(this)
        val tabLayoutMediator = TabLayoutMediator(tabLayout, viewPager,
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

    fun getPreferencesData() {
        sharedPref = this?.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            MetaData.NEWS_COUNTRY = sharedPref?.getString("lan", "kr") ?: "kr"
        }
    }

    override fun onBackPressed() {
        val time1 = System.currentTimeMillis()
        val time2 = time1 - backClickTime
        if (time2 in 0..2000) {
            finish()
        } else {
            backClickTime = time1
            Toast.makeText(
                applicationContext,
                "Press one more time and it'll be over.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}
