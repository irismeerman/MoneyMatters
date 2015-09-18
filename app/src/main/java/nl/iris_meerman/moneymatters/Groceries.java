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

public class Groceries extends AppCompatActivity implements View.OnClickListener {

    private Button addButton;
    private ListView list;
    private ArrayList<String> strArray;
    private ArrayAdapter<String> adapter;
    public EditText date, amount;
    public String d, a, obj;
    SharedPreferences sp;
    float total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.groceries);

        sp = getSharedPreferences("my preferences", Context.MODE_PRIVATE);

        addButton = (Button) findViewById(R.id.add_groceries);
        list = (ListView) findViewById(R.id.addedgroceries);
        strArray = new ArrayList<String>();
        date = (EditText) findViewById(R.id.date);
        amount = (EditText) findViewById(R.id.amount);

        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_activated_1, strArray);
        list.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        d = date.getText().toString();
        a = amount.getText().toString();
        obj = d + ": €" + a;

        if (! d.isEmpty() && ! a.isEmpty()) {
            strArray.add(obj);
            adapter.notifyDataSetChanged();
            // get huidig amount of newtotalgroceries
            String ntg = sp.getString("groceries_expenses", "00.00");
            // zorg dat je die kan editen
            SharedPreferences.Editor editor = sp.edit();
            // tel nieuwe bedrag bij opgeslagen bedrag op
            total = Float.parseFloat(ntg) + Float.parseFloat(a);
            // update de newtotalgroceries entry in de sharedpref
            editor.putString("groceries_expenses", Float.toString(total));
            editor.commit();
        } else {
            Toast.makeText(Groceries.this, "Fill in date and expenses", Toast.LENGTH_SHORT).show();
        }
    }

    public void backToMain(View v){
        Intent back = new Intent(this, MainScreen.class);
        startActivity(back);
    }
}


