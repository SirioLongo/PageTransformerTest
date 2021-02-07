package com.example.pagetransformertest

import androidx.fragment.app.Fragment
import java.lang.IndexOutOfBoundsException

class MainFragmentProviderImpl(
    private val creators: List<FragmentCreator>,
    private val defaultFragmentCreator: () -> Fragment
): MainFragmentProvider {
    override fun getFragment(position: Int): Fragment {
        return try {
            creators[position].invoke()
        } catch(iobe: IndexOutOfBoundsException) {
            defaultFragmentCreator()
        }
    }
}