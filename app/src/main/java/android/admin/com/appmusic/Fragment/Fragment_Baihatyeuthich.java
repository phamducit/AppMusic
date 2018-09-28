package android.admin.com.appmusic.Fragment;

import android.admin.com.appmusic.Adapter.BaihatyeuthichAdapter;
import android.admin.com.appmusic.Model.Baihat;
import android.admin.com.appmusic.R;
import android.admin.com.appmusic.Service.APIService;
import android.admin.com.appmusic.Service.Dataservice;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Baihatyeuthich extends Fragment {
    View view;
    RecyclerView recyclerViewbaihatyeuthich;
    BaihatyeuthichAdapter baihatyeuthichAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_baihat_yeuthich,container,false);
        recyclerViewbaihatyeuthich = view.findViewById(R.id.recyclerviewbaihatyeuthich);
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.GetBaiHatHot();
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                ArrayList<Baihat> baihatArrayList = (ArrayList<Baihat>) response.body();
                baihatyeuthichAdapter = new BaihatyeuthichAdapter(getActivity(),baihatArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewbaihatyeuthich.setLayoutManager(linearLayoutManager);
                recyclerViewbaihatyeuthich.setAdapter(baihatyeuthichAdapter);
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }
}
