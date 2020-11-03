package com.example.pk01537_duy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class baiHatAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<BaiHat> baiHatList;

    public baiHatAdapter(Context context, int layout, List<BaiHat> baiHatList) {
        this.context = context;
        this.layout = layout;
        this.baiHatList = baiHatList;
    }

    @Override
    public int getCount() {
        return baiHatList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{

        TextView txt_ten, txt_tac, txt_ngay, txt_loi;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.txt_ten = view.findViewById(R.id.txt_tenBaiHat);
            holder.txt_loi = view.findViewById(R.id.txt_loiBaiHat);
            holder.txt_tac = view.findViewById(R.id.txt_tacGia);
            holder.txt_ngay = view.findViewById(R.id.txt_ngayGui);
            view.setTag(holder);


        }else {
            holder = (ViewHolder) view.getTag();
        }

        final BaiHat baiHat = baiHatList.get(i);
        holder.txt_ten.setText(baiHat.getTen());
        holder.txt_tac.setText(baiHat.getTac());
        holder.txt_loi.setText(baiHat.getLoi());
        holder.txt_ngay.setText(baiHat.getNgay());

        return view;
    }
}
