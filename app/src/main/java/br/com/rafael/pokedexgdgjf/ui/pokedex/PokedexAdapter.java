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
import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by rafael on 8/28/16.
 **/
public class PokedexAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private List<PokemonEntrie> mList;
    private PokedexItemClickListener mListener;

    public interface PokedexItemClickListener {
        void onPokemonClick(PokemonEntrie pokemonEntrie);
    }

    @Inject
    public PokedexAdapter() {
        mList = Collections.emptyList();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_pokedex, parent, false);
        ItemPokedexViewHolder holder = new ItemPokedexViewHolder(view);
        holder.llItem.setOnClickListener(this);
        return holder;
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
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    @Override
    public void onClick(View view) {
        int itemId = view.getId();
        if (itemId == R.id.ll_item && mListener != null) {
            ItemPokedexViewHolder holder = (ItemPokedexViewHolder) view.getTag();
            mListener.onPokemonClick(mList.get(holder.getAdapterPosition()));
        }
    }

    public void setList(List<PokemonEntrie> list) {
        mList = list;
    }

    public void setListener(PokedexItemClickListener listener) {
        mListener = listener;
    }

    protected class ItemPokedexViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.ll_item)
        LinearLayout llItem;

        @Bind(R.id.pokemon_name)
        TextView pokemonName;

        @Bind(R.id.iv_pokemon)
        CircleImageView ivPokemon;

        public ItemPokedexViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
