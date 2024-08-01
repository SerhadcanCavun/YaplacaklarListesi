package com.example.yaplacaklarlistesi.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.yaplacaklarlistesi.Interface.ImageStatusListener
import com.example.yaplacaklarlistesi.Model.Avatar
import com.example.yaplacaklarlistesi.R
import com.example.yaplacaklarlistesi.UserInformationState

class AdapterAvatars(private val context: Context, private val avatarList: List<Avatar>, private val listener:ImageStatusListener): RecyclerView.Adapter<AdapterAvatars.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.avatars_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = avatarList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val avatarData = avatarList[position]
        holder.bind(avatarData)

        holder.avatarImage.setOnClickListener {
            listener.onImageChange(avatarData.avatarResId)
            val avatarName = context.resources.getResourceEntryName(avatarData.avatarResId)
            UserInformationState.avatarName = avatarName
        }
    }

    class ViewHolder(avatarView: View) : RecyclerView.ViewHolder(avatarView) {
        var avatarImage: ImageView = avatarView.findViewById(R.id.imageView2)
        fun bind(avatarData: Avatar) {
            avatarImage.setImageResource(avatarData.avatarResId)
        }
    }
}
