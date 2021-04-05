package com.example.snl.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.snl.Model.IlanDetaySliderModel;
import com.example.snl.R;
import com.squareup.picasso.Picasso;

import java.util.List;
//ilan detay sayfamızda kullanmak için pager adapter ile extend ederiz,ilan resimleri için
public class SliderAdapter extends PagerAdapter {

    List<IlanDetaySliderModel> ilanDetaySliderModelList;
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(List<IlanDetaySliderModel> ilanDetaySliderModelList, Context context) {
        this.ilanDetaySliderModelList = ilanDetaySliderModelList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return ilanDetaySliderModelList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (RelativeLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) { //ilan resimleri üst üste basacağımız imageview cağırdık
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.ilandetayslider_layout,container,false);
        ImageView imageView = (ImageView) view.findViewById(R.id.sliderIlanDetayResim);

        Picasso.with(context).load("http://192.168.1.101:80/snldb_files/" + ilanDetaySliderModelList.get(position).getImage()).resize(1050,600).into(imageView);
      //veritabanındaki kayıtlın resimleri imageviewa cektik
        container.addView(view); //viewı containera ekledilk kalıp bu.
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            //Bunu yazmamaızın amacı pageAdapter kullandığımız için view mızı destroy etmesin diye içindeki super metodunu silerek slideradepter sayfamızda Override ettik.
    }
}
