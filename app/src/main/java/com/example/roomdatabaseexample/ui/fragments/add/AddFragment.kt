package com.example.roomdatabaseexample.ui.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomdatabaseexample.R
import com.example.roomdatabaseexample.model.User
import com.example.roomdatabaseexample.viewmodel.UserViewModel
import com.example.roomdatabaseexample.databinding.FragmentAddBinding


class AddFragment : Fragment(R.layout.fragment_add) {

   lateinit var binding: FragmentAddBinding
   private lateinit var muserViewmodel: UserViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddBinding.bind(view)

        //get an instance of the viewmodel class
        muserViewmodel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.btnAdd.setOnClickListener {

            insertDataToDatabase()
        }
    }

    private fun insertDataToDatabase() {
        val firstName = binding.etAddPersonName.text.toString()
        val lastName = binding.etAddPersonLastName.text.toString()
        val age = binding.etAddPersonAge.text

        if (inputValidation(firstName, lastName, age)){
            //create user object

            val user = User(0, firstName, lastName, Integer.parseInt(age.toString()))
            //add data to database

            muserViewmodel.addUser(user)
            Toast.makeText(requireActivity(), "successfully added", Toast.LENGTH_SHORT).show()
            //after adding, navigate back to the list fragment
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireActivity(), "Please fill out all fields.", Toast.LENGTH_SHORT).show()

        }

    }

    private fun inputValidation(firstName: String, lastName: String, age: Editable): Boolean{

        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }


}