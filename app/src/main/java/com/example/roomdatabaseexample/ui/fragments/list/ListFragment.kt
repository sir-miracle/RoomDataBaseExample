package com.example.roomdatabaseexample.ui.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabaseexample.R
import com.example.roomdatabaseexample.viewmodel.UserViewModel
import com.example.roomdatabaseexample.databinding.FragmentListBinding
import com.example.roomdatabaseexample.ui.recyclerviewadapters.ListAdapter


class ListFragment : Fragment(R.layout.fragment_list) {

    lateinit var binding: FragmentListBinding
    private lateinit var mUserViewModel: UserViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)

        binding.flbAdd.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        val adapter = ListAdapter()
        val recyclerview = view.findViewById<RecyclerView>(R.id.list_recyclerview)
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(requireContext())

        //initialize the viewmodel
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->

            adapter.setData(user)

        })

        setHasOptionsMenu(true)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.menu_delete){

            deleteAllUsers()

        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUsers() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("delete all entries?")
        builder.setMessage("Are you sure you want to delete all entries ?")
        builder.setPositiveButton("Yes"){_,_,->

            mUserViewModel.deleteAll()
            Toast.makeText(requireActivity(), "All entries deleted successfully",
                Toast.LENGTH_SHORT).show()

        }
        builder.setNegativeButton("No"){_,_, ->


        }
        builder.create().show()
    }


}