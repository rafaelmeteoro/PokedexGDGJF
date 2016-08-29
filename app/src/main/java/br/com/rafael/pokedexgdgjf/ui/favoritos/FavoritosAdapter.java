package br.com.rafael.pokedexgdgjf.ui.favoritos;

import android.content.Context;
import android.support.annotation.StringDef;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import br.com.rafael.pokedexgdgjf.R;
import br.com.rafael.pokedexgdgjf.data.model.Pokemon;
import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by rafael on 8/29/16.
 **/
public class FavoritosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Pokemon> mList;

    @Inject
    public FavoritosAdapter() {
        mList = Collections.emptyList();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_favoritos, parent, false);
        return new ItemFavoritosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBindItemFavoritos((ItemFavoritosViewHolder) holder, position);
    }

    private void onBindItemFavoritos(ItemFavoritosViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        Pokemon pokemon = mList.get(position);
        holder.pokemonName.setText(pokemon.getName());
        holder.pokemonWeight.setText(String.valueOf(pokemon.getWeight()));
        holder.pokemonHeight.setText(String.valueOf(pokemon.getHeight()));

        Picasso.with(context)
                .load(pokemon.getSprites().getFrontDefault())
                .placeholder(R.drawable.pokeball)
                .error(R.drawable.cloud_outline_off)
                .into(holder.ivPokemon);
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public void setList(List<Pokemon> list) {
        mList = list;
    }

    protected class ItemFavoritosViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.iv_pokemon)
        CircleImageView ivPokemon;

        @Bind(R.id.pokemon_name)
        TextView pokemonName;

        @Bind(R.id.pokemon_weight)
        TextView pokemonWeight;

        @Bind(R.id.pokemon_height)
        TextView pokemonHeight;

        public ItemFavoritosViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
