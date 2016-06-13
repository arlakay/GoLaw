package com.erd.golaw.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.erd.golaw.R;
import com.erd.golaw.model.Pelayanan;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ILM on 6/10/2016.
 */
public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.VersionViewHolder> {

    private List<Pelayanan> loginList;
    private int rowLayout;
    Context context;
    OnItemClickListener clickListener;

    /*public AdvokatAdapter(Context context) {
        this.context = context;
    }*/

    /*public AdvokatAdapter(List<Login> restaurantList, OnItemClickListener listener) {
        this.loginList = restaurantList;
        this.clickListener = listener;

    }*/

    public ServicesAdapter(List<Pelayanan> login, int rowLayout, Context context, OnItemClickListener listener) {
        this.loginList = login;
        this.rowLayout = rowLayout;
        this.context = context;
        this.clickListener = listener;
    }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_advokat, viewGroup, false);
        VersionViewHolder viewHolder = new VersionViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VersionViewHolder versionViewHolder, int i) {
        final Pelayanan model = loginList.get(i);
        versionViewHolder.bind(model, clickListener);

    }

    @Override
    public int getItemCount() {
        return loginList == null ? 0 : loginList.size();
    }

    public void animateTo(List<Pelayanan> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(List<Pelayanan> newModels) {
        for (int i = loginList.size() - 1; i >= 0; i--) {
            final Pelayanan model = loginList.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<Pelayanan> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final Pelayanan model = newModels.get(i);
            if (!loginList.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<Pelayanan> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final Pelayanan model = newModels.get(toPosition);
            final int fromPosition = loginList.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public Pelayanan removeItem(int position) {
        final Pelayanan model = loginList.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, Pelayanan model) {
        loginList.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final Pelayanan model = loginList.remove(fromPosition);
        loginList.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }

    class VersionViewHolder extends RecyclerView.ViewHolder {
        TextView titleRes;
        TextView descRes;
        CircleImageView picRes;

        public VersionViewHolder(View itemView) {
            super(itemView);

            picRes = (CircleImageView) itemView.findViewById(R.id.profile_image_advokat);
            titleRes = (TextView) itemView.findViewById(R.id.txt_name_advokat);
            descRes = (TextView) itemView.findViewById(R.id.txt_description_advokat);

        }

        public void bind(final Pelayanan model, final OnItemClickListener listener) {
            titleRes.setText(model.getName());
            descRes.setText(model.getDetails());
//            picRes.setImageUrl(model.getUrlpic(), imageLoader);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(model);

                }
            });
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(Pelayanan model);
    }

    public void SetOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }


}
