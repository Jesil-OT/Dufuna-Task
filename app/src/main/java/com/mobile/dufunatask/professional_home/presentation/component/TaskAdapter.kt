package com.mobile.dufunatask.professional_home.presentation.component

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mobile.dufunatask.databinding.HomeItemBinding
import com.mobile.dufunatask.professional_home.data.models.task_result.TaskData
import java.util.Locale

class TaskAdapter : ListAdapter<TaskData, TaskAdapter.MedicationViewHolder>(WidgetDiffUtil()) {

    inner class MedicationViewHolder(
        private val binding: HomeItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TaskData) = with(binding) {
            taskTitle.text = item.action.lowercase(Locale.getDefault())
            taskNameAssignee.text =
                item.taskAssignments?.get(0)?.assignee?.firstName ?: "Assignee Unavailable"
            taskDoor.text = item.order ?: "Rm 3A"
            taskBed.text = item.supportLevel ?: "Bed 45"
            taskTime.text = item.hourOfDay ?: "Morning"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MedicationViewHolder(
            HomeItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MedicationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = position

    class WidgetDiffUtil : DiffUtil.ItemCallback<TaskData>() {
        override fun areItemsTheSame(
            oldItem: TaskData,
            newItem: TaskData
        ): Boolean {
            return oldItem.taskId == newItem.taskId
        }

        override fun areContentsTheSame(
            oldItem: TaskData,
            newItem: TaskData
        ): Boolean {
            return oldItem.taskId == newItem.taskId
        }
    }
}