package noor.serry.shoestoreapp.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import noor.serry.shoestoreapp.R
import noor.serry.shoestoreapp.data.Repository
import noor.serry.shoestoreapp.data.room.UserInfo
import noor.serry.shoestoreapp.databinding.FragmentRegisterBinding

class Register : Fragment() {
      lateinit var binding : FragmentRegisterBinding
      lateinit var repository : Repository
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_register, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         repository = Repository(requireContext())
        binding.loginBt.setOnClickListener(this::addNewAccount)
    }

    private fun addNewAccount(view: View){
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()
        repository.insertNewEmail(UserInfo(email, password))
        goToLoginPage()
    }

    private fun goToLoginPage(){
        findNavController().navigate(R.id.action_register_to_login)
    }
}