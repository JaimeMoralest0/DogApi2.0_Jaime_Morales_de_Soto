package com.example.dogapi.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.example.dogapi.R

class BreedAdapter(private val breeds: List<Pair<String, String>>) :
    RecyclerView.Adapter<BreedAdapter.BreedViewHolder>() {

    class BreedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgBreed: ImageView = itemView.findViewById(R.id.imgBreed)
        val txtBreedName: TextView = itemView.findViewById(R.id.txtBreedName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_breed, parent, false)
        return BreedViewHolder(view)
    }

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) {
        val (name, imageUrl) = breeds[position]
        holder.txtBreedName.text = name.capitalize()
        Picasso.get()
            .load(imageUrl)
            .fit() // Ajusta la imagen al tamaño del contenedor
            .centerCrop() // Recorta la imagen para rellenar el espacio
            .into(holder.imgBreed)
    }

    override fun getItemCount(): Int = breeds.size
}
