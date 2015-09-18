package nl.iris_meerman.moneymatters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import static nl.iris_meerman.moneymatters.R.id.groceries;
import static nl.iris_meerman.moneymatters.R.id.nightlife;
import static nl.iris_meerman.moneymatters.R.id.tuitionfee;

public class MainScreen extends AppCompatActivity {

    SharedPreferences sp;
    String groceriesexpenses, groceriesmaxbudget, clothesexpenses, clothesmaxbudget, travellingexpenses,travellingmaxbudget;
    String nightlifeexpenses, nightlifemaxbudget, dinnersexpenses, dinnersmaxbudget, presentsexpenses, presentsmaxbudget;
    String tuitionfeemaxbudget, insurancesmaxbudget, rentmaxbudget, phonemaxbudget;
    Float groceriesbalance, clothesbalance, travellingbalance, nightlifebalance, dinnersbalance, presentsbalance;
    Float tuitionfeefloat, insurancesfloat, rentfloat, phonefloat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        makeButtons();
    }

    public void makeButtons(){
        sp = getSharedPreferences("my preferences", Context.MODE_PRIVATE);
        // pak huidige groceries uitgaven en het max budget:
        groceriesexpenses = sp.getString("groceries_expenses", "00.00");
        groceriesmaxbudget = sp.getString("groceries", "00.00");
        // balans van groceries:
        groceriesbalance = Float.parseFloat(groceriesmaxbudget) - Float.parseFloat(groceriesexpenses);
        // pak de button om de text te zetten:
        Button groceries = (Button) findViewById(R.id.groceries);
        // pak huidige max budget van groceries:
        groceries.setText("Groceries\n€" + String.format("%.2f", groceriesbalance));

        clothesexpenses = sp.getString("clothes_expenses", "00.00");
        clothesmaxbudget = sp.getString("clothes", "00.00");
        clothesbalance = Float.parseFloat(clothesmaxbudget) - Float.parseFloat(clothesexpenses);
        Button clothes = (Button) findViewById(R.id.clothes);
        clothes.setText("Clothes\n€" + String.format("%.2f", clothesbalance));

        travellingexpenses = sp.getString("travelling_expenses", "00.00");
        travellingmaxbudget = sp.getString("travelling", "00.00");
        travellingbalance = Float.parseFloat(travellingmaxbudget) - Float.parseFloat(travellingexpenses);
        Button travelling = (Button) findViewById(R.id.travelling);
        travelling.setText("Travelling\n€" + String.format("%.2f", travellingbalance));

        nightlifeexpenses = sp.getString("nightlife_expenses", "00.00");
        nightlifemaxbudget = sp.getString("nightlife", "00.00");
        nightlifebalance = Float.parseFloat(nightlifemaxbudget) - Float.parseFloat(nightlifeexpenses);
        Button nightlife = (Button) findViewById(R.id.nightlife);
        nightlife.setText("Nightlife\n€" + String.format("%.2f", nightlifebalance));

        dinnersexpenses = sp.getString("dinners_expenses", "00.00");
        dinnersmaxbudget = sp.getString("dinners", "00.00");
        dinnersbalance = Float.parseFloat(dinnersmaxbudget) - Float.parseFloat(dinnersexpenses);
        Button dinners = (Button) findViewById(R.id.dinners);
        dinners.setText("Dinners\n€" + String.format("%.2f", dinnersbalance));

        presentsexpenses = sp.getString("presents_expenses", "00.00");
        presentsmaxbudget = sp.getString("presents", "00.00");
        presentsbalance = Float.parseFloat(presentsmaxbudget) - Float.parseFloat(presentsexpenses);
        Button presents = (Button) findViewById(R.id.presents);
        presents.setText("Presents\n€" + String.format("%.2f", presentsbalance));

        tuitionfeemaxbudget = sp.getString("tuitionfee", "00.00");
        Button tuitionfee = (Button) findViewById(R.id.tuitionfee);
        tuitionfeefloat = Float.parseFloat(tuitionfeemaxbudget);
        tuitionfee.setText("Tuition fee\n€" + String.format("%.2f", tuitionfeefloat));

        insurancesmaxbudget = sp.getString("insurances", "00.00");
        Button insurances = (Button) findViewById(R.id.insurances);
        insurancesfloat = Float.parseFloat(insurancesmaxbudget);
        insurances.setText("Insurances\n€" + String.format("%.2f", insurancesfloat));

        rentmaxbudget = sp.getString("rent", "00.00");
        Button rent = (Button) findViewById(R.id.rent);
        rentfloat = Float.parseFloat(rentmaxbudget);
        rent.setText("Rent\n€" + String.format("%.2f", rentfloat));

        phonemaxbudget = sp.getString("phone", "00.00");
        Button phone = (Button) findViewById(R.id.phone);
        phonefloat = Float.parseFloat(phonemaxbudget);
        phone.setText("Phone\n€" + String.format("%.2f", phonefloat));

        Float balancefloat = groceriesbalance + clothesbalance + travellingbalance +
                nightlifebalance + dinnersbalance + presentsbalance;
        TextView balancebar = (TextView) findViewById(R.id.balancebar);
        balancebar.setText("Balance: €" + String.format("%.2f", balancefloat));
    }

    public void addGroceries(View v){
        Intent goToGroc = new Intent(this, Groceries.class);
        startActivity(goToGroc);
    }

    public void addClothes(View v){
        Intent goToClo = new Intent(this, Clothes.class);
        startActivity(goToClo);
    }

    public void addTravelling(View v){
        Intent goToTra = new Intent(this, Travelling.class);
        startActivity(goToTra);
    }

    public void addNightlife(View v){
        Intent goToNig = new Intent(this, Nightlife.class);
        startActivity(goToNig);
    }

    public void addDinners(View v){
        Intent goToDin = new Intent(this, Dinners.class);
        startActivity(goToDin);
    }

    public void addPresents(View v){
        Intent goToPre = new Intent(this, Presents.class);
        startActivity(goToPre);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.reset){
            alertDialog();
        }
        return super.onOptionsItemSelected(item);
    }



    private void alertDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure to reset your expenses?");
        builder.setTitle("Confirmation Dialog");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                SharedPreferences.Editor editor = sp.edit();
                editor.putString("groceries_expenses", "00.00");
                editor.putString("clothes_expenses", "00.00");
                editor.putString("travelling_expenses", "00.00");
                editor.putString("nightlife_expenses", "00.00");
                editor.putString("dinners_expenses", "00.00");
                editor.putString("presents_expenses", "00.00");

                editor.commit();
                finish();
                startActivity(getIntent());;
                Toast.makeText(MainScreen.this, "Reset confirmed", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }
}
