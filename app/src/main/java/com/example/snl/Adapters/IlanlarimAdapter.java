package com.example.snl.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.snl.Model.IlanlarimModel;
import com.example.snl.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IlanlarimAdapter extends BaseAdapter {

    List<IlanlarimModel> ilanlarimModelList; //ilanlarım modeli list şeklinde döndürdük
    Context context;
    Activity activity; //fragmenttan gelen activy burada tanımladık
    String memberId,advertisementId;

    public IlanlarimAdapter(List<IlanlarimModel> ilanlarimModelList, Context context, Activity activity) {
        this.ilanlarimModelList = ilanlarimModelList;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return ilanlarimModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return ilanlarimModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.ilanlarim_layout,parent,false); //alt görünümü hemen üst görünüme ekleme daha sonra ekle
        ImageView imgIlanResim;
        TextView lblIlanBaslik,lblIlanAciklama,lblIlanFiyat;
        imgIlanResim = (ImageView)convertView.findViewById(R.id.imgIlanlarimIlanResim);
        lblIlanBaslik = (TextView)convertView.findViewById(R.id.lblIlanBaslik);
        lblIlanAciklama = (TextView)convertView.findViewById(R.id.lblIlanAciklama);
        lblIlanFiyat = (TextView)convertView.findViewById(R.id.lblIlanFiyat);

        lblIlanBaslik.setText(ilanlarimModelList.get(position).getTitle());
        lblIlanAciklama.setText(ilanlarimModelList.get(position).getDescription());
        lblIlanFiyat.setText(ilanlarimModelList.get(position).getPrice());

        advertisementId = ilanlarimModelList.get(position).getAdvertisementId();//listemizin ilgili satırından dönen ilanid ve üyeid aldık
        memberId = ilanlarimModelList.get(position).getMemberId();




        Picasso.with(context).load("http://192.168.1.101:80/snldb_files/" + ilanlarimModelList.get(position).getImage()).resize(100,100).into(imgIlanResim);
        //Log.wtf("Testing valid URL", "|"+ "http://192.168.1.2:80/snldb_files/"+ilanlarimModelList.get(position).getImage()+"|");

        return convertView;
    }
}
