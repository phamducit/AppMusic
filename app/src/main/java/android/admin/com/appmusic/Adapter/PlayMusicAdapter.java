package android.admin.com.appmusic.Adapter;

import android.admin.com.appmusic.Model.Baihat;
import android.admin.com.appmusic.R;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class PlayMusicAdapter extends RecyclerView.Adapter<PlayMusicAdapter.ViewHolder> {
    Context context;
    ArrayList<Baihat> mangbaihat;

    public PlayMusicAdapter(Context context, ArrayList<Baihat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_playlist_music,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Baihat baihat = mangbaihat.get(position);
        holder.tvnamesong.setText(baihat.getTenbaihat());
        holder.tvtencasi.setText(baihat.getCasi());
        holder.tvindex.setText(position + 1 + "");
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvindex, tvnamesong, tvtencasi;
        public ViewHolder(View itemView) {
            super(itemView);
            tvtencasi = itemView.findViewById(R.id.tvplaymusictencasi);
            tvindex = itemView.findViewById(R.id.tvplaymusicindex);
            tvnamesong = itemView.findViewById(R.id.tvplaymusicnamesong);
        }
    }
}
