package noor.serry.shoestoreapp.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import noor.serry.shoestoreapp.R
import noor.serry.shoestoreapp.databinding.FragmentFirstScreenBinding
import noor.serry.shoestoreapp.databinding.FragmentSecondScreenBinding

class SecondScreen : Fragment() {
     lateinit var binding: FragmentSecondScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondScreenBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nextButton.setOnClickListener(this::goToShoesList)
    }

    private fun goToShoesList(view: View){
        findNavController().navigate(R.id.action_viewPager_to_shoeListing)
    }
}