package com.openetizen.cevysays.opennews.fragments;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openetizen.cevysays.opennews.R;
import com.openetizen.cevysays.opennews.adapters.CategoryOneAdapter;
import com.openetizen.cevysays.opennews.models.CategoryOneItem;
import com.twotoasters.jazzylistview.JazzyHelper;
import com.twotoasters.jazzylistview.JazzyListView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class CategoryOneFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ArrayList<CategoryOneItem> categoryOneItemArrayList;
    private JazzyListView listView;


    public CategoryOneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category_one, container, false);

        listView = (JazzyListView) view.findViewById(R.id.list);
        listView.setTransitionEffect(JazzyHelper.GROW);
        listView.setOnItemClickListener(this);
        new DownloadData().execute();
        return view;
//        return inflater.inflate(R.layout.fragment_category_one, container, false);

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    private class DownloadData extends AsyncTask<Void, Void, Void> {
                ProgressDialog progressDialog;
        @Override
        protected void onPreExecute(){
//            super.onPreExecute();
//            progressDialog = new ProgressDialog(CategoryOneFragment.this);
//            progressDialog.setMessage("Mendownload data...");
//            progressDialog.show();

        }
        @Override
        protected Void doInBackground(Void... params) {
            ObjectMapper mapper = new ObjectMapper();
            categoryOneItemArrayList = null;
            while (categoryOneItemArrayList == null) {
                try {
                    categoryOneItemArrayList = mapper.readValue(new URL(CategoryOneItem.API),
                            mapper.getTypeFactory().constructCollectionType(ArrayList.class, CategoryOneAdapter.class));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            CategoryOneAdapter adapter = new CategoryOneAdapter(categoryOneItemArrayList, CategoryOneFragment.this);
            listView.setAdapter(adapter);

//            progressDialog.dismiss();
//            AgendaAdapter adapter = new AgendaAdapter(AgendaActivity.this, agendaArrayList);
//            adapter.SetOnItemClickListener(new AgendaAdapter().OnItemClickListener() {
//                @Override
//                public void onItemClick(View view, int position) {
//                    Intent intent = new Intent(AgendaActivity.this, DetailAgendaActivity.class);
//                    intent.putExtra("agenda", agendaArrayList.get(position));
//                    startActivity(intent);
//                }
//            });


        }
    }
}
