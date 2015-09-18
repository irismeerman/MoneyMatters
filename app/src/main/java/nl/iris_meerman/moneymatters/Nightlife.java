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

public class Nightlife extends AppCompatActivity implements View.OnClickListener {

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
        setContentView(R.layout.nightlife);
        sp = getSharedPreferences("my preferences", Context.MODE_PRIVATE);

        addButton = (Button) findViewById(R.id.add_nightlife);
        list = (ListView) findViewById(R.id.addednightlife);
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

        if (!d.isEmpty() && !a.isEmpty()) {
            obj = d + ": €" + a;
            strArray.add(obj);
            adapter.notifyDataSetChanged();

            String ntg = sp.getString("nightlife_expenses", "00.00");
            SharedPreferences.Editor editor = sp.edit();
            total = Float.parseFloat(ntg) + Float.parseFloat(a);
            editor.putString("nightlife_expenses", Float.toString(total));
            editor.commit();
        } else {
            Toast.makeText(Nightlife.this, "Fill in date and expenses", Toast.LENGTH_SHORT).show();
        }
    }

    public void backToMain(View v){
        Intent back = new Intent(this, MainScreen.class);
        startActivity(back);
    }
}


