package com.example.listbuku

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class adapter(private val context: Context, private val data: ArrayList<buku>) :
    RecyclerView.Adapter<adapter.bukuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): bukuViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.itembuku, parent, false)
        return bukuViewHolder(view)
    }

    override fun onBindViewHolder(holder: bukuViewHolder, position: Int) {
        val detailbuku = data[position]
        holder.itemView.setOnClickListener {
            val intent = Intent(context, bukadetail::class.java)
            intent.putExtra("judul", detailbuku.judul)
            intent.putExtra("penulis", detailbuku.penulis)
            context.startActivity(intent)
        }
        holder.tvjudul.text = detailbuku.judul
        holder.tvpenulis.text = detailbuku.penulis

        holder.hapus.setOnClickListener(){
            val itemPosition = holder.adapterPosition
            if (itemPosition != RecyclerView.NO_POSITION) {
                data.removeAt(itemPosition)
                notifyItemRemoved(itemPosition)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class bukuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvjudul: TextView = itemView.findViewById(R.id.judul)
        val tvpenulis: TextView = itemView.findViewById(R.id.Penulis)
        val hapus : ImageButton = itemView.findViewById(R.id.delete)
    }
}
