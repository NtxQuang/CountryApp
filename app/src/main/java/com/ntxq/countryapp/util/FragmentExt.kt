package com.ntxq.countryapp.util

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.ntxq.countryapp.R

fun Fragment.navigate(
    destination: Int, extraData: Bundle? = null
) {
    activity?.let {
        try {
            Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                .navigate(destination, extraData, navOptions {
                    anim {
                        enter = R.anim.slide_in_right
                        exit = R.anim.slide_out_left
                        popEnter = R.anim.slide_in_left
                        popExit = R.anim.slide_out_right
                    }
                })
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}

fun Fragment.popBackstack() {
    activity?.let {
        try {
            findNavController().popBackStack()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}

fun Fragment.popBackstack(destination: Int, inclusive: Boolean = false): Boolean {
    activity?.let {
        try {
            return findNavController().popBackStack(destination, inclusive)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
    return false
}

fun Fragment.onBackPressed(runnable: Runnable) {
    activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner,
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                runnable.run()
            }
        })
}