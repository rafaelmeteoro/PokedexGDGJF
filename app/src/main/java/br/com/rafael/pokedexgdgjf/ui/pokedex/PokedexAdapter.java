package br.com.rafael.pokedexgdgjf.ui.pokedex;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import br.com.rafael.pokedexgdgjf.R;
import br.com.rafael.pokedexgdgjf.data.model.PokemonEntrie;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by rafael on 8/28/16.
 **/
public class PokedexAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PokemonEntrie> mList;

    @Inject
    public PokedexAdapter() {
        mList = Collections.emptyList();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_pokedex, parent, false);
        return new ItemPokedexViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBindItemPokedex((ItemPokedexViewHolder) holder, position);
    }

    private void onBindItemPokedex(ItemPokedexViewHolder holder, int position) {
        PokemonEntrie pokemonEntrie = mList.get(position);
        holder.pokemonName.setText(pokemonEntrie.getPokemonSpecies().getName());
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public void setList(List<PokemonEntrie> list) {
        mList = list;
    }

    protected class ItemPokedexViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.pokemon_name)
        TextView pokemonName;

        public ItemPokedexViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
