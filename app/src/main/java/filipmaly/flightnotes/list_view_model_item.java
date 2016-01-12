package filipmaly.flightnotes;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class list_view_model_item extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_model_item);




    }
    String TextModelName = null;


    public String getCode() {
        return TextModelName;
    }
    public void setCode(String code) {
        this.TextModelName = code;
    }


}
