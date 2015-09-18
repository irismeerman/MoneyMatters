package nl.iris_meerman.moneymatters;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends AppCompatActivity implements View.OnClickListener{

    Button save_changes;
    EditText groceries, clothes, tr, nl, di, pr, tf, in, re, ph ;
    TextView tot;

    String groceries1, clothes1, tr1, nl1, di1, pr1, tf1, in1, re1, ph1, totalS;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSavedBudgetValues();
    }


    public void getSavedBudgetValues(){
        sharedpreferences = getSharedPreferences("my preferences", Context.MODE_PRIVATE);

        // retrieve de waardes van de kosten om weer te geven
        String groc = sharedpreferences.getString("groceries", "00.00");
        groceries = (EditText) findViewById(R.id.groceries_budget);
        groceries.setText(groc);

        String clo = sharedpreferences.getString("clothes", "00.00");
        clothes = (EditText) findViewById(R.id.clothes_budget);
        clothes.setText(clo);

        String ni = sharedpreferences.getString("nightlife", "00.00");
        nl = (EditText) findViewById(R.id.nightlife_budget);
        nl.setText(ni);

        String tra = sharedpreferences.getString("travelling", "00.00");
        tr = (EditText) findViewById(R.id.travelling_budget);
        tr.setText(tra);

        String din = sharedpreferences.getString("dinners", "00.00");
        di = (EditText) findViewById(R.id.dinners_budget);
        di.setText(din);

        String pre = sharedpreferences.getString("presents", "00.00");
        pr = (EditText) findViewById(R.id.presents_budget);
        pr.setText(pre);

        String tui = sharedpreferences.getString("tuitionfee", "00.00");
        tf = (EditText) findViewById(R.id.tuitionfee_budget);
        tf.setText(tui);

        String ins = sharedpreferences.getString("insurances", "00.00");
        in = (EditText) findViewById(R.id.insurances_budget);
        in.setText(ins);

        String ren = sharedpreferences.getString("rent", "00.00");
        re = (EditText) findViewById(R.id.rent_budget);
        re.setText(ren);

        String pho = sharedpreferences.getString("phone", "00.00");
        ph = (EditText) findViewById(R.id.phone_budget);
        ph.setText(pho);

        String total = sharedpreferences.getString("total", "00.00");
        tot = (TextView) findViewById(R.id.total_expenses);
        tot.setText(total);

    }


    public void saveChanges(View v) {

        // get all values from inputfields
        groceries = (EditText) findViewById(R.id.groceries_budget);
        groceries1 = groceries.getText().toString();

        clothes = (EditText) findViewById(R.id.clothes_budget);
        clothes1 = clothes.getText().toString();

        tr = (EditText) findViewById(R.id.travelling_budget);
        tr1 = tr.getText().toString();

        nl = (EditText) findViewById(R.id.nightlife_budget);
        nl1 = nl.getText().toString();

        di = (EditText) findViewById(R.id.dinners_budget);
        di1 = di.getText().toString();

        pr = (EditText) findViewById(R.id.presents_budget);
        pr1 = pr.getText().toString();

        tf = (EditText) findViewById(R.id.tuitionfee_budget);
        tf1 = tf.getText().toString();

        in = (EditText) findViewById(R.id.insurances_budget);
        in1 = in.getText().toString();

        re = (EditText) findViewById(R.id.rent_budget);
        re1 = re.getText().toString();

        ph = (EditText) findViewById(R.id.phone_budget);
        ph1 = ph.getText().toString();


        // Sum the values to get total
        float total = Float.parseFloat(groceries1) + Float.parseFloat(clothes1)
                + Float.parseFloat(tr1) + Float.parseFloat(nl1)
                + Float.parseFloat(di1) + Float.parseFloat(pr1)
                + Float.parseFloat(tf1) + Float.parseFloat(in1)
                + Float.parseFloat(re1) + Float.parseFloat(ph1);

        totalS = Float.toString(total);
        totalS = totalS + "0";

        // save all values
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("groceries", groceries1);
        editor.putString("clothes", clothes1);
        editor.putString("travelling", tr1);
        editor.putString("nightlife", nl1);
        editor.putString("dinners", di1);
        editor.putString("presents", pr1);
        editor.putString("tuitionfee", tf1);
        editor.putString("insurances", in1);
        editor.putString("rent", re1);
        editor.putString("phone", ph1);
        editor.putString("total", totalS);

        editor.commit();

        // Toast message:
        Context context = getApplicationContext();
        CharSequence text = "Changes saved";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        // When saved, automatically return to main screen again
        Intent backToMain = new Intent(this, MainScreen.class);
        startActivity(backToMain);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }
}
