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
import androidx.preference.PreferenceManager
import noor.serry.shoestoreapp.R
import noor.serry.shoestoreapp.data.Repository
import noor.serry.shoestoreapp.databinding.FragmentLoginBinding

class Login : Fragment() {

    lateinit var binding : FragmentLoginBinding
    lateinit var repository: Repository
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isLoginBefore()
        repository = Repository(requireContext())
        binding.registerBt.setOnClickListener(this::goToRegisterPage)
        binding.loginBt.setOnClickListener(this::Login)
    }

    fun goToRegisterPage(view: View){
        findNavController().navigate(R.id.action_login_to_register)
    }

     private fun Login(view: View){
         val email = binding.email.text.toString();
       if(repository.getPassword(email)==null)
           Toast.makeText(requireContext(), "this email not found", Toast.LENGTH_SHORT).show()
         else if (!repository.getPassword(email).equals(binding.password.text.toString()))
           Toast.makeText(requireContext(), " password is wrong", Toast.LENGTH_SHORT).show()
         else {findNavController().navigate(R.id.action_login_to_viewpager)
         setEmailInSharedPreferences(email)}
     }

    private fun setEmailInSharedPreferences(email: String){
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val  editor = sharedPreferences.edit()
        editor.putString("email",email)
        editor.putBoolean("isLogin",true)
        editor.apply()
    }

    private fun isLoginBefore(){
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val isLogin = sharedPreferences.getBoolean("isLogin",false)
        if(isLogin)
            findNavController().navigate(R.id.action_login_to_shoeListing)
    }

}