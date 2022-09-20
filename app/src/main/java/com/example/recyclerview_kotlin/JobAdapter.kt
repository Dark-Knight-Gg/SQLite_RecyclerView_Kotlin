package com.example.recyclerview_kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class JobAdapter(private var context:MainActivity,private var listJob:ArrayList<Job>):RecyclerView.Adapter<JobAdapter.ViewHolder> (){
    class ViewHolder (view : View):RecyclerView.ViewHolder(view){
        var adapter_imgPicture : ImageView =view.findViewById(R.id.adadpter_imgPicture)
        var adapter_txtName :TextView=view.findViewById(R.id.adadpter_txtName)
        var adapter_txtDescribe:TextView=view.findViewById(R.id.adapter_txtDescribe)
        var adapter_imgDelete :ImageView=view.findViewById(R.id.adapter_imgDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var view = LayoutInflater.from(context).inflate(R.layout.adapter_job,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var job = listJob.get(position)
        //holder.adapter_imgPicture.setImageResource(job.picture)
        holder.adapter_txtName.setText(job.Name)
        holder.adapter_txtDescribe.setText(job.Name)
        holder.adapter_imgDelete.setOnClickListener(){
            context.Delete(position)
        }
    }

    override fun getItemCount(): Int {
        return listJob.size
    }
}