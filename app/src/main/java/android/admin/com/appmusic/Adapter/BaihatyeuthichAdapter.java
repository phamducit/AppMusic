package android.admin.com.appmusic.Adapter;

import android.admin.com.appmusic.Activity.PlayMusicActivity;
import android.admin.com.appmusic.Model.Baihat;
import android.admin.com.appmusic.R;
import android.admin.com.appmusic.Service.APIService;
import android.admin.com.appmusic.Service.Dataservice;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaihatyeuthichAdapter extends RecyclerView.Adapter<BaihatyeuthichAdapter.ViewHolder> {
    Context context;
    ArrayList<Baihat> baihatArrayList;

    public BaihatyeuthichAdapter(Context context, ArrayList<Baihat> baihatArrayList) {
        this.context = context;
        this.baihatArrayList = baihatArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_baihat_yeuthich,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Baihat baihat = baihatArrayList.get(position);
        holder.tvten.setText(baihat.getTenbaihat());
        holder.tvcasi.setText(baihat.getCasi());
        Picasso.with(context).load(baihat.getHinhbaihat()).into(holder.imvhinh);
    }

    @Override
    public int getItemCount() {
        return baihatArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvten, tvcasi;
        ImageView imvhinh, imvthich;
        public ViewHolder(View itemView) {
            super(itemView);
            tvten = itemView.findViewById(R.id.tvtenbaihatyeuthich);
            tvcasi = itemView.findViewById(R.id.tvcasibaihatyeuthich);
            imvhinh = itemView.findViewById(R.id.imvbaihatyeuthich);
            imvthich = itemView.findViewById(R.id.imvthich);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayMusicActivity.class);
                    intent.putExtra("nhac",baihatArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
            imvthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imvthich.setImageResource(R.drawable.iconloved);
                    Dataservice dataservice = APIService.getService();
                    Call<String> callback = dataservice.UpdateLuotThich("1",baihatArrayList.get(getPosition()).getIdbaihat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if(ketqua.equals("Success")){
                                Toast.makeText(context, "Đã thích", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, "Lỗi!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                        }
                    });
                    imvthich.setEnabled(false);
                }
            });
        }
    }
}
