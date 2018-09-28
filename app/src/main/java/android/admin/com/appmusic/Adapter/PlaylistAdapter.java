package android.admin.com.appmusic.Adapter;

import android.admin.com.appmusic.Model.Playlist;
import android.admin.com.appmusic.R;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PlaylistAdapter extends ArrayAdapter<Playlist> {
    public PlaylistAdapter(@NonNull Context context, int resource, @NonNull List<Playlist> objects) {
        super(context, resource, objects);
    }
    class ViewHolder{
        TextView tvtenplaylist;
        ImageView imvbackground, imvplaylist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.dong_playlist,null);
            viewHolder = new ViewHolder();
            viewHolder.tvtenplaylist = convertView.findViewById(R.id.tvtenplaylist);
            viewHolder.imvbackground = convertView.findViewById(R.id.imvbackgroundplaylist);
            viewHolder.imvplaylist = convertView.findViewById(R.id.imvplaylist);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Playlist playlist = getItem(position);
        Picasso.with(getContext()).load(playlist.getHinh()).into(viewHolder.imvbackground);
        Picasso.with(getContext()).load(playlist.getIcon()).into(viewHolder.imvplaylist);
        viewHolder.tvtenplaylist.setText(playlist.getTen());
        return convertView;
    }
}
