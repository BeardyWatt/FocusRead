package com.hfad.focusread;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Book> books;

    public MyAdapter(Context c, ArrayList<Book> b) {

        context = c;
        books = b;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.book_object, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bookTitle.setText(books.get(position).getBookTitle());
        holder.bookAuthor.setText(books.get(position).getBookAuthor());
        holder.numberOfPages.setText(books.get(position).getNumberOfPages());
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView bookTitle, bookAuthor, numberOfPages;
        public MyViewHolder(@NonNull View itemView) {
           super(itemView);
        bookTitle = (TextView) itemView.findViewById(R.id.textBookTitle);
        bookAuthor = (TextView) itemView.findViewById(R.id.textAuthor);
        numberOfPages = (TextView) itemView.findViewById(R.id.textNop);
       }
   }
}