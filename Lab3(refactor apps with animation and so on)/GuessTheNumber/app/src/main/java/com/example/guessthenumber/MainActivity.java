package com.example.guessthenumber;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tvInfo;
    EditText etInput;
    Button bControl, bNewGame, bExit;
    ProgressBar progressBar;
    ImageView ivSmile;
    int randomNumber;
    int attemptsLeft = 5; // Количество попыток

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = findViewById(R.id.textView2);
        etInput = findViewById(R.id.editText);
        bControl = findViewById(R.id.button1);
        bNewGame = findViewById(R.id.button_new_game);
        bExit = findViewById(R.id.button_exit);
        progressBar = findViewById(R.id.progressBar);
        ivSmile = findViewById(R.id.imageView);

        generateRandomNumber();

        // Кнопка "Проверить"
        bControl.setOnClickListener(v -> checkGuess());

        // Кнопка "Новая игра"
        bNewGame.setOnClickListener(v -> {
            resetGame();
            animateRestart();
        });

        // Кнопка "Выход"
        bExit.setOnClickListener(v -> finish());
    }

    private void generateRandomNumber() {
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;
        attemptsLeft = 5;
        progressBar.setProgress(attemptsLeft * 20); // Обновляем прогресс-бар
        tvInfo.setText(getString(R.string.try_to_guess));
    }

    private void checkGuess() {
        try {
            int inputValue = Integer.parseInt(etInput.getText().toString());

            if (inputValue < 1 || inputValue > 100) {
                tvInfo.setText(getString(R.string.error));
            } else {
                attemptsLeft--;
                progressBar.setProgress(attemptsLeft * 20);

                if (inputValue == randomNumber) {
                    showResultDialog(true);
                } else if (attemptsLeft == 0) {
                    showResultDialog(false);
                } else {
                    tvInfo.setText(inputValue < randomNumber ? getString(R.string.behind) : getString(R.string.ahead));
                    animateWrongGuess();
                }
            }
        } catch (NumberFormatException e) {
            tvInfo.setText(getString(R.string.error));
            Toast.makeText(this, "Введите корректное число", Toast.LENGTH_SHORT).show();
        }
    }

    private void showResultDialog(boolean isWin) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(isWin ? "Поздравляем!" : "Вы проиграли!");
        dialog.setMessage(isWin ? "Вы угадали число!" : "Правильный ответ: " + randomNumber);

        dialog.setPositiveButton("Новая игра", (dialog1, which) -> resetGame());
        dialog.setNegativeButton("Выход", (dialog12, which) -> finish());

        dialog.show();
        animateWinLose(isWin);
    }

    private void resetGame() {
        generateRandomNumber();
        etInput.setText("");
        tvInfo.setText(getString(R.string.try_to_guess));
        ivSmile.setImageResource(R.drawable.neutral); // Обновление изображения
    }

    private void animateWrongGuess() {
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        tvInfo.startAnimation(shake);
    }

    private void animateWinLose(boolean isWin) {
        Animation anim = AnimationUtils.loadAnimation(this, isWin ? R.anim.zoom_in : R.anim.fade_out);
        ivSmile.startAnimation(anim);
        ivSmile.setImageResource(isWin ? R.drawable.happy : R.drawable.sad);
    }

    private void animateRestart() {
        Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        ivSmile.startAnimation(rotate);
    }
}
