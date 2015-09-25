package nl.iris_meerman.moneymatters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Groceries extends AppCompatActivity implements View.OnClickListener {

    private Button addButton;
    public ListView list;
    public EditText date, amount;
    public String d, a, obj;
    Expenses exp = new Expenses();
    String groceriesobjects = "groceriesobjects.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.groceries);

        addButton = (Button) findViewById(R.id.add_groceries);
        list = (ListView) findViewById(R.id.addedgroceries);
        date = (EditText) findViewById(R.id.date);
        amount = (EditText) findViewById(R.id.amount);

        exp.displayList(list, groceriesobjects, getApplicationContext());

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {
        d = date.getText().toString();
        a = amount.getText().toString();
        obj = d + ": €" + a;

        if (!d.isEmpty() && !a.isEmpty()) {
            exp.writeExpenses(obj, "groceriesobjects.txt", "groceries_expenses", getApplicationContext(), a);
        } else {
            Toast.makeText(Groceries.this, "Fill in date and expenses", Toast.LENGTH_SHORT).show();
        }
    }

    public void backToMain(View v){
        Intent back = new Intent(this, MainScreen.class);
        startActivity(back);
    }
}


