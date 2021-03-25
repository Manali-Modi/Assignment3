package com.example.assignment3;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment3.model.BookData;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookHolder> {

    ArrayList<BookData> bookDataList;
    ArrayList<BookData> bookDataListFull;
    Context ctx;
    AlertDialog.Builder ad;
    private final Filter bookFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<BookData> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(bookDataListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (BookData item : bookDataListFull) {
                    if (item.getBookName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;

        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            bookDataList.clear();
            bookDataList.addAll((List) filterResults.values);
            notifyDataSetChanged();

        }
    };

    public BookAdapter(Context ct, ArrayList<BookData> bookDataList) {
        ctx = ct;
        this.bookDataList = bookDataList;
        bookDataListFull = new ArrayList<>(bookDataList);
    }

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.book_row, parent, false);
        return new BookHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder holder, int position) {
        holder.txtbname.setText(bookDataList.get(position).bookName);
        holder.txtaname.setText(bookDataList.get(position).authorName);
    }

    @Override
    public int getItemCount() {
        return bookDataList.size();
    }

    public Filter getFilter() {
        return bookFilter;
    }


    public class BookHolder extends RecyclerView.ViewHolder {
        TextView txtaname, txtbname;
        ImageView imgview;
        TextView vbname, vaname, vgenre, vtype, vdate, vage, vage2, vage3, vage4;

        public BookHolder(@NonNull View itemView) {
            super(itemView);
            txtaname = itemView.findViewById(R.id.txtaname);
            txtbname = itemView.findViewById(R.id.txtbname);
            imgview = itemView.findViewById(R.id.imgview);
            ad = new AlertDialog.Builder(ctx);

            imgview.setOnClickListener(view -> {
                Log.d("msg", "onclick");
                LayoutInflater inflater = LayoutInflater.from(ctx);
                View viewbook = inflater.inflate(R.layout.view_book, null);

                vbname = viewbook.findViewById(R.id.vbname);
                vaname = viewbook.findViewById(R.id.vaname);
                vgenre = viewbook.findViewById(R.id.vgenre);
                vtype = viewbook.findViewById(R.id.vtype);
                vdate = viewbook.findViewById(R.id.vdate);
                vage = viewbook.findViewById(R.id.vage);
                vage2 = viewbook.findViewById(R.id.vage2);
                vage3 = viewbook.findViewById(R.id.vage3);
                vage4 = viewbook.findViewById(R.id.vage4);

                vbname.setText(bookDataList.get(getAdapterPosition()).bookName);
                vaname.setText(bookDataList.get(getAdapterPosition()).authorName);
                vgenre.setText(bookDataList.get(getAdapterPosition()).bookGenre);
                vtype.setText(bookDataList.get(getAdapterPosition()).bookType);
                vdate.setText(bookDataList.get(getAdapterPosition()).launchDate);
                if (bookDataList.get(getAdapterPosition()).ageGrpOne == null) {
                    vage.setVisibility(View.INVISIBLE);
                } else
                    vage.setText(bookDataList.get(getAdapterPosition()).ageGrpOne);

                if (bookDataList.get(getAdapterPosition()).ageGrpTwo == null) {
                    vage2.setVisibility(View.INVISIBLE);
                } else
                    vage2.setText(bookDataList.get(getAdapterPosition()).ageGrpTwo);

                if (bookDataList.get(getAdapterPosition()).ageGrpThree == null) {
                    vage3.setVisibility(View.INVISIBLE);
                } else
                    vage3.setText(bookDataList.get(getAdapterPosition()).ageGrpThree);

                if (bookDataList.get(getAdapterPosition()).ageGrpFour == null) {
                    vage4.setVisibility(View.INVISIBLE);
                } else
                    vage4.setText(bookDataList.get(getAdapterPosition()).ageGrpFour);

                ad.setView(viewbook);
                ad.setPositiveButton("OK", (dialogInterface, i) -> {

                });

                ad.setNeutralButton("Edit", (dialogInterface, i) -> {
                    Toast.makeText(ctx, "Edit clicked", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ctx, Edit.class);
                    intent.putExtra("POSITION", getAdapterPosition());
                    ctx.startActivity(intent);
                });

                ad.create().show();
            });
        }
    }
}
