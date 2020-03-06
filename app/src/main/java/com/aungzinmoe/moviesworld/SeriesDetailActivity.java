package com.aungzinmoe.moviesworld;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class SeriesDetailActivity extends AppCompatActivity {

   static SeriesModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_detail);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        ImageView imageView=findViewById(R.id.series_image);
        TextView seriesName=findViewById(R.id.series_name);
        Glide.with(getApplicationContext())
                .load(model.seriesImage)
                .into(imageView);
        seriesName.setText(model.seriesName);
        EpisodeRecyclerAdapter.activity=this;
        final RecyclerView episode_list=findViewById(R.id.episode_list);
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        CollectionReference movieRef=db.collection("movies");
        movieRef.whereEqualTo("movieSeries",model.seriesName).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                ArrayList<MovieModel> episodes=new ArrayList<>();
                for (DocumentSnapshot snapshot:queryDocumentSnapshots)
                {
                    episodes.add(snapshot.toObject(MovieModel.class));
                }
                EpisodeRecyclerAdapter adapter=new EpisodeRecyclerAdapter(episodes,getApplicationContext());
                LinearLayoutManager lm=new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
                episode_list.setAdapter(adapter);
                episode_list.setLayoutManager(lm);
            }
        });
    }
}
