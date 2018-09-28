package android.admin.com.appmusic.Fragment;

import android.admin.com.appmusic.Activity.DanhsachbaihatActivity;
import android.admin.com.appmusic.Activity.DanhsachtatcachudeActivity;
import android.admin.com.appmusic.Activity.DanhsachtheloaitheochudeActivity;
import android.admin.com.appmusic.Model.ChuDe;
import android.admin.com.appmusic.Model.TheLoai;
import android.admin.com.appmusic.Model.Theloaitrongngay;
import android.admin.com.appmusic.R;
import android.admin.com.appmusic.Service.APIService;
import android.admin.com.appmusic.Service.Dataservice;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_ChuDe_TheLoai_Today extends Fragment {
    View view;
    HorizontalScrollView horizontalScrollView;
    TextView tvxemthemchudetheloai;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chude_theloai_today,container,false);
        horizontalScrollView = view.findViewById(R.id.horizontalscrollview);
        tvxemthemchudetheloai = view.findViewById(R.id.tvxemthemchudevatheloai);
        tvxemthemchudetheloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DanhsachtatcachudeActivity.class);
                startActivity(intent);
            }
        });
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<Theloaitrongngay> callback = dataservice.GetCategoryMusic();
        callback.enqueue(new Callback<Theloaitrongngay>() {
            @Override
            public void onResponse(Call<Theloaitrongngay> call, Response<Theloaitrongngay> response) {
                Theloaitrongngay theloaitrongngay = response.body();

                final ArrayList<ChuDe> chuDeArrayList = new ArrayList<>();
                chuDeArrayList.addAll(theloaitrongngay.getChuDe());

                final ArrayList<TheLoai> theLoaiArrayList = new ArrayList<>();
                theLoaiArrayList.addAll(theloaitrongngay.getTheLoai());

                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(550,230);
                layoutParams.setMargins(10,20,10,10);
                for(int i = 0; i < (chuDeArrayList.size()); i++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if(chuDeArrayList.get(i).getHinhchude() != null){
                        Picasso.with(getActivity()).load(chuDeArrayList.get(i).getHinhchude()).into(imageView);
                    }
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    final int finalI1 = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), DanhsachtheloaitheochudeActivity.class);
                            intent.putExtra("chude",chuDeArrayList.get(finalI1));
                            startActivity(intent);
                        }
                    });
                    final int finalI = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), DanhsachbaihatActivity.class);
                            intent.putExtra("idtheloai",theLoaiArrayList.get(finalI));
                            startActivity(intent);
                        }
                    });
                }
//                for(int j = 0; j < (theLoaiArrayList.size()); j++){
//                    CardView cardView = new CardView(getActivity());
//                    cardView.setRadius(10);
//                    ImageView imageView = new ImageView(getActivity());
//                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//                    if(theLoaiArrayList.get(j).getHinhtheloai() != null){
//                        Picasso.with(getActivity()).load(theLoaiArrayList.get(j).getHinhtheloai()).into(imageView);
//                    }
//                    cardView.setLayoutParams(layoutParams);
//                    cardView.addView(imageView);
//                    linearLayout.addView(cardView);
//                }
                horizontalScrollView.addView(linearLayout);
            }

            @Override
            public void onFailure(Call<Theloaitrongngay> call, Throwable t) {

            }
        });
    }
}
