package com.example.debtcontrol;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myViewHolder>{
    ArrayList<Model> dataHolder;

    public myAdapter(ArrayList<Model> dataHolder) {
        this.dataHolder = dataHolder;

    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        holder.names.setText(dataHolder.get(position).getName());
        holder.lists.setText(dataHolder.get(position).getItemList());
        holder.totals.setText(dataHolder.get(position).getTotal());

        //Object in Model
        Model s = dataHolder.get(position);

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Edit.class);
                intent.putExtra("id", s.getId());
                v.getContext().startActivity(intent);
            }
        });

        //set onclick listener to the delete btn
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //use od Dialog Builder
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext(), R.style.MyAlertDialogStyle);
                builder.setTitle("Delete Confirmation!");
                builder.setMessage("Confirm Delete...");
                builder.setCancelable(false);
                builder.setNegativeButton("no",null);
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseHelper dbHelper = new DatabaseHelper(v.getContext());
                        Boolean result = dbHelper.deleteUserdata(s.getId());
                        if(result == true) {
                            Toast.makeText(v.getContext(), "Delete Success", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(v.getContext(), Home.class);
                            v.getContext().startActivity(intent);

                        }else{
                            Toast.makeText(v.getContext(), "Delete Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.show();

            }
        });


    }

    @Override
    public int getItemCount() {
        return dataHolder.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder {

        TextView names,lists,totals;
        Button delete,edit;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            names = (TextView)itemView.findViewById(R.id.name);
            lists = (TextView)itemView.findViewById(R.id.list);
            totals = (TextView)itemView.findViewById(R.id.total);

            delete = (Button) itemView.findViewById(R.id.Delete);
            edit = (Button) itemView.findViewById(R.id.Edit);

        }
    }

}




