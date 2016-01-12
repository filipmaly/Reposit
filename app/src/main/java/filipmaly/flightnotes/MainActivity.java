package filipmaly.flightnotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity


{

    public void drawerClick(MenuItem item){        new NavClickHandler(this).itemClick(item);
    }

    public void buttonClick(View view){
        new NavClickHandler(this).buttonClick(view);
    }



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


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

        return super.onOptionsItemSelected(item);


    }

    public void kliknuti (View button)
    {
        switch (button.getId())
        {
            case R.id.startbuttonAirport:
                Intent intent1 = new Intent(getApplicationContext(), calendar_activity.class);
                startActivity(intent1);

                break;
            case R.id.startbuttonrecharge:
                Intent intent2 = new Intent(getApplicationContext(), ListViewActivity.class);
                startActivity(intent2);
                break;

            case R.id.startbuttonmodel:
                Intent intent3 = new Intent(getApplicationContext(), list_view_model.class);
                startActivity(intent3);
                break;

        }

    }

    }




