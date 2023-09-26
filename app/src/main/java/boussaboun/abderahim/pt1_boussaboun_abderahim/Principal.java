package boussaboun.abderahim.pt1_boussaboun_abderahim;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Principal extends AppCompatActivity {
    EditText factor1, factor2;
    TextView result, operationTextView;
    Button AC, DEL, multiplicacio, divisio, resta, suma, igual;
    String currentFactor = "factor1"; // Controla cuál factor se está editando actualmente
    String selectedOperator = ""; // Almacena el operador seleccionado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar objetos Button y otros elementos usando findViewById
        factor1 = findViewById(R.id.factor1);
        factor2 = findViewById(R.id.factor2);
        result = findViewById(R.id.result);
        operationTextView = findViewById(R.id.operation);
        AC = findViewById(R.id.AC);
        DEL = findViewById(R.id.DEL);
        multiplicacio = findViewById(R.id.multiplicacio);
        divisio = findViewById(R.id.divisio);
        resta = findViewById(R.id.resta);
        suma = findViewById(R.id.suma);
        igual = findViewById(R.id.igual);

        // Configurar los eventos onClick para los botones
        AC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                factor1.setText("");
                factor2.setText("");
                operationTextView.setText("");
                result.setText("");
                currentFactor = "factor1";
                factor1.requestFocus(); // Establece el enfoque en factor1
            }
        });

        DEL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!factor2.getText().toString().isEmpty()) {
                    // Obtiene la posición actual del cursor
                    int cursorPosition = factor2.getSelectionStart();

                    // Elimina un carácter de factor2
                    String factor2Text = factor2.getText().toString();
                    if (cursorPosition > 0) {
                        String newText = factor2Text.substring(0, cursorPosition - 1) + factor2Text.substring(cursorPosition);
                        factor2.setText(newText);

                        // Restaura la posición del cursor
                        factor2.setSelection(cursorPosition - 1);
                    }
                } else if (!operationTextView.getText().toString().isEmpty()) {
                    // Si factor2 está vacío pero el TextView de operación tiene contenido, elimina el contenido del TextView de operación
                    operationTextView.setText("");
                } else if (currentFactor.equals("factor1")){
                    // Obtiene la posición actual del cursor
                    int cursorPosition = factor1.getSelectionStart();

                    // Elimina un carácter de factor2
                    String factor1Text = factor1.getText().toString();
                    if (cursorPosition > 0) {
                        String newText = factor1Text.substring(0, cursorPosition - 1) + factor1Text.substring(cursorPosition);
                        factor1.setText(newText);

                        // Restaura la posición del cursor
                        factor1.setSelection(cursorPosition - 1);
                }}

                else {
                    // Si el factor2 y el TextView de operación están vacíos, cambia el enfoque al factor1
                    factor1.requestFocus();
                    switchFactor();
                }
            }
        });


        multiplicacio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOperator = "x"; // Puedes usar "x" como representación visual de la multiplicación
                switchFactor(); // Cambia al siguiente factor
                updateOperationTextView();
                getCurrentEditText().requestFocus(); // Establece el enfoque en el factor actual
            }
        });

        divisio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOperator = "/"; // Puedes usar "/" como representación visual de la división
                switchFactor(); // Cambia al siguiente factor
                updateOperationTextView();
                getCurrentEditText().requestFocus(); // Establece el enfoque en el factor actual
            }
        });

        resta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOperator = "-";
                switchFactor(); // Cambia al siguiente factor
                updateOperationTextView();
                getCurrentEditText().requestFocus(); // Establece el enfoque en el factor actual
            }
        });

        suma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOperator = "+";
                switchFactor(); // Cambia al siguiente factor
                updateOperationTextView();
                getCurrentEditText().requestFocus(); // Establece el enfoque en el factor actual
            }
        });

        igual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation();
            }
        });
    }

    private void switchFactor() {
        currentFactor = (currentFactor.equals("factor1")) ? "factor2" : "factor1";
    }

    private EditText getCurrentEditText() {
        return (currentFactor.equals("factor1")) ? factor1 : factor2;
    }

    private void updateOperationTextView() {
        operationTextView.setText( selectedOperator );
    }

    private void performOperation() {
        String num1 = factor1.getText().toString();
        String num2 = factor2.getText().toString();

        if (num1.isEmpty() || num2.isEmpty() || selectedOperator.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Ingresa ambos números y selecciona un operador", Toast.LENGTH_SHORT).show();
            return;
        }

        double resultValue = 0;
        double operand1 = Double.parseDouble(num1);
        double operand2 = Double.parseDouble(num2);

        switch (selectedOperator) {
            case "+":
                resultValue = operand1 + operand2;
                break;
            case "-":
                resultValue = operand1 - operand2;
                break;
            case "x":
                resultValue = operand1 * operand2;
                break;
            case "/":
                if (operand2 != 0) {
                    resultValue = operand1 / operand2;
                } else {
                    Toast.makeText(getApplicationContext(), "No se puede dividir por cero", Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
            default:
                break;
        }
        result.setText(String.valueOf(resultValue));
    }
}
