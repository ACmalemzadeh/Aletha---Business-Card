package ca.aletha.businesscard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main)
        // Find the view pager that will allow the user to swipe between fragments
        val viewPager = findViewById<ViewPager>(R.id.viewpager)
        // Create an adapter that knows which fragment should be shown on each page
        val adapter = SimpleFragmentPagerAdapter(supportFragmentManager)
        // Set the adapter onto the view pager
        viewPager.adapter = adapter
    }
}