package com.example.guessthenumber;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tvInfo;
    EditText etInput;
    Button bControl;
    int randomNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tvInfo = findViewById(R.id.textView2);
        etInput = findViewById(R.id.editText);
        bControl = findViewById(R.id.button1);

        generateRandomNumber();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void generateRandomNumber() {
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;
        tvInfo.setText(getResources().getString(R.string.try_to_guess));
    }

    public void onClick(android.view.View view) {
        try {
            int inputValue = Integer.parseInt(etInput.getText().toString());

            if (inputValue < 1 || inputValue > 100) {
                tvInfo.setText(getResources().getString(R.string.error));
            } else {
                if (inputValue == randomNumber) {
                    tvInfo.setText(getResources().getString(R.string.hit));
                } else if (inputValue < randomNumber) {
                    tvInfo.setText(getResources().getString(R.string.behind));
                } else {
                    tvInfo.setText(getResources().getString(R.string.ahead));
                }
            }
        } catch (NumberFormatException e) {
            tvInfo.setText(getResources().getString(R.string.error));
            Toast.makeText(this, "Введите корректное число", Toast.LENGTH_SHORT).show();
        }
    }

    public void onExitClick(android.view.View view) {
        finish();
    }

    public void onNewGameClick(android.view.View view) {
        generateRandomNumber();
        etInput.setText("");
        tvInfo.setText(getResources().getString(R.string.try_to_guess));
    }
}