package noor.serry.shoestoreapp.ui.shoeListing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import noor.serry.shoestoreapp.R
import noor.serry.shoestoreapp.data.room.ShoesInfo
import noor.serry.shoestoreapp.databinding.FragmentShoeListingBinding

class ShoeListing : Fragment() {
    lateinit var binding : FragmentShoeListingBinding
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
        setRecyclerLayoutManager()
        setObserver()

    }

    private fun setRecyclerLayoutManager() {
        binding.recyclerView.setLayoutManager(LinearLayoutManager(context))
    }

    private fun setObserver() {
        viewModel.getShoesDetails()?.observe(viewLifecycleOwner) { shoesInfo: List<ShoesInfo> ->
            onChanged(shoesInfo)
        }
    }

    fun onChanged(shoesInfo: List<ShoesInfo>) {
        binding.recyclerView.adapter = RecyclerAdapter(shoesInfo )
    }

    private fun goToShoeDetailPage(view: View){
        findNavController().navigate(R.id.action_shoeListing_to_shoeDetailPage)
    }
}