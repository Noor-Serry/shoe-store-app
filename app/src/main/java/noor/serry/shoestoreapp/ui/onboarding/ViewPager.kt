package noor.serry.shoestoreapp.ui.onboarding

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import noor.serry.shoestoreapp.R
import noor.serry.shoestoreapp.databinding.FragmentViewPagerBinding


class ViewPager : Fragment() {
    lateinit var binding : FragmentViewPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewPagerBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       setAdapterToViewPager()

    }
    private fun setAdapterToViewPager(){
        val fragments = arrayListOf(FirstScreen(),SecondScreen())
        val adapter = ViewPagerAdapter(fragments,requireActivity().supportFragmentManager,lifecycle)
        binding.viewPager.adapter = adapter
    }


}