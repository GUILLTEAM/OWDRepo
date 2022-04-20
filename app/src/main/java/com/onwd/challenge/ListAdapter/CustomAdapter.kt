package com.onwd.challenge.ListAdapter

import android.view.LayoutInflater

import com.onwd.challenge.R
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.onwd.challenge.ListAdapter.ItemsViewModel
import androidx.recyclerview.widget.RecyclerView
import com.onwd.challenge.ListAdapter.CenterSnapHelper
import com.onwd.challenge.CellClickListener
import android.util.Log

class CustomAdapter(private val mList: List<ItemsViewModel>, private val snapHelper: CenterSnapHelper, private val cellClickListener: CellClickListener ) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_holderitem, parent, false)

        return ViewHolder(view)
    }
  
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
  
        val ItemsViewModel = mList[position]

        holder.imageView.setImageResource(ItemsViewModel.image)
        holder.textView.text = ItemsViewModel.item.getName()
        holder.itemView.setOnClickListener {
            if (holder.adapterPosition != RecyclerView.NO_POSITION) {
                snapHelper.scrollTo(holder.adapterPosition, true)
            }

            cellClickListener.onCellClickListener(ItemsViewModel)
        }

    }
  
    override public fun getItemCount(): Int {
        return mList.size
    }

 public fun refresAdapter(Items: List<ItemsViewModel>){
     notifyDataSetChanged()
 }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = ItemView.findViewById<ImageView>(R.id.device_image)
        val textView: TextView = ItemView.findViewById<TextView>(R.id.devicename_text)
        val itemview: View = ItemView
    }
}