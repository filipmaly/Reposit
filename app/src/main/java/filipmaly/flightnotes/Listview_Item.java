package filipmaly.flightnotes;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Listview_Item extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview__item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }
    String textView_ID_Battery = null;
    String textView_Name_Battery = null;
    String textView_Cycle_Battery = null;
    String textView_Lastuse_Battery = null;

    public String getCode() {
        return textView_ID_Battery;
    }
    public void setCode(String code) {
        this.textView_ID_Battery = code;
    }
    public String getName() {
        return textView_Name_Battery;
    }
    public void setName(String name) {
        this.textView_Name_Battery = name;
    }
    public String getContinent() {
        return textView_Cycle_Battery;
    }
    public void setContinent(String continent) {
        this.textView_Cycle_Battery = continent;
    }
    public String getRegion() {
        return textView_Lastuse_Battery;
    }
    public void setRegion(String region) {
        this.textView_Lastuse_Battery = region;
    }


}



