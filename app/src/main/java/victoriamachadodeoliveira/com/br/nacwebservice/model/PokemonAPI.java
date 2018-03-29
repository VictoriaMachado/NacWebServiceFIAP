package victoriamachadodeoliveira.com.br.nacwebservice.model;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by rm75283 on 16/09/2016.
 */
public interface PokemonAPI {

    @GET("/pokemon/{id}")
    void getPokemon(@Path("id")int id, Callback<Pokemon> pokemon);

}
