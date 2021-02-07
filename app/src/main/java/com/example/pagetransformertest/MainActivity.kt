package com.example.pagetransformertest

import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val fragmentCreators: List<FragmentCreator> by lazy {
        listOf<FragmentCreator>(
            { FirstFragment.newInstance() },
            { LastFragment() }
        )
    }
    private val defaultCreator: FragmentCreator by lazy {
        { FirstFragment.newInstance() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewPager()
    }

    private fun setupViewPager() {
        val fragmentProvider = MainFragmentProviderImpl(fragmentCreators, defaultCreator)
        val adapter = MainFragmentPagerAdapter(this, fragmentCreators.size, fragmentProvider)
        pager?.adapter = adapter
        pager?.setPageTransformer(MainPageTransformer())
        pager?.offscreenPageLimit = 1
    }
}
