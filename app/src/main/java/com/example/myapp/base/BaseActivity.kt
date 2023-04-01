package com.example.myapp.base

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * @author Juan Olguin
 */
abstract class BaseActivity : AppCompatActivity() {

    @IdRes var fragmentContainerId: Int? = null



    override fun onBackPressed() {
        var handled = false
        for (fragment: Fragment in supportFragmentManager.fragments) {
            if (fragment is BaseFragment<*>) {
                handled = fragment.onBackPressed()
                if (handled) {
                    break
                }
            }
        }
        if (!handled) {
            super.onBackPressed()
        }
    }

    fun navigateTo(fragment: Fragment, fragmentTag: String, addToBackStack: Boolean) {
        fragmentContainerId ?: throw IllegalStateException("set fragmentContainerId first")
        fragmentContainerId?.let {
            supportFragmentManager.beginTransaction().apply {
                replace(it, fragment)
                if (addToBackStack) {
                    addToBackStack(fragmentTag)
                }
                commit()
            }
        }
    }

    fun navigateTo(fragment: Fragment, addToBackStack: Boolean = true) {
        navigateTo(fragment, fragment::class.java.simpleName, addToBackStack)
    }

    fun backToPrevious() {
        supportFragmentManager.popBackStack()
    }

    fun backTo(fragmentTag: String?, inclusive: Boolean) {
        val flags = if (inclusive) FragmentManager.POP_BACK_STACK_INCLUSIVE else 0
        supportFragmentManager.popBackStack(fragmentTag, flags)
    }

    fun backTo(fragmentClass: Class<BaseFragment<*>>, inclusive: Boolean = false) {
        backTo(fragmentClass.simpleName, inclusive)
    }

}