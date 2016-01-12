package filipmaly.flightnotes;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.Time;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ListViewActivity extends AppCompatActivity {


    private SimpleCursorAdapter dataadap;
    DatabaseHelper myDb;
    public static String Idkliknuti="0";

    public void drawerClick(MenuItem item){        new NavClickHandler(this).itemClick(item);
    }

    public void buttonClick(View view){
        new NavClickHandler(this).buttonClick(view);
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery_listview);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        myDb = new DatabaseHelper(this);
        myDb.getWritableDatabase();

        displayListView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_design, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent2 = new Intent(getApplicationContext(), About.class);
            startActivity(intent2);

            return true;
        }
        if (id == R.id.action_back) {
            Intent intent4 = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent4);


            return true;
        }

        return super.onOptionsItemSelected(item);

    }





    private void displayListView() {


        Cursor res = myDb.getListData();

        String[] columns = new String[]
                {
                        DatabaseHelper.COL_5,
                        DatabaseHelper.COL_2,
                        DatabaseHelper.COL_8,
                        DatabaseHelper.COL_6
                };

        int[] itemfrom_xml = new int[]{
                R.id.Battery_ID,
                R.id.Battery_Name,
                R.id.TextModeSpinner,
                R.id.Battery_LastUse,
        };


        dataadap = new SimpleCursorAdapter
                (
                this,
                        R.layout.activity_listview__item,
                res,
                columns,
                itemfrom_xml, 0);


         ListView listView = (ListView) findViewById(R.id.listViewbattery);
         listView.setAdapter(dataadap);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view,
                                    int position, long id) {
                // Get the cursor, positioned to the corresponding row in the result set
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);


                Idkliknuti = cursor.getString( cursor.getColumnIndex("_id") );



                Intent intent1 = new Intent(getApplicationContext(), get_vallue_button.class);
                startActivity(intent1);



            }
        });
    }


    public void onClickListwiev (View button)
    {
        switch (button.getId())
        {
            case R.id.buttonADDbattListWiev:
                AddData();
                displayListView();
                break;


        }
    }

    public  void AddData() {

        Calendar call = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String actualdate = (dateFormat.format(call.getTime())).toString();
        Time now = new Time();
        now.setToNow();
        String actualtime = now.format("%k:%M");



        boolean isInserted = myDb.insertData("Zadej jmeno", "Zadej typ", "Zadej články", "Zadej kapacitu", actualdate , actualtime , "0");


    }

}

