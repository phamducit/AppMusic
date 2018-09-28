package android.admin.com.appmusic.Activity;

import android.admin.com.appmusic.Adapter.ViewPagerPlaylistMusicAdapter;
import android.admin.com.appmusic.Fragment.Fragment_DiaNhac;
import android.admin.com.appmusic.Fragment.Fragment_Playlist_Playmusic;
import android.admin.com.appmusic.Model.Baihat;
import android.admin.com.appmusic.R;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.rtp.AudioStream;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlayMusicActivity extends AppCompatActivity {
    Toolbar toolbarplaymusic;
    TextView tvtimesong,tvtotaltimesong;
    SeekBar sktime;
    ImageButton imvplay,imvrep,imvprev,imvnext,imvrandom;
    ViewPager viewPagerplaymusic;
    public static ArrayList<Baihat> mangbaihat = new ArrayList<>();
    public static ViewPagerPlaylistMusicAdapter viewPagerPlaylistMusicAdapter;
    Fragment_DiaNhac fragment_diaNhac;
    Fragment_Playlist_Playmusic fragment_playlist_playmusic;
    MediaPlayer mediaPlayer;
    int position = 0;
    boolean repeat = false;
    boolean checkrandomm = false;
    boolean next = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        GetDataFromIntent();
        init();
        eventClick();
    }

    private void eventClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(viewPagerPlaylistMusicAdapter.getItem(1)!=null){
                    if (mangbaihat.size()>0){
                        fragment_diaNhac.PlayNhac(mangbaihat.get(0).getHinhbaihat());
                        handler.removeCallbacks(this);
                    }else {
                        handler.postDelayed(this,300);
                    }
                }
            }
        },500);
        imvplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imvplay.setImageResource(R.drawable.iconplay);
                }
            }
        });
        imvrep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repeat == false){
                    if (checkrandomm == true){
                        checkrandomm = false;
                        imvrep.setImageResource(R.drawable.iconsyned);
                        imvrandom.setImageResource(R.drawable.iconsuffle);
                    }
                    imvrep.setImageResource(R.drawable.iconsyned);
                    repeat = true;
                }else {
                    imvrep.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                }
            }
        });
        imvrandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkrandomm == false){
                    if (repeat == true){
                        repeat = false;
                        imvrandom.setImageResource(R.drawable.iconshuffled);
                        imvrep.setImageResource(R.drawable.iconrepeat);
                    }
                    imvrandom.setImageResource(R.drawable.iconshuffled);
                    checkrandomm = true;
                }else {
                    imvrandom.setImageResource(R.drawable.iconsuffle);
                    checkrandomm = false;
                }
            }
        });
        sktime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        imvnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mangbaihat.size() > 0 ){
                    if(mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (mangbaihat.size())){
                        imvplay.setImageResource(R.drawable.iconpause);
                        position++;
                        if(repeat == true){
                            if(position == 0){
                                position = mangbaihat.size();
                            }
                            position -= 1;
                        }
                        if(checkrandomm == true){
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if(index == position){
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > (mangbaihat.size() - 1)){
                            position = 0;
                        }
                        new PlayMp3().execute(mangbaihat.get(position).getLinkbaihat());
                        fragment_diaNhac.PlayNhac(mangbaihat.get(position).getHinhbaihat());
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenbaihat());
                    }
                }
                imvprev.setClickable(false);
                imvnext.setClickable(false);
                Handler handler1 = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imvprev.setClickable(true);
                        imvnext.setClickable(true);
                    }
                },5000);
            }
        });
        imvprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mangbaihat.size() > 0 ){
                    if(mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (mangbaihat.size())){
                        imvplay.setImageResource(R.drawable.iconpause);
                        position--;

                        if(position < 0){
                            position = mangbaihat.size() - 1;

                        }

                        if(repeat == true){
                            position += 1;
                        }
                        if(checkrandomm == true){
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if(index == position){
                                position = index - 1;
                            }
                            position = index;
                        }
                        new PlayMp3().execute(mangbaihat.get(position).getLinkbaihat());
                        fragment_diaNhac.PlayNhac(mangbaihat.get(position).getHinhbaihat());
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenbaihat());
                        UpdateTime();
                    }
                }
                imvprev.setClickable(false);
                imvnext.setClickable(false);
                Handler handler1 = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imvprev.setClickable(true);
                        imvnext.setClickable(true);
                    }
                },5000);
            }
        });
    }

    private void GetDataFromIntent() {
        Intent intent = getIntent();
        mangbaihat.clear();
        if (intent != null){
            if(intent.hasExtra("nhac")){
                Baihat baihat = intent.getParcelableExtra("nhac");
                mangbaihat.add(baihat);
            }
            if(intent.hasExtra("cacbaihat")){
                ArrayList<Baihat> baihatArrayList = intent.getParcelableArrayListExtra("cacbaihat");
                mangbaihat = baihatArrayList;
            }
        }


    }

    private void init() {
        toolbarplaymusic = findViewById(R.id.toolbarplaymusic);
        tvtimesong = findViewById(R.id.tvtimesong);
        tvtotaltimesong = findViewById(R.id.tvtotaltimesong);
        sktime = findViewById(R.id.seekbarsong);
        imvplay = findViewById(R.id.imvbtnplay);
        imvnext = findViewById(R.id.imvbtnnext);
        imvprev = findViewById(R.id.imvbtnprev);
        imvrandom = findViewById(R.id.imvbtnsuffle);
        imvrep = findViewById(R.id.imvbtnrepeat);
        viewPagerplaymusic = findViewById(R.id.viewpagerplaymusic);
        setSupportActionBar(toolbarplaymusic);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Play Music");
        toolbarplaymusic.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                mediaPlayer.stop();
                mangbaihat.clear();
            }
        });
        toolbarplaymusic.setTitleTextColor(Color.WHITE);
        fragment_diaNhac = new Fragment_DiaNhac();
        fragment_playlist_playmusic = new Fragment_Playlist_Playmusic();
        viewPagerPlaylistMusicAdapter = new ViewPagerPlaylistMusicAdapter(getSupportFragmentManager());
        viewPagerPlaylistMusicAdapter.AddFragment(fragment_playlist_playmusic);
        viewPagerPlaylistMusicAdapter.AddFragment(fragment_diaNhac);
        viewPagerplaymusic.setAdapter(viewPagerPlaylistMusicAdapter);
        fragment_diaNhac = (Fragment_DiaNhac) viewPagerPlaylistMusicAdapter.getItem(1);
        if(mangbaihat.size() > 0 ){
            getSupportActionBar().setTitle(mangbaihat.get(0).getTenbaihat());
            new PlayMp3().execute(mangbaihat.get(0).getLinkbaihat());
            imvplay.setImageResource(R.drawable.iconpause);
        }
    }
    class PlayMp3 extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                }
            });
            mediaPlayer.setDataSource(baihat);
            mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
            UpdateTime();
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        tvtotaltimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        sktime.setMax(mediaPlayer.getDuration());
    }
    private void UpdateTime(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer != null){
                    sktime.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    tvtimesong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 300);
                    mediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
                        @Override
                        public void onSeekComplete(MediaPlayer mp) {
                            next = true;
                            try{
                                Thread.sleep(1000);
                            }catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                }
            }
        },300);
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(next == true){
                    if(mangbaihat.size() > 0 ){
                        if(mediaPlayer.isPlaying() || mediaPlayer != null){
                            mediaPlayer.stop();
                            mediaPlayer.release();
                            mediaPlayer = null;
                        }
                        if (position < (mangbaihat.size())){
                            imvplay.setImageResource(R.drawable.iconpause);
                            position++;
                            if(repeat == true){
                                if(position == 0){
                                    position = mangbaihat.size();
                                }
                                position -= 1;
                            }
                            if(checkrandomm == true){
                                Random random = new Random();
                                int index = random.nextInt(mangbaihat.size());
                                if(index == position){
                                    position = index - 1;
                                }
                                position = index;
                            }
                            if (position > (mangbaihat.size() - 1)){
                                position = 0;
                            }
                            new PlayMp3().execute(mangbaihat.get(position).getLinkbaihat());
                            fragment_diaNhac.PlayNhac(mangbaihat.get(position).getHinhbaihat());
                            getSupportActionBar().setTitle(mangbaihat.get(position).getTenbaihat());
                        }
                    }
                    imvprev.setClickable(false);
                    imvnext.setClickable(false);
                    Handler handler1 = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imvprev.setClickable(true);
                            imvnext.setClickable(true);
                        }
                    },5000);
                    next = false;
                    handler1.removeCallbacks(this);
                }else {
                    handler1.postDelayed(this,1000);
                }
            }
        },1000);
    }
}
