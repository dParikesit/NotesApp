package com.codingwithme.notesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.codingwithme.notesapp.database.UsersDatabase
import com.codingwithme.notesapp.entities.Users
import kotlinx.android.synthetic.main.fragment_login.buttonLogin2
import kotlinx.android.synthetic.main.fragment_login.buttonRegister2
import kotlinx.android.synthetic.main.fragment_login.editTextTextEmailAddress
import kotlinx.android.synthetic.main.fragment_login.editTextTextPassword
import kotlinx.coroutines.launch


class LoginFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            LoginFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonRegister2.setOnClickListener {
            var registerFragment = RegisterFragment.newInstance();
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.frame_layout, registerFragment).commit();
        }

        buttonLogin2.setOnClickListener{
            if (editTextTextEmailAddress.text.isNullOrEmpty()){
                Toast.makeText(context,"Email is required", Toast.LENGTH_SHORT).show()
            } else if (editTextTextPassword.text.isNullOrEmpty()){
                Toast.makeText(context,"Password is required", Toast.LENGTH_SHORT).show()
            } else{
                launch {
                    context?.let {
                        var user = Users();
                        user.email = editTextTextEmailAddress.text.toString();
                        user.password = editTextTextPassword.text.toString();

                        var userDb = UsersDatabase.getDatabase(it).userDao().getUser(user.email.toString());

                        if (userDb.password!!.toString() == user.password.toString()){
                            (activity as MainActivity).replaceFragment(HomeFragment.newInstance(), true);
                        }
                    }
                }
            }
        }
    }
}