package noor.serry.shoestoreapp.ui.shoeListing

import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import noor.serry.shoestoreapp.R
import noor.serry.shoestoreapp.data.room.ShoesInfo
import noor.serry.shoestoreapp.databinding.CustomRecyclerViewBinding



class RecyclerAdapter(var shoesInfo : List<ShoesInfo>) : RecyclerView.Adapter<RecyclerAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        var binding = CustomRecyclerViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binding.shoesInfo = shoesInfo[position]
        val imageByte = Base64.decode(shoesInfo.get(position).image,Base64.DEFAULT)
        holder.imageView.setImageBitmap(BitmapFactory.decodeByteArray(imageByte,0,imageByte.size))
    }

    override fun getItemCount(): Int {
        return shoesInfo.size
    }

    class Holder( var binding: CustomRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root) {
         var imageView: ImageView

        init {
            imageView  = itemView.findViewById(R.id.shoesImage)
           itemView.setOnClickListener(this::onItemClickListener)
        }

        private fun onItemClickListener(view: View){
         with(binding.shoesInfo!!) {
             val directions = ShoeListingDirections.actionShoeListingToShoeDetail("Name : $name","Price : $price","Size : ${size}",
             "Company Name :$company",description,image)
             Navigation.findNavController(binding.root).navigate(directions)
         }
        }


    }
}