package com.example.buoi1_demo_druc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buoi1_demo_druc.DTO.CatDTO;
import com.example.buoi1_demo_druc.Dao.catDao;
import com.example.buoi1_demo_druc.R;

import java.util.ArrayList;

public class cat_Adapter extends RecyclerView.Adapter<cat_Adapter.ViewHolder> {
    catDao catDao;
    Context context;
    ArrayList<CatDTO>listcat;

    public cat_Adapter(Context context, ArrayList<CatDTO> listcat) {
        this.context = context;
        this.listcat = listcat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=inflater.inflate(R.layout.rowcat,null);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_id.setText(listcat.get(position).getId()+"");
        holder.tv_name.setText(listcat.get(position).getName());

//        if (listcat.get(position).getName()==1){
//            holder.chktem.setChecked(true);
//
//        }
        holder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        int  id =listcat.get(holder.getAdapterPosition()).getId();
//        catDao=new catDao(cat_Adapter.this);
        boolean check=  catDao.removeToto(id);
        if (check){

            Toast.makeText(context, "xoa thanh cong", Toast.LENGTH_SHORT).show();
            listcat.clear();
            listcat=catDao.getAll();
            notifyItemRemoved(holder.getAdapterPosition());
        }else{
            Toast.makeText(context, "xoa that bai", Toast.LENGTH_SHORT).show();
        }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listcat.size();
    }
//    Context context;
//    int layout;
//    ArrayList<CatDTO> list;
//
//    public cat_Adapter(Context context, int layout, ArrayList<CatDTO> list) {
//        this.context = context;
//        this.layout = layout;
//        this.list = list;
//    }
//
//    @Override
//    public int getCount() {
//        return list.size();
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return list.get(i);
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return i;
//    }
//
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        view=inflater.inflate(layout,null);
//
//        TextView txtten=view.findViewById(R.id.txtten);
//        TextView txtid=view.findViewById(R.id.txtid);
//        txtid.setText("id:"+list.get(i).getId()+"");
//        txtten.setText("name: "+list.get(i).getName());
//
//        return view;
//    }

    public static class  ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_id,tv_name;
        CheckBox chktem;
        ImageView btnupdate ,btndelete;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_id=itemView.findViewById(R.id.tv_id);
        tv_name=itemView.findViewById(R.id.tv_ten);
        btndelete=itemView.findViewById(R.id.img_delete);
        btnupdate=itemView.findViewById(R.id.img_edit);
        chktem=itemView.findViewById(R.id.chk_item);
    }
}
}
