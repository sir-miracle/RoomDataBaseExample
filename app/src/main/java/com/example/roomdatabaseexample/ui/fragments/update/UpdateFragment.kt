package com.example.roomdatabaseexample.ui.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomdatabaseexample.R
import com.example.roomdatabaseexample.databinding.FragmentUpdateBinding
import com.example.roomdatabaseexample.model.User
import com.example.roomdatabaseexample.viewmodel.UserViewModel

class UpdateFragment : Fragment(R.layout.fragment_update) {
    lateinit var binding: FragmentUpdateBinding
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mUserViewmodel: UserViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUpdateBinding.bind(view)
        mUserViewmodel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.etUpdatePersonName.setText(args.currentUser.firstName)
        binding.etUpdatePersonLastName.setText(args.currentUser.lastName)
        binding.etUpdatePersonAge.setText(args.currentUser.age.toString())

        binding.btnUpdate.setOnClickListener {
            updateItem()
        }
        //add menu to the app bar
        setHasOptionsMenu(true)


    }

    private fun updateItem() {
        val firstName = binding.etUpdatePersonName.text.toString()
        val lastName = binding.etUpdatePersonLastName.text.toString()
        val age = binding.etUpdatePersonAge.text

        if (inputValidation(firstName, lastName, age)) {
            //create user object
            val updatedUser = User(
                args.currentUser.id, firstName,
                lastName, Integer.parseInt(age.toString())
            )
            //update current user
            mUserViewmodel.updateUser(updatedUser)
            Toast.makeText(
                requireActivity(),
                "update successful", Toast.LENGTH_SHORT
            ).show()
            //after updating, navigate back to the list fragment

            findNavController().navigate(R.id.action_updateFragment_to_listFragment)

        } else {
            Toast.makeText(
                requireActivity(),
                "Please fill out all fields", Toast.LENGTH_SHORT
            ).show()
        }

    }

    private fun inputValidation(firstName: String, lastName: String, age: Editable): Boolean {

        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun deleteUser() {

    val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("delete ${args.currentUser.firstName}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.firstName}?")
        builder.setPositiveButton("Yes"){_,_,->

            mUserViewmodel.deleteUser(args.currentUser)
            Toast.makeText(requireActivity(), "${args.currentUser.firstName} deleted successfully",
                Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_updateFragment_to_listFragment)

        }
        builder.setNegativeButton("No"){_,_, ->


        }
        builder.create().show()
    }


}