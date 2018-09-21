package com.hunter.appstreetassignment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {

    private final OnItemClick<Image.ImagesBean> onItemClick;
    private ArrayList<Image.ImagesBean> data = new ArrayList<>();

    public ImagesAdapter(OnItemClick<Image.ImagesBean> onItemClick) {
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
              loadImage(viewHolder.image,data.get(i));
    }

    private void loadImage(ImageView image, Image.ImagesBean image1) {
        Picasso.get()
                .load(image1.getUrl())
                .placeholder(R.mipmap.ic_launcher)
                .into(image);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(ArrayList<Image.ImagesBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClick.onItemClick(data.get(getLayoutPosition()),getLayoutPosition(),view);
                }
            });
        }
    }
}
