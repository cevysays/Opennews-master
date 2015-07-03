package com.openetizen.cevysays.opennews.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.openetizen.cevysays.opennews.R;
import com.openetizen.cevysays.opennews.fragments.AgendaFragment;
import com.openetizen.cevysays.opennews.models.AgendaItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Cevy Yufindra on 12/06/2015.
 */
public class AgendaAdapter extends BaseAdapter
{
    private ArrayList<AgendaItem> posts;
    private Context context;

    public AgendaAdapter(ArrayList<AgendaItem> posts, Context context)
    {
        this.posts = posts;
        this.context = context;
    }

    @Override
    public int getCount()
    {
        return posts.size();
    }

    @Override
    public Object getItem(int position)
    {
        return posts.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;


        if (convertView == null) {
            LayoutInflater inflater
                    = (LayoutInflater) context
                    .getSystemService(Context
                            .LAYOUT_INFLATER_SERVICE);

            convertView = inflater
                    .inflate(R.layout.item_agenda,
                            parent, false);

            holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.image);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            holder.image = (ImageView) convertView
                    .findViewById(R.id.image);
            holder.title = (TextView) convertView
                    .findViewById(R.id.title);
            holder.created_at = (TextView) convertView
                    .findViewById(R.id.created_at);
            holder.user_id = (TextView) convertView.findViewById(R.id.user_id);

            convertView.setTag(holder);

        }
        return convertView;
    }

    private class ViewHolder {

        TextView article_id;
        TextView user_id;
        TextView title;
        TextView content;
        TextView category_cd;
        TextView publish_status;
        TextView created_at;
        TextView updated_at;
        ImageView image;

    }
}
