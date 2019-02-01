package com.mylocarson.reservationapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mylocarson.reservationapp.R;
import com.mylocarson.reservationapp.models.tables.Table;
import com.mylocarson.reservationapp.utils.Preferences;

import java.util.ArrayList;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewHolder> {
    private ArrayList<Table> tables = new ArrayList<>();
    private Context context;
    private Table table;
    int numberofTable_notAvailable=0;


    public TableAdapter(ArrayList<Table> tables, Context context){
        this.tables = tables;
        this.context = context;

    }
    @NonNull
    @Override
    public TableAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TableAdapter.ViewHolder holder, int position) {
        table = tables.get(position);
        if (table.getIs_available().equalsIgnoreCase("true")) {
            holder.table_description.setText("More Info : "+table.getDescription());
            holder.table_name.setText(table.getName());

//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Preferences.saveSelectedTable(context,table);
//                }
//            });
        }



    }

    @Override
    public int getItemCount() {
        return tables.size() ;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView table_name, table_description;

        private ViewHolder(View itemView) {
            super(itemView);
            table_name = itemView.findViewById(R.id.table_name);
            table_description = itemView.findViewById(R.id.table_description);
        }
    }
}
