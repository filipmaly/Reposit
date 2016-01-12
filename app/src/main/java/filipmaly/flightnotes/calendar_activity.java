package filipmaly.flightnotes;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.ListView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class calendar_activity extends AppCompatActivity {

    public void drawerClick(MenuItem item){        new NavClickHandler(this).itemClick(item);
    }

    public void buttonClick(View view){
        new NavClickHandler(this).buttonClick(view);
    }



    public static boolean NewDate;
    public static String IdkliknutiCalendar= "0";
    public static  String monthnumber = "01";
    private SimpleCursorAdapter dataadap;
    DatabaseHelper myDb;
    public static String selectedDate = "";

    CalendarView calendar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_listview);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myDb = new DatabaseHelper(this);
        myDb.getWritableDatabase();

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        selectedDate = (dateFormat.format(cal.getTime()));
        monthnumber = getchar();

        displayListViewCalendar();


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


    public void ChangeMonth (View button)
    {
        switch (button.getId())
        {
            case R.id.button1:
                monthnumber = "01";
                displayListViewCalendar();
                 break;

            case R.id.button2:
                monthnumber = "02";
                displayListViewCalendar();
                break;

            case R.id.button3:
                monthnumber = "03";
                displayListViewCalendar();

                break;
            case R.id.button4:
                monthnumber = "04";
                displayListViewCalendar();
                break;

            case R.id.button5:
                monthnumber = "05";
                displayListViewCalendar();
                break;

            case R.id.button6:
                monthnumber = "06";
                displayListViewCalendar();
                break;

            case R.id.button7:
                monthnumber = "07";
                displayListViewCalendar();
                break;

            case R.id.button8:
                monthnumber = "08";
                displayListViewCalendar();
            break;

            case R.id.button9:
                monthnumber = "09";
                displayListViewCalendar();
                break;

            case R.id.button10:
                monthnumber = "10";
                displayListViewCalendar();
            break;

            case R.id.button11:
                monthnumber = "11";
                displayListViewCalendar();

                break;
            case R.id.button12:
                monthnumber = "12";
                displayListViewCalendar();
                break;
        }

    }




    public void adddate (View button)
    {
        switch (button.getId())
        {
            case R.id.addcalendardate:
                NewDate = true;
            Intent intent1 = new Intent(getApplicationContext(), get_calendar_data.class);
            startActivity(intent1);
       }

    }

    private void displayListViewCalendar() {


        Cursor res = myDb.getDatePlace();

        String[] columns = new String[]
                {
                        DatabaseHelper.COL_2A,
                        DatabaseHelper.COL_4A

                };

        int[] itemfrom_xml = new int[]{
                R.id.Date_TextView,
                R.id.Place_TextView

        };


        dataadap = new SimpleCursorAdapter
                (
                        this,
                        R.layout.activity_list_wiev__calendar__item,
                        res,
                        columns,
                        itemfrom_xml, 0);


        ListView listView = (ListView) findViewById(R.id.listViewCalendar);
        listView.setAdapter(dataadap);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view,
                                    int position, long id) {
                // Získání pozice kurzoru
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);
                IdkliknutiCalendar = cursor.getString( cursor.getColumnIndex("_id") );
                NewDate = false;
                Intent intent1 = new Intent(getApplicationContext(), get_calendar_data.class);
                startActivity(intent1);
            }
        });

    }
    //Metoda pro získání měsíce z datumu
    public static String getchar(){

        String Str1 = new String(selectedDate);
        String month="";
        try{
            month= new StringBuilder().append(Str1.charAt(3)).toString() + new StringBuilder().append(Str1.charAt(4)).toString();
        }catch( Exception ex){
            System.out.println(ex.toString());
        }
        return month;
    }


}
