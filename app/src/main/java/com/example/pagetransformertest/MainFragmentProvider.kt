package com.example.pagetransformertest

import androidx.fragment.app.Fragment

interface MainFragmentProvider {
    fun getFragment(position: Int): Fragment
}