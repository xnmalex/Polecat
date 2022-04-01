package com.alexgui.polecat.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexgui.polecat.R
import com.alexgui.polecat.model.data.CatFeed
import com.alexgui.polecat.model.remote.Resource
import com.alexgui.polecat.viewmodel.CatFeedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainFragment : Fragment(), CatFeedAdapter.CatFeedItemListener {
    private lateinit var adapter: CatFeedAdapter
    private val viewModel by viewModels<CatFeedViewModel>()
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView(view)
        setupObservers(view)
    }

    private fun setupRecyclerView(view: View) {
        adapter = CatFeedAdapter(this)
        var linearLayoutManager: LinearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.reverseLayout = true
        linearLayoutManager.stackFromEnd = true

        view.findViewById<RecyclerView>(R.id.recyclerView).layoutManager = linearLayoutManager
        view.findViewById<RecyclerView>(R.id.recyclerView).addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )
        view.findViewById<RecyclerView>(R.id.recyclerView).adapter = adapter

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_next) {
               viewModel.getFeed()
                return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupObservers(view: View) {
        viewModel.catFeed.observe(viewLifecycleOwner, Observer {
            when (it.status) {

                Resource.Status.SUCCESS -> {
                    view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                    view.findViewById<RecyclerView>(R.id.recyclerView).scrollToPosition(adapter.itemCount-1)

                }
                Resource.Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }

                Resource.Status.LOADING -> {
                    view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
                }
            }
        })


    }

    override fun onClickedCatFeed(catFeed: CatFeed) {
        runBlocking {
            async {
                viewModel.deleteCatFeed(catFeed)

            }
        }

    }
}