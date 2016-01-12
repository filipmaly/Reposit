package filipmaly.flightnotes;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static filipmaly.flightnotes.R.*;

public class get_vallue_button extends AppCompatActivity {
    public static int countofcycle = 0;
    DatabaseHelper myDb;

    EditText editNameGet,TypeSetGet,cellNumberGet, capacityGet,lastGet,timerGet,TextWievCycle,editID_get  ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_get_vallue_button);

        myDb = new DatabaseHelper(this);
        myDb.getWritableDatabase();

        editID_get = (EditText) findViewById(R.id.editID_get);
         editNameGet = (EditText) findViewById(R.id.editNameGet);
         TypeSetGet = (EditText) findViewById(R.id.TypeSetGet);
         cellNumberGet = (EditText) findViewById(R.id.cellNumberGet);
         capacityGet = (EditText) findViewById(R.id.capacityGet);
         lastGet = (EditText) findViewById(R.id.lastGet);
         timerGet = (EditText) findViewById(R.id.timerGet);
         TextWievCycle = (EditText) findViewById(R.id.TextWievCycle);



        nastavTexty();

    }


    public void nastavTexty () {
    Cursor res = myDb.getIDData();


        editID_get.setText(ListViewActivity.Idkliknuti);
        editNameGet.setText(res.getString(1));
        TypeSetGet.setText(res.getString(2));
        cellNumberGet.setText(res.getString(3));
        capacityGet.setText(res.getString(4));
        lastGet.setText(res.getString(5));
        timerGet.setText(res.getString(6));
        TextWievCycle.setText(res.getString(7));
        countofcycle = (res.getInt(7));

        editID_get.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        editNameGet.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        TypeSetGet.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        cellNumberGet.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        capacityGet.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        lastGet.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        timerGet.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        TextWievCycle.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


    }



    public void changeCycle(View button) {
        switch (button.getId()) {
            case id.buttonADDcycle:
                changeplus();

                break;
            case id.buttonDELcycle:
                changeminus();


                break;


            case id.saveBatt:

                savedata();
                Intent intent1 = new Intent(getApplicationContext(), ListViewActivity.class);
                startActivity(intent1);

                break;

            case id.deleleteBatt:

                DeleteData();

                Intent intent2 = new Intent(getApplicationContext(), ListViewActivity.class);
                startActivity(intent2);

                break;

            case id.saveactual:
                saveactualdata();


                Intent intent3 = new Intent(getApplicationContext(), ListViewActivity.class);
                startActivity(intent3);

                break;


        }
    }

    public void changeplus() {
        TextView newview = (TextView) findViewById(id.TextWievCycle);
        countofcycle = countofcycle + 1;
        String helpstring = Integer.toString(countofcycle);
        newview.setText(helpstring);

    }

    public void changeminus() {
        TextView newview = (TextView) findViewById(id.TextWievCycle);
        if (countofcycle != 0) {
            countofcycle = countofcycle - 1;
        }
        String helpstring = Integer.toString(countofcycle);
        newview.setText(helpstring);


    }

    public void savedata()
{
    boolean isUpdate = myDb.updateData
            (
                    editID_get.getText().toString(),
                    editNameGet.getText().toString(),
                    TypeSetGet.getText().toString(),
                    cellNumberGet.getText().toString(),
                    capacityGet.getText().toString(),
                    lastGet.getText().toString(),
                    timerGet.getText().toString(),
                    TextWievCycle.getText().toString());
}


    public void saveactualdata()
    {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        String actualdate = (dateFormat.format(cal.getTime())).toString();
        Time now = new Time();
        now.setToNow();
        String actualtime = now.format("%k:%M");
        changeplus();
        boolean isUpdate = myDb.updateData
                (
                        editID_get.getText().toString(),
                        editNameGet.getText().toString(),
                        TypeSetGet.getText().toString(),
                        cellNumberGet.getText().toString(),
                        capacityGet.getText().toString(),
                        actualdate,
                        actualtime,
                        TextWievCycle.getText().toString());
    }

    public  void DeleteData() {
        Integer deleteRows = myDb.deleteData(editID_get.getText().toString());

    }

}