package com.example.assignment1_ict602;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    Button btnCalc, btnClear;
    EditText etKwh, etRebate;
    TextView tvOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        btnCalc = findViewById(R.id.btnCalc);
        btnClear = findViewById(R.id.btnClear);
        etKwh = findViewById(R.id.etKwh);
        etRebate = findViewById(R.id.etRebate);
        tvOutput = findViewById(R.id.tvOutput);

        // Setup Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Calculate button functionality
        btnCalc.setOnClickListener(v -> {
            try {
                double kwh = Double.parseDouble(etKwh.getText().toString());
                double rebate = Double.parseDouble(etRebate.getText().toString());

                // Validate rebate percentage
                if (rebate < 0 || rebate > 5) {
                    showAlert("Invalid Rebate", "Rebate percentage must be between 0 and 5.");
                    return;
                }

                // Calculate bill
                double totalBill = calculateBill(kwh, rebate);
                tvOutput.setText(String.format("Total Bill: RM %.2f", totalBill));
            } catch (NumberFormatException e) {
                showAlert("Input Error", "Please enter valid numerical values for kWh and rebate.");
            }
        });

        // Clear button functionality
        btnClear.setOnClickListener(v -> {
            etKwh.setText("");
            etRebate.setText("");
            tvOutput.setText("Bill will appear here.");
        });
    }

    private double calculateBill(double kwh, double rebate) {
        double totalBill = 0.0;

        // Block-wise rate calculations
        if (kwh <= 200) {
            totalBill = kwh * 21.8;
        } else if (kwh <= 300) {
            totalBill = (200 * 21.8) + ((kwh - 200) * 33.4);
        } else if (kwh <= 600) {
            totalBill = (200 * 21.8) + (100 * 33.4) + ((kwh - 300) * 51.6);
        } else {
            totalBill = (200 * 21.8) + (100 * 33.4) + (300 * 51.6) + ((kwh - 600) * 54.6);
        }

        // Apply rebate
        totalBill /= 100; // Convert sen to RM
        totalBill -= totalBill * (rebate / 100);

        return totalBill;
    }

    private void showAlert(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_about) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
