package android.admin.com.appmusic.Activity;


import android.admin.com.appmusic.Adapter.DanhsachplaylistAdapter;
import android.admin.com.appmusic.Model.Playlist;
import android.admin.com.appmusic.R;
import android.admin.com.appmusic.Service.APIService;
import android.admin.com.appmusic.Service.Dataservice;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DanhsachplaylistActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerViewdanhsachplaylist;
    DanhsachplaylistAdapter danhsachplaylistAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachplaylist);
        AnhXa();
        Init();
        GetData();
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Playlist>> callback = dataservice.GetDanhsachcacplaylist();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                ArrayList<Playlist> mangPlaylist = (ArrayList<Playlist>) response.body();
                danhsachplaylistAdapter = new DanhsachplaylistAdapter(DanhsachplaylistActivity.this,mangPlaylist);
                recyclerViewdanhsachplaylist.setLayoutManager(new GridLayoutManager(DanhsachplaylistActivity.this,2));
                recyclerViewdanhsachplaylist.setAdapter(danhsachplaylistAdapter);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }

    private void Init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Playlists");
        toolbar.setTitleTextColor(Color.BLACK);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        toolbar = findViewById(R.id.toolbardanhsachplaylist);
        recyclerViewdanhsachplaylist = findViewById(R.id.recyclerviewdanhsachplaylist);
    }
}
