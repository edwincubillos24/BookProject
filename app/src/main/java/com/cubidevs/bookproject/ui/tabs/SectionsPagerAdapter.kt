package com.cubidevs.bookproject.ui.tabs

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.cubidevs.bookproject.R
import com.cubidevs.bookproject.ui.delete.DeleteFragment
import com.cubidevs.bookproject.ui.list.ListFragment
import com.cubidevs.bookproject.ui.newbook.NewBookFragment
import com.cubidevs.bookproject.ui.update.UpdateFragment

private val TAB_TITLES = arrayOf(
    R.string.title_list,
    R.string.title_new,
    R.string.title_update,
    R.string.title_delete
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return ListFragment()
            1 -> return NewBookFragment()
            2 -> return UpdateFragment()
            else -> return DeleteFragment()
        }
    }


    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 4
    }
}