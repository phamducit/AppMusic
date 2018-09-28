package android.admin.com.appmusic.Fragment;

import android.admin.com.appmusic.Activity.PlayMusicActivity;
import android.admin.com.appmusic.Adapter.PlayMusicAdapter;
import android.admin.com.appmusic.R;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment_Playlist_Playmusic extends Fragment {
    View view;
    RecyclerView recyclerViewplaylistmusic;
    PlayMusicAdapter playMusicAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playlist_playmusic,container,false);
        recyclerViewplaylistmusic = view.findViewById(R.id.recyclerviewplaylistmusic);
        if(PlayMusicActivity.mangbaihat.size() > 0){
            playMusicAdapter = new PlayMusicAdapter(getActivity(),PlayMusicActivity.mangbaihat);
            recyclerViewplaylistmusic.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerViewplaylistmusic.setAdapter(playMusicAdapter);
        }
        return view;
    }
}
