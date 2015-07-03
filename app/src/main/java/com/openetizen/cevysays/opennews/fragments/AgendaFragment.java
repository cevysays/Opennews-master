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
import com.openetizen.cevysays.opennews.activity.MainActivity;
import com.openetizen.cevysays.opennews.adapters.AgendaAdapter;
import com.openetizen.cevysays.opennews.models.AgendaItem;
import com.twotoasters.jazzylistview.JazzyHelper;
import com.twotoasters.jazzylistview.JazzyListView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AgendaFragment extends Fragment implements AdapterView.OnItemClickListener {
    private ArrayList<AgendaItem> agendaItemArrayList;
    private JazzyListView listView;


    public AgendaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_agenda, container, false);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_agenda, container, false);

        listView = (JazzyListView) view.findViewById(R.id.list);
        listView.setTransitionEffect(JazzyHelper.GROW);
        listView.setOnItemClickListener(this);
        new DownloadData().execute();
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    private class DownloadData extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
//            super.onPreExecute();
//            progressDialog = new ProgressDialog(CategoryOneFragment.this);
//            progressDialog.setMessage("Mendownload data...");
//            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... params) {
            ObjectMapper mapper = new ObjectMapper();
            agendaItemArrayList = null;
            while (agendaItemArrayList == null) {
                try {
                    agendaItemArrayList = mapper.readValue(new URL(AgendaItem.API),
                            mapper.getTypeFactory().constructCollectionType(ArrayList.class, AgendaAdapter.class));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            AgendaAdapter adapter = new AgendaAdapter(agendaItemArrayList , getActivity());
            listView.setAdapter(adapter);

//            progressDialog.dismiss();
//            AgendaAdapter adapter = new AgendaAdapter(AgendaActivity.this, agendaArrayList);
//            adapter.SetOnItemClickListener(new AgendaAdapter().OnItemClickListener() {
//                @Override
//                public void onItemClick(View view, int position) {
//                    Intent intent = new Intent(AgendaFragment.this, DetailAgendaActivity.class);
//                    intent.putExtra("agenda", agendaArrayList.get(position));
//                    startActivity(intent);
//                }
//            });


        }
    }


}
