package com.example.qodra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private List<ItemChat> listChats;

    public ChatAdapter(List<ItemChat> listChats, Context context) {
        this.listChats = listChats;
    }

    // Setting up ViewHolder for Recycler View. From which class data will be shown on ViewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_chat, parent, false);
        return new ViewHolder(v);
    }

    // Binding the data into ViewHolder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ItemChat listChat = listChats.get(position);

        if (listChat.getMsgUser().equals("USER")) {
            holder.textViewUserText.setText(listChat.getMsgText());
            holder.textViewUserText.setVisibility(View.VISIBLE);
            holder.textViewBotText.setVisibility(View.GONE);
            holder.imageViewBotLogo.setVisibility(View.GONE);
        } else {
            holder.textViewBotText.setText(listChat.getMsgText());
            holder.textViewBotText.setVisibility(View.VISIBLE);
            holder.textViewUserText.setVisibility(View.GONE);
            holder.imageViewBotLogo.setVisibility(View.VISIBLE);
        }
    }

    // View item count
    @Override
    public int getItemCount() {
        return listChats == null ? 0 : listChats.size();
    }

    // Setting Variables for ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewBotText;
        public TextView textViewUserText;
        public ImageView imageViewBotLogo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewBotText = itemView.findViewById(R.id.textViewBotText);
            textViewUserText = itemView.findViewById(R.id.textViewUserText);
            imageViewBotLogo = itemView.findViewById(R.id.imageViewBotLogo);
        }
    }
}
