package com.example.test

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.example.test.fragments.message
import java.lang.ref.WeakReference

class Router(activity : FragmentActivity, container: Int) {
    private val weakActivity = WeakReference(activity)
    private val fragmentContainer = container

    fun navigateTo(addToBack : Boolean, fragmentNew: () -> Fragment, transportedMessage: Any? = null) {
        val activity = weakActivity.get()

        activity?.run {

            val fragment = fragmentNew()
            if (transportedMessage != null) {
                val args = Bundle()
                if (transportedMessage is Int) {
                    args.putInt(message, transportedMessage)
                }
                if (transportedMessage is String) {
                    args.putString(message, transportedMessage)
                }
                fragment.arguments = args
            }

            val transaction = supportFragmentManager.beginTransaction()
            transaction.setCustomAnimations( R.anim.anim_second, R.anim.anim_first)
            transaction.replace(fragmentContainer, fragment)
            if (addToBack) transaction.addToBackStack(fragment::class.java.simpleName)
            transaction.commit()
        }
    }

    fun navigateBack() : Boolean {
        val activity = weakActivity.get()

        activity?.run {
            if (supportFragmentManager.backStackEntryCount > 0) {
                supportFragmentManager.popBackStack()
                return true
            }
        }

        return false
    }
}

