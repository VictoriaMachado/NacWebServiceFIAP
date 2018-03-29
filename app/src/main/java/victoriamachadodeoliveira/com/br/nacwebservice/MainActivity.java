package victoriamachadodeoliveira.com.br.nacwebservice;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import victoriamachadodeoliveira.com.br.nacwebservice.model.Pokemon;
import victoriamachadodeoliveira.com.br.nacwebservice.model.PokemonAPI;

public class MainActivity extends AppCompatActivity {

    private EditText edPokeID;
    private TextView tvOrdem;
    private TextView tvNome;
    private TextView tvAltura;
    private TextView tvPeso;

    private Pokemon meuPokemon;

    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edPokeID = (EditText)findViewById(R.id.etPokeID);
        tvOrdem = (TextView)findViewById(R.id.tvOrdem);
        tvNome = (TextView)findViewById(R.id.tvNome);
        tvAltura = (TextView)findViewById(R.id.tvAltura);
        tvPeso = (TextView)findViewById(R.id.tvPeso);
    }

    public void pesquisar(View v) {

        exibirProgress();

        final RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://pokeapi.co/api/v2").build();

        PokemonAPI api = restAdapter.create(PokemonAPI.class);

        api.getPokemon(Integer.parseInt(edPokeID.getText().toString()), new Callback<Pokemon>() {
            @Override
            public void success(Pokemon pokemon, Response response) {
                meuPokemon = pokemon;
                tvOrdem.setText(meuPokemon.getOrder());
                tvNome.setText(meuPokemon.getName());
                tvAltura.setText(meuPokemon.getHeight());
                tvPeso.setText(meuPokemon.getWeight());
                fecharProgress();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void exibirProgress() {
        progress = new ProgressDialog(this);
        progress.setMessage("Capturando...");
        progress.setIndeterminate(true);
        progress.show();
    }

    private void fecharProgress() {
        progress.setMessage("GOTCHA!");
        if(progress != null){
            if(progress.isShowing()){
                try{
                    Thread.sleep(500);
                }catch (Exception ex){

                }
                progress.dismiss();
            }
        }
    }

}
