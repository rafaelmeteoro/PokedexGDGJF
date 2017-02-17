package br.com.rafael.pokedexgdgjf.ui.pokedex;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import br.com.rafael.pokedexgdgjf.R;
import br.com.rafael.pokedexgdgjf.data.model.PokemonEntrie;
import br.com.rafael.pokedexgdgjf.ui.listener.OnPokemonClickListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by rafael on 8/28/16.
 **/
public class PokedexAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PokemonEntrie> mList;
    private OnPokemonClickListener mListener;

    @Inject
    public PokedexAdapter() {
        mList = Collections.emptyList();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item_pokedex, parent, false);
        return new ItemPokedexViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBindItemPokedex((ItemPokedexViewHolder) holder, position);
    }

    private void onBindItemPokedex(ItemPokedexViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        PokemonEntrie pokemonEntrie = mList.get(position);
        holder.pokemonName.setText(pokemonEntrie.getPokemonSpecies().getName());
        holder.llItem.setTag(holder);

        String imageUrl = context.getString(R.string.layout_item_pokedex_image_format, pokemonEntrie.getEntryNumber());
        Picasso.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.pokeball)
                .error(R.drawable.cloud_outline_off)
                .into(holder.ivPokemon);

        holder.llItem.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.onPokemonClick(pokemonEntrie);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public void setList(List<PokemonEntrie> list) {
        mList = list;
    }

    public void setListener(OnPokemonClickListener listener) {
        mListener = listener;
    }

    protected class ItemPokedexViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_item)
        LinearLayout llItem;

        @BindView(R.id.pokemon_name)
        TextView pokemonName;

        @BindView(R.id.iv_pokemon)
        CircleImageView ivPokemon;

        public ItemPokedexViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
