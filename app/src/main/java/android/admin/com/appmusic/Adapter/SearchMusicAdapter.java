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

public class SearchMusicAdapter extends RecyclerView.Adapter<SearchMusicAdapter.ViewHolder>  {
    Context context;
    ArrayList<Baihat> mangbaihat;

    public SearchMusicAdapter(Context context, ArrayList<Baihat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.dong_search_song, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Baihat baihat = mangbaihat.get(position);
        holder.tvtenbaihat.setText(baihat.getTenbaihat());
        holder.tvcasi.setText(baihat.getCasi());
        Picasso.with(context).load(baihat.getHinhbaihat()).into(holder.imvbaihat);
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvtenbaihat, tvcasi;
        ImageView imvbaihat, imvthich;
        public ViewHolder(View itemView) {
            super(itemView);
            tvtenbaihat = itemView.findViewById(R.id.tvSearchNameSong);
            tvcasi = itemView.findViewById(R.id.tvSearchCasi);
            imvbaihat = itemView.findViewById(R.id.imvSearch);
            imvthich = itemView.findViewById(R.id.imvSearchluotthich);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayMusicActivity.class);
                    intent.putExtra("nhac",mangbaihat.get(getPosition()));
                    context.startActivity(intent);
                }
            });
            imvbaihat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imvthich.setImageResource(R.drawable.iconloved);
                    Dataservice dataservice = APIService.getService();
                    Call<String> callback = dataservice.UpdateLuotThich("1",mangbaihat.get(getPosition()).getIdbaihat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if(ketqua.equals("Success")){
                                Toast.makeText(context, "Đã Thích", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
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
