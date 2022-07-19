package com.hfad.focusread;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    Context context;
    ArrayList<Book> bookList;

    public MyAdapter(Context c, ArrayList<Book> b) {

        context = c;
        bookList = b;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.book_object, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        Book currentBook = bookList.get(position);
        holder.bindTo(currentBook);
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView bookTitle, bookAuthor, numberOfPages;
        public ViewHolder(@NonNull View itemView) {
           super(itemView);
            bookTitle = (TextView) itemView.findViewById(R.id.textBookTitle);
            bookAuthor = (TextView) itemView.findViewById(R.id.textAuthor);
            numberOfPages = (TextView) itemView.findViewById(R.id.no_pages_txt);
            itemView.setOnClickListener(this);
       }
       void bindTo(Book currentBook){
            bookTitle.setText(currentBook.getBookTitle());
            bookAuthor.setText(currentBook.getBookAuthor());
            numberOfPages.setText(currentBook.getNumberOfPages());
       }
       public void onClick(View v){
            Book currentBook = bookList.get(getAdapterPosition());
            Intent intent = new Intent(context,SetupReadActivity.class);
            intent.putExtra("TITLE", currentBook.getBookTitle());
            intent.putExtra("AUTHOR", currentBook.getBookAuthor());
            intent.putExtra("NOP", currentBook.getNumberOfPages());
            context.startActivity(intent);
       }
   }
}