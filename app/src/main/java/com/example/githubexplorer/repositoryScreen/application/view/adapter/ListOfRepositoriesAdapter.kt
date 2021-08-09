package com.example.githubexplorer.repositoryScreen.application.view.adapter


import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.githubexplorer.R
import com.example.githubexplorer.repositoryScreen.domain.model.RepositoryDetails
import java.util.*

/**
 * @param mContext
 * @param deviceDataList List of device data
 * @param listener
 * adapter for device images
 */
class ListOfRepositoriesAdapter(
    private val mContext: Context,
    private var repositoryDataList: ArrayList<RepositoryDetails>,
    private val listener: RepositoryListListener
) : RecyclerView.Adapter<ListOfRepositoriesAdapter.ViewHolder>() {

    //variables
    val booleanList = ArrayList<Boolean>()
    private val selectedRepositoryList = ArrayList<RepositoryDetails>()

    /**
     * @param parent  ViewGroup into which the new View will be added
     * @param viewType The view type of the new View
     * attach view to the recyclerview
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.repo_list_item, parent, false)
        return ViewHolder(view)
    }

    /**
     * return the number of item present in the list
     */
    override fun getItemCount(): Int {
        return repositoryDataList.size
    }

    /**
     * @param searchedList list of searched devices
     * Searched data list
     */
    fun searchedList(searchedList: ArrayList<RepositoryDetails>) {
        this.repositoryDataList = searchedList
        for (item in searchedList)
            booleanList.add(false)
        notifyDataSetChanged()
    }

    /**
     * @param holder ViewHolder which should be updated
     * @param position position of the item
     * This is a on bind view holder overridden function
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Device name and Location
        holder.nameTextView.text = repositoryDataList[position].name
        holder.descriptionTextView.text = repositoryDataList[position].description

        Log.d("KRISHNA12345",repositoryDataList[position].isSelected.toString())
        if (repositoryDataList[position].isSelected)
            holder.addToWatchListImageView.setColorFilter(Color.YELLOW)
        else
            holder.addToWatchListImageView.setColorFilter(Color.GRAY)


        holder.itemView.setOnClickListener {
            repositoryDataList[position].isSelected = !repositoryDataList[position].isSelected
            if (!selectedRepositoryList.contains(repositoryDataList[position])) {
                holder.addToWatchListImageView.setColorFilter(Color.YELLOW)
                selectedRepositoryList.add(repositoryDataList[position])
            } else {
                selectedRepositoryList.remove(repositoryDataList[position])
                holder.addToWatchListImageView.setColorFilter(Color.GRAY)
            }
            listener.onClick(selectedRepositoryList)
        }
    }


    /**
     * @param itemView
     * This is on bind view holder class
     */
    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.repository_name_text_view)
        val descriptionTextView: TextView = itemView.findViewById(R.id.repository_description_text_view)
        val addToWatchListImageView: ImageView = itemView.findViewById(R.id.add_to_watchlist)
    }

    /**
     * an Interface which needs to be implemented whenever the adapter is attached to the
     * recyclerview edit functionality
     */
    interface RepositoryListListener {
        fun onClick(selectedRepositoriesList: ArrayList<RepositoryDetails>)
    }
}