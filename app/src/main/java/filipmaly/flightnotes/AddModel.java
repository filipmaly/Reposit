package filipmaly.flightnotes;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddModel extends AppCompatActivity {

    DatabaseHelper myDb;

    EditText ModelEditText;
    TextView ModelNameTextview, Idmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_model);

        myDb = new DatabaseHelper(this);
        myDb.getWritableDatabase();

        ModelEditText = (EditText) findViewById(R.id.ModelEditText);
        ModelNameTextview = (TextView) findViewById(R.id.ModelNameTextview);
        Idmodel = (TextView) findViewById(R.id.Idmodel);


        nastavTextyModelu();

    }


    public void nastavTextyModelu () {
        Cursor res = myDb.getModelDataUpdate();


        Idmodel.setText(list_view_model.IdModelu);
        ModelNameTextview.setText(res.getString(1));
        ModelEditText.setText(res.getString(1));
        ModelEditText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

    }



    public void AddModelButtons(View button) {
        switch (button.getId()) {
            case R.id.DeleteModel:
                DeleteDataModel();
                Intent intent1 = new Intent(getApplicationContext(), list_view_model.class);
                startActivity(intent1);

                break;
            case R.id.SaveModel:
                savedataModel();
                Intent intent2 = new Intent(getApplicationContext(), list_view_model.class);
                startActivity(intent2);


                break;


        }
    }



    public void savedataModel()
    {
        boolean isUpdate = myDb.updateDataModel
                (
                        Idmodel.getText().toString(),
                        ModelEditText.getText().toString());

    }

    public  void DeleteDataModel() {
        Integer deleteRows = myDb.deleteDataModel(Idmodel.getText().toString());

    }

}
