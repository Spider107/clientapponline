package com.aungzinmoe.moviesworld;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SeriesFragment extends Fragment {


    public SeriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_series, container, false);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        final RecyclerView newseries=view.findViewById(R.id.newseries);
        final RecyclerView allseries=view.findViewById(R.id.allseries);
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        CollectionReference Ref=db.collection("series");
        Ref.whereEqualTo("seriesCagegory","NewSeries").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                ArrayList<SeriesModel> seriesModels=new ArrayList<SeriesModel>();
                for (DocumentSnapshot snapshot:queryDocumentSnapshots){
                    seriesModels.add(snapshot.toObject(SeriesModel.class));
                }

                SeriesRecyclerAdapter adapter=new SeriesRecyclerAdapter(seriesModels,getContext(),getLayoutInflater());
                newseries.setAdapter(adapter);
                LinearLayoutManager lm=new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
                newseries.setLayoutManager(lm);

            }
        });
        Ref.whereEqualTo("seriesCagegory","AllSeries").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                ArrayList<SeriesModel> seriesModels=new ArrayList<SeriesModel>();
                for (DocumentSnapshot snapshot:queryDocumentSnapshots){
                    seriesModels.add(snapshot.toObject(SeriesModel.class));
                }

                SeriesRecyclerAdapter adapter=new SeriesRecyclerAdapter(seriesModels,getContext(),getLayoutInflater());
                allseries.setAdapter(adapter);
                LinearLayoutManager lm=new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
                allseries .setLayoutManager(lm);

            }
        });
        AdView mAdView1 = view.findViewById(R.id.adView1);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView1.loadAd(adRequest);

        AdView mAdView2 = view.findViewById(R.id.adView2);
        adRequest = new AdRequest.Builder().build();
        mAdView2.loadAd(adRequest);


        return view;
    }

}
