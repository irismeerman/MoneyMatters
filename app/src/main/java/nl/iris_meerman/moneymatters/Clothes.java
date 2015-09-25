package nl.iris_meerman.moneymatters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Clothes extends AppCompatActivity implements View.OnClickListener {

    private Button addButton;
    private ListView list;
    public EditText date, amount;
    public String d, a, obj;
    Expenses exp = new Expenses();
    String clothesobjects = "clothesobjects.txt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clothes);

        addButton = (Button) findViewById(R.id.add_clothes);
        list = (ListView) findViewById(R.id.addedclothes);
        date = (EditText) findViewById(R.id.date);
        amount = (EditText) findViewById(R.id.amount);

        exp.displayList(list, clothesobjects, getApplicationContext());
    }

    @Override
    public void onClick(View v) {
        d = date.getText().toString();
        a = amount.getText().toString();
        obj = d + ": €" + a;

        if (!d.isEmpty() && !a.isEmpty()) {
            exp.writeExpenses(obj, "clothesobjects.txt", "clothes_expenses", getApplicationContext(), a);
        } else {
            Toast.makeText(Clothes.this, "Fill in date and expenses", Toast.LENGTH_SHORT).show();
        }
    }

    public void backToMain(View v){
        Intent back = new Intent(this, MainScreen.class);
        startActivity(back);
    }
}


