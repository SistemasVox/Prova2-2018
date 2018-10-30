package professorangoti.com.interaocomousuario.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import professorangoti.com.interaocomousuario.Api.ApiEndpoint;
import professorangoti.com.interaocomousuario.Produto;
import professorangoti.com.interaocomousuario.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "teste";
    private static String pedido;
    ArrayList<Produto> produtos = new ArrayList<Produto>();
    private int entrega;
    private String valor;

    public static String getPedido() {
        return pedido;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent intent = getIntent();
        pedido = intent.getStringExtra("mensagem");
        TextView textView = findViewById(R.id.order_textview);
        textView.setText(pedido);

        Spinner spinner = findViewById(R.id.label_spinner);
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.labels_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (spinner != null) {
            spinner.setAdapter(adapter);
        }
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked.
        switch (view.getId()) {
            case R.id.sameday:
                if (checked)
                    // Same day service
                    entrega = R.string.entrega_no_dia_seguinte;
                displayToast(getString(entrega));
                break;
            case R.id.nextday:
                if (checked)
                    // Next day delivery
                    entrega = R.string.entrega_no_dia_seguinte;
                displayToast(getString(entrega));
                break;
            case R.id.pickup:
                if (checked)
                    // Pick up
                    entrega = R.string.retirar_na_loja;
                displayToast(getString(entrega));
                break;
            default:
                // Do nothing.
                break;
        }
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        displayToast(parent.getItemAtPosition(position).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void construirJSON2() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://provaddm2018.atwebpages.com/")
                .addConverterFactory(GsonConverterFactory.create(gson)).build();

        ApiEndpoint apiService = retrofit.create(ApiEndpoint.class);
        Call<ArrayList<Produto>> call = apiService.obterPosts();

        call.enqueue(new Callback<ArrayList<Produto>>() {

            @Override
            public void onResponse(Call<ArrayList<Produto>> call, Response<ArrayList<Produto>> response) {
                produtos = response.body();
                construirTela3();
            }

            @Override
            public void onFailure(Call<ArrayList<Produto>> call, Throwable t) {
                displayToast("Falha no Retrofit.");
            }

        });
    }

    private void construirTela3() {
        Intent i = new Intent(this, Tela3.class);
        i.putExtra("valor_passar_praFrente", getValor());
        startActivity(i);
    }

    public void fecharConta(View v) {
        Log.i(TAG, "Chamei o Retrofit, pedido: " + pedido);
        construirJSON2();
    }

    public String getValor() {
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getProduto().toString().equals(pedido.toString())) {
                return String.valueOf(produtos.get(i).getValor());
            }
        }
        return "";
    }

}
