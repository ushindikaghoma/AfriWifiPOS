package com.soft.afri_wifi.Releve;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.soft.afri_wifi.Compte.data.CompteResponse;
import com.soft.afri_wifi.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterReleve extends RecyclerView.Adapter<AdapterReleve.ReleveListAdapter>{

    public List<CompteResponse> list;
    Context context;

    public AdapterReleve(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public ReleveListAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_releve, parent, false);
        return new ReleveListAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReleveListAdapter holder, int position) {
        CompteResponse compteResponse = this.list.get(position);
        holder.textView_libelle.setText(""+compteResponse.getLibelle());
        holder.textView_date.setText(""+compteResponse.getDateOperation());

        if(compteResponse.getDebit() > 0)
        {
            holder.imageView_arrow.setImageResource(R.drawable.baseline_arrow_downward_24);
            holder.textView_montant.setText("CDF "+compteResponse.getDebit());
            holder.textView_montant.setTextColor(Color.parseColor("#000000"));
        }else
        {

        }
        if (compteResponse.getCredit() > 0)
        {
            holder.imageView_arrow.setImageResource(R.drawable.baseline_arrow_upward_24);
            holder.textView_montant.setText("CDF "+-compteResponse.getCredit());
            holder.textView_montant.setTextColor(Color.parseColor("#E3564C"));
        }else
        {

        }

    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    class ReleveListAdapter extends RecyclerView.ViewHolder
    {
        TextView textView_libelle;
        TextView textView_date;
        TextView textView_montant;
        ImageView imageView_arrow;

        public ReleveListAdapter(@NonNull View itemView) {
            super(itemView);

            textView_libelle = itemView.findViewById(R.id.model_releve_libelle);
            textView_date = itemView.findViewById(R.id.model_releve_date);
            textView_montant = itemView.findViewById(R.id.model_releve_montant);
            imageView_arrow = itemView.findViewById(R.id.image_user);

        }
    }

    public void setList(List<CompteResponse> list)
    {
        this.list.clear();
        this.list.addAll(list);
    }
}
