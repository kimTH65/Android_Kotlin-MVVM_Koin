package com.example.memo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.memo.databinding.FragmentAddBinding
import com.example.memo.databinding.FragmentMainBinding
import com.example.memo.model.room.Entity
import com.example.memo.viewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.recycler_item.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate

class Fragment_Main : Fragment() {
    lateinit var home_activity: MainActivity
    private var mBinding: FragmentMainBinding? = null
    private val binding get() = mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentMainBinding.inflate(inflater, container, false)
        home_activity = context as MainActivity

        val viewModel: MainViewModel by viewModels()
        val mAdapter = RecyclerViewAdapter(home_activity , viewModel)

        val LinearManager = LinearLayoutManager(home_activity)
        LinearManager.reverseLayout = true
        LinearManager.stackFromEnd = true

        binding.recyclerview.apply {
            adapter = mAdapter
            layoutManager = LinearManager

        }
        viewModel.allUsers.observe(this, Observer { users ->
            // Update the cached copy of the users in the adapter.
            users?.let { mAdapter.setUsers(it) }
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}