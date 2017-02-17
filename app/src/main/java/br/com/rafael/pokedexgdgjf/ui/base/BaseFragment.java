package br.com.rafael.pokedexgdgjf.ui.base;

import android.support.v4.app.Fragment;

import br.com.rafael.pokedexgdgjf.injection.HasComponent;

/**
 * Created by rafael on 2/15/17.
 **/

public class BaseFragment extends Fragment {

    @SuppressWarnings("unchecked")
    protected <T> T getComponent(Class<T> componentType) {
        return componentType.cast(((HasComponent<T>) getActivity()).getComponent());
    }
}
