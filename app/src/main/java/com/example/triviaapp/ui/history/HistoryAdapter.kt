package com.example.triviaapp.ui.history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.triviaapp.databinding.HistoryRvBinding
import com.example.triviaapp.db.Quiz


class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryHolder>() {

    private val items = ArrayList<Quiz>()
    fun submitList(quizList: List<Quiz>) {
        items.clear()
        items.addAll(quizList)
        notifyDataSetChanged()
    }

    inner class HistoryHolder(private val binding: HistoryRvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(quiz: Quiz) {
            with(binding){
                labelGame.text="GAME ${position+1}"
                tvName.text=quiz.userName.toString().trim()
                tvDate.text=quiz.dateTime.toString().trim()
                tvFirstAns.text="Ans : "+quiz.bestCricketer.trim()
                tvScndAns.text="Ans : "+quiz.colorsInNationlFlag.trim()
            }

        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryAdapter.HistoryHolder {
        val binding =
            HistoryRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        holder.bind(items[holder.adapterPosition])
    }

    override fun getItemCount(): Int = items.size


}
