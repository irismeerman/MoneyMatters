package nl.iris_meerman.moneymatters;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Expenses  {

    private ArrayAdapter<String> adapter;
    private ArrayList<String> strArray;
    SharedPreferences sp;
    float total;

    // displayList opens the text file that contains the date+amount objects.
    // It than reads the textfile and adds it to the strArray that will be
    // translated to data that can be displayed in the listview.
    // So it shows the objects in the listview.
    public void displayList(ListView list, String object, Context applicationContext)  {
        strArray = new ArrayList<String>();
        Scanner scan = null;
        String test = null;
        try {
            scan = new Scanner(applicationContext.openFileInput(object));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //Log.d("test object: ", );

        if (scan!=null) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                strArray.add(line);
            }
        }
        adapter = new ArrayAdapter<String>(applicationContext.getApplicationContext(),
                android.R.layout.simple_list_item_activated_1, strArray);
        list.setAdapter(adapter);
    }

    // writeExpenses makes sure that new entries are added to the textfile and that the
    // amount is added to the the total amount of expences for the specific tag.
    public void writeExpenses(String obj, String object, String outputfile, Context applicationContext, String a){
        strArray.add(obj);
        adapter.notifyDataSetChanged();

        PrintStream database = null;
        try {
            database = new PrintStream(applicationContext.openFileOutput(object, applicationContext.MODE_PRIVATE));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (String element: strArray){
            database.println(element);
        }
        database.close();
        sp = applicationContext.getSharedPreferences("my preferences", Context.MODE_PRIVATE);
        // get huidig amount of newtotalgroceries
        String ntg = sp.getString(outputfile, "00.00");
        // zorg dat je die kan editen
        SharedPreferences.Editor editor = sp.edit();
        // tel nieuwe bedrag bij opgeslagen bedrag op
        total = Float.parseFloat(ntg) + Float.parseFloat(a);
        // update de newtotalgroceries entry in de sharedpref
        editor.putString(outputfile, Float.toString(total));
        editor.commit();

    }


}
