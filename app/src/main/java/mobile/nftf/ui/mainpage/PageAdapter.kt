package mobile.nftf.ui.mainpage

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class PageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private val TITLE = listOf("검색 결과", "내 보관함")

    override fun getPageTitle(position: Int): CharSequence? {
        return TITLE[position]
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> SearchFragment.newInstance()
            else -> CartFragment.newInstance()
        }
    }

    override fun getCount(): Int {
        return TITLE.size
    }
}