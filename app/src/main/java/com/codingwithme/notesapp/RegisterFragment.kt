package com.codingwithme.notesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.codingwithme.notesapp.database.UsersDatabase
import com.codingwithme.notesapp.entities.Users
import kotlinx.android.synthetic.main.fragment_register.buttonLogin
import kotlinx.android.synthetic.main.fragment_register.buttonRegister4
import kotlinx.android.synthetic.main.fragment_register.editTextEmailAddress
import kotlinx.android.synthetic.main.fragment_register.editTextName
import kotlinx.android.synthetic.main.fragment_register.editTextPassword
import kotlinx.coroutines.launch

class RegisterFragment : BaseFragment() {
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
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            RegisterFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonLogin.setOnClickListener {
            var loginFragment = LoginFragment.newInstance();
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.frame_layout, loginFragment).commit();
        }

        buttonRegister4.setOnClickListener{
            if (editTextEmailAddress.text.isNullOrEmpty()){
                Toast.makeText(context,"Email is required", Toast.LENGTH_SHORT).show()
            } else if (editTextName.text.isNullOrEmpty()){
                Toast.makeText(context,"Name is required", Toast.LENGTH_SHORT).show()
            } else if (editTextPassword.text.isNullOrEmpty()){
                Toast.makeText(context,"Password is required", Toast.LENGTH_SHORT).show()
            } else{
                launch {
                    context?.let {
                        var user = Users();
                        user.email = editTextEmailAddress.text.toString();
                        user.name = editTextName.text.toString();
                        user.password = editTextPassword.text.toString();

                        UsersDatabase.getDatabase(it).userDao().insertUser(user);

                        (activity as MainActivity).replaceFragment(HomeFragment.newInstance(), true);
                    }
                }
            }
        }
    }
}