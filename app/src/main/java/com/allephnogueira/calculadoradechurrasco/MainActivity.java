package com.allephnogueira.calculadoradechurrasco;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.allephnogueira.calculadoradechurrasco.Service.StringFormatada;

public class MainActivity extends AppCompatActivity {

    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Aqui estou dizendo que vai ser borda a borda.
        setContentView(R.layout.activity_main); // Aqui vai ser qual arquivo XML controla isso

        // Aqui estamos ajustando a view com base no sistema.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Referências aos componentes da UI
        EditText number1EditText = findViewById(R.id.number1);
        Button calculateButton = findViewById(R.id.calculateButton);
        TextView resultTextView = findViewById(R.id.result);

        // Configurando o botão de cálculo
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String number1Str = number1EditText.getText().toString();

                // Verificar se o usuário digitou a quantidade de pessoas!
                if (!number1Str.isEmpty()) {

                    // Deixar o campo de exibir detalhes visível
                    resultTextView.setVisibility(View.VISIBLE);

                    int numeroDePessoas = Integer.parseInt(number1Str);
                    double totalAlimento = numeroDePessoas * 0.400;

                    // Texto formatado para exibir apenas 3 casas
                    String totalAlimentoF = String.format("%.3f", numeroDePessoas * 0.400);
                    String totalBebidaF = String.format("%.2f", numeroDePessoas * 1.500);

                    resultTextView.setText("Total de alimento recomendado: " + totalAlimentoF + "KG" +
                            "\n\n" + "Refrigerante: " + totalBebidaF + "L");

                    // Remover o botão "Gerar quantidade exata!" existente, se houver
                    LinearLayout mainLayout = findViewById(R.id.main_layout);
                    if (nextButton != null) {
                        mainLayout.removeView(nextButton);
                    }

                    // Criar e adicionar o novo botão programaticamente
                    nextButton = new Button(MainActivity.this);
                    nextButton.setText("Gerar meu KIT CHURRASCO");
                    nextButton.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
                    nextButton.setTextColor(getResources().getColor(android.R.color.white));
                    nextButton.setPadding(12, 12, 12, 12);

                    // Configurar a ação do novo botão
                    nextButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            // Formatando os alimentos para exibir apenas 3 casas após o ponto.
                            String carne = StringFormatada.stringFormatted(totalAlimento, 0.24);
                            String frango = StringFormatada.stringFormatted(totalAlimento, 0.24);
                            String suino = StringFormatada.stringFormatted(totalAlimento, 0.22);
                            String linguica = StringFormatada.stringFormatted(totalAlimento, 0.30);

                            resultTextView.setText("\nCarne = " + carne + "KG\n" +
                                    "Frango = " + frango + "KG\n" +
                                    "Suíno = " + suino + "KG\n" +
                                    "Linguiça = " + linguica + "KG\n" +
                                    "Pao de alho = " + number1Str + "un\n\n" +
                                    "Refrigerante = " + totalBebidaF + " Litros");
                        }
                    });

                    // Adicionar o botão ao layout principal
                    mainLayout.addView(nextButton);

                } else {
                    resultTextView.setText("Digite a quantidade de participantes!");
                    resultTextView.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
