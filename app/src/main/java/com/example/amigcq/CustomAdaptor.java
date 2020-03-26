package com.example.amigcq;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.example.amigcq.model.NotificationModel;

import java.util.List;

public class CustomAdaptor extends RecyclerView.Adapter<CustomAdaptor.CustomViewHolder>{

    private List<NotificationModel> notification;
    private Context context;

    public CustomAdaptor(Context context, List<NotificationModel> notification){
        this.notification = notification;
        this.context = context;
    }



    @Override
    public CustomViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.eachnotification,null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        final NotificationModel notificationItem = notification.get(position);
        System.out.println("here");
        holder.title.setText(Html.fromHtml(notificationItem.getTitle()));
        holder.body.setText(Html.fromHtml(notificationItem.getBody()));
    }

    @Override
    public int getItemCount() {
        return (null != notification ? notification.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView title;
        protected TextView body;

        public CustomViewHolder(View view) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.title);
            this.body = (TextView) view.findViewById(R.id.body);
        }
    }
}