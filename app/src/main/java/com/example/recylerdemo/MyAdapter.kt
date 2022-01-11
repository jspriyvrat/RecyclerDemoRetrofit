package com.example.recylerdemo


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide


class MyAdapter(val context: Context, val myData: List<MyModel>) :
    Adapter<MyAdapter.Inner>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Inner {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.mycard, parent, false)
        return Inner(view)
    }

    override fun onBindViewHolder(holder: Inner, position: Int) {
        val data = myData[position]

        holder.emails.text=data.email
        holder.txtfirst.text=data.first_name
        holder.txtsecond.text=data.last_name
        Glide.with(context).load(data.avatar).into(holder.avatars)
    }

    override fun getItemCount(): Int {
        return myData.size
    }

    class Inner(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtfirst:TextView=itemView.findViewById(R.id.firstName)
        val txtsecond:TextView=itemView.findViewById(R.id.lastName)
        val avatars:ImageView=itemView.findViewById(R.id.mypic)
        val emails:TextView=itemView.findViewById(R.id.email)
    }
}