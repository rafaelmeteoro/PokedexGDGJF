package br.com.rafael.pokedexgdgjf.ui.favoritos;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import br.com.rafael.pokedexgdgjf.R;
import br.com.rafael.pokedexgdgjf.data.model.Pokemon;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by rafael on 8/29/16.
 **/
public class FavoritosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private List<Pokemon> mList;
    private FavoritosItemClickListener mListener;

    public interface FavoritosItemClickListener {
        void onPokemonClick(Pokemon pokemon);
    }

    @Inject
    public FavoritosAdapter() {
        mList = Collections.emptyList();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_favoritos, parent, false);
        ItemFavoritosViewHolder holder = new ItemFavoritosViewHolder(view);
        holder.ibDelete.setOnClickListener(this);
        return holder;
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
        holder.ibDelete.setTag(holder);

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

    @Override
    public void onClick(View view) {
        int itemId = view.getId();
        if (itemId == R.id.ib_delete && mListener != null) {
            ItemFavoritosViewHolder holder = (ItemFavoritosViewHolder) view.getTag();
            mListener.onPokemonClick(mList.get(holder.getAdapterPosition()));
        }
    }

    public void setList(List<Pokemon> list) {
        mList = list;
    }

    public void setListener(FavoritosItemClickListener listener) {
        mListener = listener;
    }

    protected class ItemFavoritosViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_pokemon)
        CircleImageView ivPokemon;

        @BindView(R.id.pokemon_name)
        TextView pokemonName;

        @BindView(R.id.pokemon_weight)
        TextView pokemonWeight;

        @BindView(R.id.pokemon_height)
        TextView pokemonHeight;

        @BindView(R.id.ib_delete)
        ImageButton ibDelete;

        public ItemFavoritosViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
