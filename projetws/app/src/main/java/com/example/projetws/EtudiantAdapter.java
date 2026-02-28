package com.example.projetws;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EtudiantAdapter extends RecyclerView.Adapter<EtudiantAdapter.VH> {

    public interface OnItemClick {
        void onClick(Etudiant e);
    }

    private final List<Etudiant> data;
    private final OnItemClick listener;

    public EtudiantAdapter(List<Etudiant> data, OnItemClick listener) {
        this.data = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_etudiant, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Etudiant e = data.get(position);
        holder.txtNomPrenom.setText(e.getNom() + " " + e.getPrenom());
        holder.txtVilleSexe.setText(e.getVille() + " | " + e.getSexe());

        holder.itemView.setOnClickListener(v -> listener.onClick(e));
    }

    @Override
    public int getItemCount() { return data.size(); }

    static class VH extends RecyclerView.ViewHolder {
        TextView txtNomPrenom, txtVilleSexe;
        VH(@NonNull View itemView) {
            super(itemView);
            txtNomPrenom = itemView.findViewById(R.id.txtNomPrenom);
            txtVilleSexe = itemView.findViewById(R.id.txtVilleSexe);
        }
    }
}