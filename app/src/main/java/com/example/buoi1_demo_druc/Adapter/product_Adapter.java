package com.example.buoi1_demo_druc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.buoi1_demo_druc.DTO.Product_DTO;
import com.example.buoi1_demo_druc.R;

import java.util.ArrayList;

public class product_Adapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<Product_DTO>list;

    public product_Adapter(Context context, int layout, ArrayList<Product_DTO> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(layout,null);
        TextView txtten =view.findViewById(R.id.ten);
        TextView txtgia =view.findViewById(R.id.gia);
        TextView txtid_cat =view.findViewById(R.id.idcat);
        TextView txtid=view.findViewById(R.id.id);
        txtid.setText("id: "+list.get(i).getId()+" ");
        txtten.setText("name: "+list.get(i).getName());
        txtgia.setText("gia: "+list.get(i).getPricae()+" ");
        txtid_cat.setText("id_cat: "+list.get(i).getIdcat()+" ");

        return view;
    }
}
