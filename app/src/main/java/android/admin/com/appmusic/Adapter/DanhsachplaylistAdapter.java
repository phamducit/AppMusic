package android.admin.com.appmusic.Adapter;

import android.admin.com.appmusic.Activity.DanhsachbaihatActivity;
import android.admin.com.appmusic.Model.Playlist;
import android.admin.com.appmusic.R;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhsachplaylistAdapter extends RecyclerView.Adapter<DanhsachplaylistAdapter.ViewHolder> {

    Context context;
    ArrayList<Playlist> playlistArrayList;

    public DanhsachplaylistAdapter(Context context, ArrayList<Playlist> playlistArrayList) {
        this.context = context;
        this.playlistArrayList = playlistArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danhsachplaylist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Playlist playlist = playlistArrayList.get(position);
        Picasso.with(context).load(playlist.getIcon()).into(holder.imvhinhnen);
        holder.tvtenplaylist.setText(playlist.getTen());
    }

    @Override
    public int getItemCount() {
        return playlistArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imvhinhnen;
        TextView tvtenplaylist;
        public ViewHolder(View itemView) {
            super(itemView);
            imvhinhnen = itemView.findViewById(R.id.imvdanhsachplaylist);
            tvtenplaylist = itemView.findViewById(R.id.tvdanhsachplaylist);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("itemplaylist",playlistArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
