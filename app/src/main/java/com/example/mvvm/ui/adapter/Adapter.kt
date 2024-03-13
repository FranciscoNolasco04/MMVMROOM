package com.example.mvvm.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.R
import com.example.mvvm.data.models.Book
import com.example.mvvm.data.models.Repository
import com.example.mvvm.ui.BookModel.BookModelView

class Adapter(

) : RecyclerView.Adapter<ViewBook>(){
    var repositorioBooks: List<Book> = Repository.books

    fun setData(newData: List<Book>) {
        repositorioBooks = newData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewBook {
        val layoutInflater = LayoutInflater.from(parent. context)
        val layoutItemHotel = R.layout. item_hotel
        return ViewBook(
            layoutInflater.inflate(layoutItemHotel, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewBook, position: Int) {
        holder.renderize( repositorioBooks.get(position))
    }

    override fun getItemCount(): Int = repositorioBooks.size
}