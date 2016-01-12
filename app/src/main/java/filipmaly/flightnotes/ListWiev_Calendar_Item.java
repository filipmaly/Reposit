package filipmaly.flightnotes;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ListWiev_Calendar_Item extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_wiev__calendar__item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    String Date_TextView = null;
    String Place_TextView = null;


    public String getCode() {
        return Date_TextView;
    }
    public void setCode(String date) {
        this.Date_TextView = date;
    }
    public String getName() {
        return Place_TextView;
    }
    public void setName(String place) {
        this.Place_TextView = place;
    }


}
