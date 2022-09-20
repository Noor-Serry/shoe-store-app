package noor.serry.shoestoreapp.ui.shoeListing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import noor.serry.shoestoreapp.R
import noor.serry.shoestoreapp.R.layout.custom_view
import noor.serry.shoestoreapp.data.room.ShoesInfo
import noor.serry.shoestoreapp.databinding.CustomViewBinding
import noor.serry.shoestoreapp.databinding.FragmentShoeListingBinding


class ShoeListing : Fragment() {
    lateinit var binding : FragmentShoeListingBinding
    var directions : ShoeListingDirections.ActionShoeListingToShoeDetail? = null
    lateinit var viewModel : ViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_shoe_listing, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addShoes.setOnClickListener(this::goToShoeDetailPage)
        viewModel = ViewModelProvider(requireActivity()).get(ViewModel::class.java)
        setObserver()
        setActionBar()
    }

    private fun setObserver() {
        viewModel.getShoesDetails()?.observe(viewLifecycleOwner) { shoesInfo: List<ShoesInfo> ->
            onChanged(shoesInfo)
        }
    }

    
  private  fun onChanged(shoesInfo: List<ShoesInfo>) {
        for(shoeInfo in shoesInfo){
            var view = LayoutInflater.from(requireContext()).inflate(custom_view,null)
            var customViewBinding = DataBindingUtil.inflate<CustomViewBinding>(layoutInflater,custom_view,view as LinearLayout,false)
            customViewBinding.shoesInfo = shoeInfo
            setAction(shoeInfo)
            customViewBinding.root.setOnClickListener(this::showShoeDetail)
        binding.addNewShoes.addView(customViewBinding.root)}
    }

    private fun goToShoeDetailPage(view: View){
        findNavController().navigate(R.id.action_shoeListing_to_shoeDetailPage)
    }

    private fun showShoeDetail(view: View){
        findNavController().navigate(directions!!)
    }

    private fun setAction(shoesInfo: ShoesInfo){
        with(shoesInfo){
            directions = ShoeListingDirections.actionShoeListingToShoeDetail("Name : $name","Price : $price","Size : ${size}",
                "Company Name :$company",description,image)
        }
    }

    private fun setActionBar(){
        (activity as AppCompatActivity).setSupportActionBar(binding.toolBar)
        with((activity as AppCompatActivity).supportActionBar!!){
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_baseline_logout_24)
            setTitle("")

        }
        binding.toolBar.setNavigationOnClickListener { logOut() }
    }

    private fun logOut(){
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val  editor = sharedPreferences.edit()
        editor.putBoolean("isLogin",false)
        editor.apply()
        findNavController().navigate(R.id.action_shoeListing_to_login)

    }



}