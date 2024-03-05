package com.example.motionassignment2.adapter

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.motionassignment2.R
import com.example.motionassignment2.bnv.HomeFragmentDirections
import com.example.motionassignment2.model.Courses
import com.squareup.picasso.Picasso

class CoursesAdapter(private val items : List<Courses>) :
    RecyclerView.Adapter<CoursesAdapter.ViewHolder>() {
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val courseImage: ImageView = itemView.findViewById(R.id.iv_item)
        val courseTitle: TextView = itemView.findViewById(R.id.tv_title)
        val courseQuestion: TextView = itemView.findViewById(R.id.tv_questionmarks)
        val courseDuration: TextView = itemView.findViewById(R.id.tv_timemarks)
        val courseButton : AppCompatButton = itemView.findViewById(R.id.btn_detail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesAdapter.ViewHolder {
       return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false))
    }

    override fun onBindViewHolder(holder: CoursesAdapter.ViewHolder, position: Int) {
        val currentItem = items[position]
        Picasso.get().load(currentItem.courseImage).into(holder.courseImage)
        holder.courseTitle.setText(currentItem.courseTitle)
        holder.courseQuestion.setText(currentItem.answeredQuestion)
        holder.courseDuration.setText(currentItem.duration)
        holder.courseButton.setOnClickListener {
            val action = HomeFragmentDirections.actionActionHomeToCourseDetailsFragment2(
                currentItem.courseImage!!,
                currentItem.courseTitle!!,
                currentItem.rating!!,
                currentItem.answeredQuestion!!,
                currentItem.totalQuestion!!,
                currentItem.status!!,
                currentItem.duration!!)
            findNavController(holder.itemView).navigate(action)
        }
        if(currentItem.status == "in_progress") {
            holder.courseButton.text = "Continue Test"
        } else {
            holder.courseButton.text = "Revisit"
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }
}