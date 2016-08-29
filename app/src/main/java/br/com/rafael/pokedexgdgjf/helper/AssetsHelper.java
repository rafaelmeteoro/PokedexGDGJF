package br.com.rafael.pokedexgdgjf.helper;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import br.com.rafael.pokedexgdgjf.PokedexApplication;

/**
 * Classe responsável por pegar um arquivo json local, ler e devolve-lo em string
 * Created by rafael on 8/29/16.
 **/
public final class AssetsHelper {

    /**
     * Devolve em string o conteúdo de um arquivo com o caminho passado pelo assetPath
     * */
    public static String getStringFromAssets(Context context, String assetPath) throws IOException {
        StringBuilder buf = new StringBuilder();
        InputStream inputStream;
        String str;

        try {
            inputStream = PokedexApplication.get(context).getAssets().open(assetPath);
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            while ((str = in.readLine()) != null) {
                buf.append(str);
            }
            inputStream.close();
            in.close();
        } catch (IOException e) {
            throw e;
        }

        return buf.toString();
    }
}
