package com.example.splashscreenapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{
    private ArrayList<MyListData> listdata;
    private Context context;

    // RecyclerView recyclerView;
    public MyListAdapter(ArrayList<MyListData> listdata, Context context) {
        this.listdata = listdata;
        this.context = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MyListData myListData = listdata.get(position);
        holder.textView.setText(listdata.get(position).getDescription());
        holder.infoImageView.setImageResource(listdata.get(position).getInfoImvId());
        holder.editImageView.setImageResource(listdata.get(position).getEditImvId());
        holder.editImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Modify the Text");
                builder.setCancelable(false);
                final EditText editText = new EditText(context);
                editText.setText(holder.textView.getText());
                editText.setGravity(Gravity.CENTER_HORIZONTAL);
                builder.setView(editText);
                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newEntry = editText.getText().toString();
                        myListData.setDescription(newEntry);
                        listdata.set(holder.getAdapterPosition(), myListData);
                        notifyItemChanged(holder.getAdapterPosition());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        holder.deleteImageView.setImageResource(listdata.get(position).getDeleteImvId());
        holder.deleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(holder.getAdapterPosition());
            }
        });
    }

    private void remove(int position){
        listdata.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView infoImageView;
        public ImageView deleteImageView;
        public ImageView editImageView;
        public TextView textView;
        public CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.infoImageView = (ImageView) itemView.findViewById(R.id.imageView);
            this.deleteImageView = (ImageView) itemView.findViewById(R.id.imv_delete);
            this.editImageView = (ImageView) itemView.findViewById(R.id.imv_edit);
            this.textView = (TextView) itemView.findViewById(R.id.textView);
            cardView = (CardView)itemView.findViewById(R.id.cardView);
        }
    }
}
