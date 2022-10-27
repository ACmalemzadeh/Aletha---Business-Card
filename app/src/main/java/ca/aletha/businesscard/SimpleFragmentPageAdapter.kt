package ca.aletha.businesscard

import android.util.Log
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class SimpleFragmentPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm!!) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                Fragment1() // fragment with QR Code
            }
            else -> {
                Fragment2() // the fragment that is achieved by swiping left. VCard filling info.
            }
        }
    }

    override fun getCount(): Int {
        return 2 //returns teh number of fragments
    }

}
