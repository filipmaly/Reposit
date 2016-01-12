package filipmaly.flightnotes;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by filipmaly on 9. 11. 2015.
 */
public class NavClickHandler {

    private Activity activity;

    public NavClickHandler(Activity activity){ this.activity = activity;
    }

    public void itemClick(MenuItem item){
        runActivity(item.getItemId());
    }
    public void buttonClick(View view){
        runActivity(view.getId());
    }

    private void runActivity (int id) {
        Intent intent = null;
        switch (id){
            case R.id.drawer_menu:
                intent = new Intent(activity,MainActivity.class);
                break;

            case R.id.Kalendar:
                intent = new Intent(activity,calendar_activity.class);
                break;
            case R.id.Baterie:
                intent = new Intent(activity,ListViewActivity.class);
                break;
            case R.id.Modely:
                intent = new Intent(activity,list_view_model.class);
                break;

            case R.id.Autori:
                intent = new Intent(activity,About.class);
                break;


        }
        if (intent != null) activity.startActivity(intent);
    }





}
