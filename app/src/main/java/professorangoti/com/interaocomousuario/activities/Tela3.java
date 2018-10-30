package professorangoti.com.interaocomousuario.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import professorangoti.com.interaocomousuario.R;

public class Tela3 extends Activity {

    String valor_DaoutraClasse = "Erro";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela3);

        Intent intent = getIntent();
        valor_DaoutraClasse = intent.getStringExtra("valor_passar_praFrente");

        TextView valor = findViewById(R.id.txtValor);
        valor.setText(valor_DaoutraClasse);
    }
}
