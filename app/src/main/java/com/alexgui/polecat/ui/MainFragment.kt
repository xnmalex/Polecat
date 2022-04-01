package com.alexgui.polecat.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.alexgui.polecat.R
import com.alexgui.polecat.model.data.CatFeed
import com.alexgui.polecat.viewmodel.CatFeedViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class IntroFragment : Fragment() {
    private val viewModel: CatFeedViewModel by viewModels()
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_intro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }



    }

    private fun appendCatFeed(catFeed: List<CatFeed>){
        val sb = StringBuilder()
        for(cat in catFeed){
            sb.append(cat.fact + "\n")
        }
        //text.text = sb.toString()
    }
}