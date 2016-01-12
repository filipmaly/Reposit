package filipmaly.flightnotes;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class SpinnerMode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_mode);



    }

    String TextModeSpinner = null;


    public String getCode() {
        return TextModeSpinner;
    }
    public void setCode(String code) {
        this.TextModeSpinner = code;
    }


}
