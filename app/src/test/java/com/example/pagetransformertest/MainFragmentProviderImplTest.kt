package com.example.pagetransformertest

import androidx.fragment.app.Fragment
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MainFragmentProviderImplTest {
    lateinit var fragmentCreators: List<FragmentCreator>
    lateinit var defaultFragmentCreator: FragmentCreator

    @Before
    fun setup() {
        fragmentCreators = listOf(
            ::TestFragmentOne,
            ::TestFragmentTwo
        )

        defaultFragmentCreator = ::TestFragmentDefault
    }

    @Test
    fun testGetFragment_validPositionReturnsCorrectFragment() {
        val validPosition = 0

        val sut = MainFragmentProviderImpl(creators = fragmentCreators, defaultFragmentCreator = defaultFragmentCreator)

        val fragment = sut.getFragment(validPosition)

        assertTrue(fragment is TestFragmentOne)
    }

    @Test
    fun testGetFragment_firstPositionReturnsCorrectFragment() {
        val firstPosition = 0

        val sut = MainFragmentProviderImpl(creators = fragmentCreators, defaultFragmentCreator = defaultFragmentCreator)

        val firstFragment = sut.getFragment(firstPosition)

        assertTrue(firstFragment is TestFragmentOne)
    }

    @Test
    fun testGetFragment_lastPositionReturnsCorrectFragment() {
        val lastPosition = fragmentCreators.lastIndex

        val sut = MainFragmentProviderImpl(creators = fragmentCreators, defaultFragmentCreator = defaultFragmentCreator)

        val lastFragment = sut.getFragment(lastPosition)

        assertTrue(lastFragment is TestFragmentTwo)
    }

    @Test
    fun testGetFragment_invalidPositionReturnsDefaultFragment() {
        val invalidPosition = 10

        val sut = MainFragmentProviderImpl(creators = fragmentCreators, defaultFragmentCreator = defaultFragmentCreator)

        val fragment = sut.getFragment(invalidPosition)

        assertTrue(fragment is TestFragmentDefault)
    }

    @Test
    fun testGetFragment_negativePositionReturnsDefaultFragment() {
        val negativePosition = -1

        val sut = MainFragmentProviderImpl(creators = fragmentCreators, defaultFragmentCreator = defaultFragmentCreator)

        val fragment = sut.getFragment(negativePosition)

        assertTrue(fragment is TestFragmentDefault)
    }

    inner class TestFragmentOne: Fragment()
    inner class TestFragmentTwo: Fragment()
    inner class TestFragmentDefault: Fragment()
}