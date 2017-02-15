package br.com.rafael.pokedexgdgjf.ui.exception;

/**
 * Created by rafael on 2/14/17.
 *
 */

public class MvpViewNotAttachedException extends RuntimeException {

    public MvpViewNotAttachedException(String type) {
        super("View not attached to " + type);
    }
}
