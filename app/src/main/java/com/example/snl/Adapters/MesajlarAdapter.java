package com.example.snl.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.snl.ActivityChat;
import com.example.snl.Model.AccountModel;
import com.example.snl.OtherId;
import com.example.snl.R;
import com.example.snl.Retrofit.ApiClient;
import com.example.snl.Retrofit.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
//Bu adapterimizi mesajları listelemek  için kullanırız.
public class MesajlarAdapter extends BaseAdapter {
    List<String> otherIdList;//string otherıd liste döndür
    String userId;
    Context context;
    ApiInterface apiInterface;
    Activity activity;

    public MesajlarAdapter(List<String> otherIdList, String userId, Context context, Activity activity) {
        this.otherIdList = otherIdList;
        this.userId = userId;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return otherIdList.size(); //Listemizin sizenı verdik.
    }

    @Override
    public Object getItem(int position) {
        return otherIdList.get(position); //listemizin positionını döndürdük.
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.other, parent, false); //layoutumuzu tanımladık.
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        TextView textView = (TextView) convertView.findViewById(R.id.txtOther);
        getUser(otherIdList.get(position),textView);//method cağırdık
        LinearLayout linearOtherLayout = (LinearLayout)convertView.findViewById(R.id.linearOtherLayout);
        linearOtherLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ActivityChat.class);
                OtherId.setOtherId(otherIdList.get(position));//tıkladıgın an bizim otherlistimizden dönen idyi set eder
                activity.startActivity(intent);
            }
        });
        return convertView;
    }

    public void getUser(String memberId, TextView textView){
        Call<AccountModel> request = apiInterface.account(memberId); //memberıdye göre cağırdık
        request.enqueue(new Callback<AccountModel>() {
            @Override
            public void onResponse(Call<AccountModel> call, Response<AccountModel> response) {
                if (response.isSuccessful()){
                   textView.setText(response.body().getEmail());
                }
            }

            @Override
            public void onFailure(Call<AccountModel> call, Throwable t) {
                Log.i("My Tag : ","Hata : "+t);
            }
        });
    }
}
