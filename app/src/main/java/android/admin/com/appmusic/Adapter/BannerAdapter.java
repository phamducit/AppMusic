package android.admin.com.appmusic.Adapter;

import android.admin.com.appmusic.Activity.DanhsachbaihatActivity;
import android.admin.com.appmusic.Model.Quangcao;
import android.admin.com.appmusic.R;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BannerAdapter extends PagerAdapter {
    Context context;
    ArrayList<Quangcao> arrayBanner;

    public BannerAdapter(Context context, ArrayList<Quangcao> arrayBanner) {
        this.context = context;
        this.arrayBanner = arrayBanner;
    }

    @Override
    public int getCount() {
        return arrayBanner.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_banner,null);

        ImageView imvbackgroundbanner = view.findViewById(R.id.imvbackgroundbanner);
        ImageView imvbanner = view.findViewById(R.id.imvbanner);
        TextView tvtitlebanner = view.findViewById(R.id.tvtitlebannerbaihat);
        TextView tvnoidung = view.findViewById(R.id.tvnoidung);

        Picasso.with(context).load(arrayBanner.get(position).getHinhanh()).into(imvbackgroundbanner);
        Picasso.with(context).load(arrayBanner.get(position).getHinhBaiHat()).into(imvbanner);
        tvtitlebanner.setText(arrayBanner.get(position).getTenBaiHat());
        tvnoidung.setText(arrayBanner.get(position).getNoidung());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                intent.putExtra("banner",arrayBanner.get(position));
                context.startActivity(intent);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
