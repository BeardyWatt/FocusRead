package com.hfad.focusread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BookListActivity extends AppCompatActivity {

    private RecyclerView mBook_RecyclerView;
    private List<Book> mBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        mBook_RecyclerView = (RecyclerView) findViewById(R.id.book_recyclerview);
        mBook_RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBooks = new ArrayList<>();
        for(int i = 0; i<50; i++){
            Book book = new Book();
            book.setmBookTitle("Book #"+i);
            book.setmBookAuthor("Author "+i);
            mBooks.add(book);
        }
        mBook_RecyclerView.setAdapter(new BookAdapter(mBooks));
    }
    class BookAdapter extends RecyclerView.Adapter<BookViewHolder>{
        private List<Book> mBooks;
        public BookAdapter(List<Book> books) {
            super();
            this.mBooks = books;
        }
        @NonNull
        @Override
        public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new BookViewHolder(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
            holder.bind(this.mBooks.get(position));

        }

        @Override
        public int getItemCount() {
            return this.mBooks.size();
        }
    }
    class BookViewHolder extends RecyclerView.ViewHolder {
        private TextView mBookTitle;
        private TextView mBookAuthor;

        public BookViewHolder(ViewGroup container) {
            super(LayoutInflater.from(BookListActivity.this).inflate(R.layout.book_list_item, container,
                    false));
            mBookTitle = (TextView) itemView.findViewById(R.id.book_title_tv);
            mBookAuthor = (TextView) itemView.findViewById(R.id.author_tv);
        }
        public void bind(Book book) {
            mBookTitle.setText(book.getmBookTitle());
            mBookAuthor.setText(book.getmBookAuthor());
        }


    }
}
class Book{
    private String mBookTitle;
    private String mBookAuthor;

    public String getmBookTitle() {
        return mBookTitle;
    }

    public void setmBookTitle(String mBookTitle) {
        this.mBookTitle = mBookTitle;
    }

    public String getmBookAuthor() {
        return mBookAuthor;
    }

    public void setmBookAuthor(String mBookAuthor) {
        this.mBookAuthor = mBookAuthor;
    }
}