package com.appdatabase.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.appdatabase.R;
import com.appdatabase.model.Contato;

import java.util.ArrayList;

public class ContatoAdapter extends RecyclerView.Adapter<ContatoAdapter.ContatosViewHolder> {

    private ArrayList<Contato> contatos;
    private Listener listener;

    // CONSTRUTOR
    public ContatoAdapter(ArrayList<Contato> contatos) {
        this.contatos = contatos;
    }

    public void setContatos(ArrayList<Contato> contatos) {
        this.contatos = contatos;
    }

    // Interface do listener a ser implementada na activity
    public interface Listener {
        void onClick(int position);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        // Se for necessário criar tipos diferentes de views.
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public ContatosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CardView cardView = (CardView) inflater.inflate(R.layout.card_contato, parent, false);
        return new ContatosViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContatosViewHolder holder, int position) {
        holder.tvNome.setText(contatos.get(position).nome);
        holder.tvEmail.setText(contatos.get(position).email);
        holder.tvTel.setText(contatos.get(position).tel);

        // lister para o clique
        holder.itemView.setOnClickListener(
                v -> {
                    int posicaoAtual = holder.getAdapterPosition();
                    if (posicaoAtual != RecyclerView.NO_POSITION) {
                        this.listener.onClick(posicaoAtual);
                    }
                }
        );
    }

    @Override
    public int getItemCount() {
        return contatos.size();
    }

    public static class ContatosViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNome;
        private TextView tvEmail;
        private TextView tvTel;
        public ContatosViewHolder(@NonNull CardView cardView) {
            super(cardView);
            this.tvNome = cardView.findViewById(R.id.tv_nome);
            this.tvEmail = cardView.findViewById(R.id.tv_email);
            this.tvTel = cardView.findViewById(R.id.tv_tel);
        }
    }
}
