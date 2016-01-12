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
import android.widget.ListView;


public class list_view_model extends AppCompatActivity {


    public void drawerClick(MenuItem item){        new NavClickHandler(this).itemClick(item);
    }

    public void buttonClick(View view){
        new NavClickHandler(this).buttonClick(view);
    }



    private SimpleCursorAdapter dataadapModel;
    DatabaseHelper db;

    public static String IdModelu="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_model);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DatabaseHelper(this);
        db.getWritableDatabase();

        displayListViewModel();
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



    private void displayListViewModel() {

        Cursor res = db.getModelData();
        String[] columnsModel = new String[]
                {
                        DatabaseHelper.COL_2P,
                };

        int[] Model_item = new int[]{
                R.id.TextModeSpinner,

        };


        dataadapModel = new SimpleCursorAdapter
                (
                        this,
                        R.layout.activity_list_view_model_item,
                        res,
                        columnsModel,
                        Model_item, 0);


        ListView listView1 = (ListView) findViewById(R.id.listViewModel);
        listView1.setAdapter(dataadapModel);


        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view,
                                    int position, long id) {
                // Get the cursor, positioned to the corresponding row in the result set
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);

               IdModelu = cursor.getString( cursor.getColumnIndex("_id") );


                Intent intent1 = new Intent(getApplicationContext(), AddModel.class);
                startActivity(intent1);



            }
        });
    }


    public void onClickModel(View button)
    {
        switch (button.getId())
        {
            case R.id.buttonADDModelListView:
                AddDataModel();
                displayListViewModel();

                break;

        }
    }

    public  void AddDataModel() {


        boolean isInserted = db.insertDataModel("Zadej jmeno");


    }

}
