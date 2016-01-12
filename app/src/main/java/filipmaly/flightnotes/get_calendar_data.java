package filipmaly.flightnotes;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Timestamp;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class get_calendar_data extends AppCompatActivity implements IAsyncResponse  {

    final String LOG_TAG = "test: ";

    //List<String> weekForecast;


    public static boolean spinhourset= false;
    public static boolean spinhourset2= false;
    public static boolean spinnerPlaceSet= false;


    //private SimpleCursorAdapter dataadapModel;

    DatabaseHelper myDb;
    Spinner spinnermodel1, spinnermodel2, spinnermodel3, spinnermodel4, spinnerhour, spinnerPlace;
    EditText editTextTime, editTextHour,editTextWeather,editTextWind,editTextTemp,editTextPlace, editTextDate;
    TextView textViewDate,textViewModel1,textViewModel2,textViewModel3,textViewModel4, getID_calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_calendar_data);


        myDb = new DatabaseHelper(this);
        myDb.getWritableDatabase();

        editTextTime = (EditText) findViewById((R.id.editTextTime));
        editTextHour = (EditText) findViewById((R.id.editTextHour));
        editTextWeather =(EditText) findViewById((R.id.editTextWeather));
        editTextWind= (EditText) findViewById((R.id.editTextWind));
        editTextTemp= (EditText) findViewById((R.id.editTextTemp));
        editTextPlace= (EditText) findViewById((R.id.editTextPlace));
        editTextDate= (EditText) findViewById((R.id.editTextDate));

        textViewDate = (TextView) findViewById(R.id.textViewDate);
        textViewModel1= (TextView) findViewById(R.id.textViewModel1);
        textViewModel2= (TextView) findViewById(R.id.textViewModel2);
        textViewModel3= (TextView) findViewById(R.id.textViewModel3);
        textViewModel4= (TextView) findViewById(R.id.textViewModel4);
        getID_calendar= (TextView) findViewById(R.id.getID_calendar);

        spinnermodel1 = (Spinner) findViewById(R.id.spinnermodel1);
        spinnermodel2 = (Spinner) findViewById(R.id.spinnermodel2);
        spinnermodel3 = (Spinner) findViewById(R.id.spinnermodel3);
        spinnermodel4 = (Spinner) findViewById(R.id.spinnermodel4);
        spinnerhour = (Spinner) findViewById(R.id.spinnerhour);
        spinnerPlace= (Spinner) findViewById(R.id.spinnerPlace);


        setspine2();
        setHourDate();
        stringminute();
        setSpinnerPlace();

        if(calendar_activity.NewDate == false)
        {
        setTextsCalendar();
        }

    }

    public void SaveCalendar (View button)
    {
        switch (button.getId())
        {
            case R.id.calendarsave:
                if(calendar_activity.NewDate== false)
                {
                          saveUpdatedData();                         ;
                         spinhourset =false;
                         spinhourset2 =false;
                         spinnerPlaceSet = false;
                     }
                else
                     {
                     savedataAirport2();
                         spinhourset =false;
                         spinhourset2 =false;
                         spinnerPlaceSet = false;

                     }

                if(validatDate()== true) {
                Intent intent1 = new Intent(getApplicationContext(), calendar_activity.class);
                startActivity(intent1);
                 }
                else {  Toast.makeText(getApplicationContext(),"Datum nesplňuje formát dd/mm/yyyy = "+(editTextDate.getText().toString()),Toast.LENGTH_LONG).show();               }


                break;
            case R.id.buttonCalendarDel:
                deleteDataAirport();
                spinhourset =false;
                spinhourset2 =false;
                spinnerPlaceSet = false;

                Intent intent1 = new Intent(getApplicationContext(), calendar_activity.class);
                startActivity(intent1);
                    break;
        }

    }


    public void setspine2() {
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        List<String> lables = db.getAllLabels();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnermodel1.setAdapter(dataAdapter);
        spinnermodel2.setAdapter(dataAdapter);
        spinnermodel3.setAdapter(dataAdapter);
        spinnermodel4.setAdapter(dataAdapter);


            spinnermodel1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (spinhourset2 == true) {
                        textViewModel1.setText(spinnermodel1.getSelectedItem().toString());
                        textViewModel1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                    }
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });
        spinnermodel2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinhourset2 == true) {
                    textViewModel2.setText(spinnermodel2.getSelectedItem().toString());
                    textViewModel2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
        spinnermodel3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinhourset2 == true) {
                    textViewModel3.setText(spinnermodel3.getSelectedItem().toString());
                    textViewModel3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
        spinnermodel4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinhourset2 == true)
                {
                textViewModel4.setText(spinnermodel4.getSelectedItem().toString());
                textViewModel4.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


            }}

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });



    }


    public void stringminute() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                   R.array.minuteItem, android.R.layout.simple_spinner_item);
           adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerhour.setAdapter(adapter);

        spinnerhour.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        spinnerhour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                if (spinhourset == true) {
                    editTextTime.setText(spinnerhour.getSelectedItem().toString());
                    editTextTime.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                }

            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
       });

   }

    //String forecastJsonStr = null;
    public void setSpinnerPlace() {
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.City, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPlace.setAdapter(adapter2);

        spinnerPlace.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        spinnerPlace.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {



                if (spinnerPlaceSet == true) {

                    editTextPlace.setText(spinnerPlace.getSelectedItem().toString());
                    editTextPlace.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    makeToast();

                    if(checkInternetConnection() == true)
                    {

                    new MyTask().execute();

                    SystemClock.sleep(7000);

                    editTextWeather.setText(WeatherJsonParsing.JSONweather);
                    editTextWind.setText(WeatherJsonParsing.JSONwind);
                    editTextTemp.setText(WeatherJsonParsing.JSONtemperature);
                        editTextWind.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        editTextTemp.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        editTextWeather.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    }


                }
                spinhourset = true;
                spinhourset2 = true;
                spinnerPlaceSet = true;


            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

    }

    public void setHourDate()
    {
        Time now = new Time();
        now.setToNow();
        editTextHour.setText(now.format("%k:%M"));
        editTextHour.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        textViewDate.setText(calendar_activity.selectedDate);
        textViewDate.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        editTextDate.setText(calendar_activity.selectedDate);
        editTextDate.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
    }

    public void setTextsCalendar ()
    {
        Cursor res = myDb.GetIdDate();

        getID_calendar.setText(res.getString(0));
        editTextDate.setText(res.getString(1));
        editTextHour.setText(res.getString(2));
        editTextPlace.setText(res.getString(3));
        editTextWeather.setText(res.getString(4));
        editTextTemp.setText(res.getString(5));
        textViewModel1.setText(res.getString(6));
        textViewModel2.setText(res.getString(7));
        textViewModel3.setText(res.getString(8));
        textViewModel4.setText(res.getString(9));
        editTextTime.setText(res.getString(11));
        editTextWind.setText(res.getString(12));

        getID_calendar.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        editTextDate.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        editTextHour.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        editTextPlace.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        editTextWeather.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        editTextTemp.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textViewModel1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textViewModel2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textViewModel3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textViewModel4.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        editTextTime.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        editTextWind.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
    }


    //Uložení nového záznamu do DB
    public void savedataAirport2() {
        boolean isUpdate = myDb.insertDataAirport
                (
                        editTextDate.getText().toString(),
                        editTextHour.getText().toString(),
                        editTextPlace.getText().toString(),
                        editTextWeather.getText().toString(),
                        editTextTemp.getText().toString(),
                        textViewModel1.getText().toString(),
                        textViewModel2.getText().toString(),
                        textViewModel3.getText().toString(),
                        textViewModel4.getText().toString(),
                        getchar(), // uložení id na filtr kalendáře
                        editTextTime.getText().toString(),
                        editTextWind.getText().toString()


                );

    }

    public void makeToast() {
        Toast.makeText(getApplicationContext(), "Stahuju Aktuální počasí počkej prosím", Toast.LENGTH_LONG).show();
    }

    //Update záznamu v DB
    public void saveUpdatedData()
    {
        boolean isUpdate = myDb.updateDataAirport

                (getID_calendar.getText().toString(),
                        editTextDate.getText().toString(),
                        editTextHour.getText().toString(),
                        editTextPlace.getText().toString(),
                        editTextWeather.getText().toString(),
                        editTextTemp.getText().toString(),
                        textViewModel1.getText().toString(),
                        textViewModel2.getText().toString(),
                        textViewModel3.getText().toString(),
                        textViewModel4.getText().toString(),
                        calendar_activity.monthnumber,
                        editTextTime.getText().toString(),
                        editTextWind.getText().toString());
    }
    //Metoda pro získání měsíce z datumu
    public String getchar(){

            String Str1 = new String(editTextDate.getText().toString());
            String month="";
            try{
                month= new StringBuilder().append(Str1.charAt(3)).toString() + new StringBuilder().append(Str1.charAt(4)).toString();

                            }catch( Exception ex){
                System.out.println(ex.toString());
                Toast.makeText(getApplicationContext(),"Nastala chyba "+ex.toString(),Toast.LENGTH_LONG).show();
            }
        return month;
    }
    //Metoda na kontrolu správného vstupu datumu
    public boolean validatDate() {
        Pattern p = Pattern.compile("[0-3]+[0-9]+/+[0-1]+[0-9]+/+[2]+[0]+[0-3]+[0-9]");
        Matcher m = p.matcher(editTextDate.getText().toString());
        if (m.find()) {
            return true;
        } else {
            return false;
        }
    }

    public  void deleteDataAirport() {
        Integer deleteDataAirport = myDb.deleteDataAirport(getID_calendar.getText().toString());

    }

    // kontrola zda je telefon připojený
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }




    // asynchronní třída
    public class MyTask extends AsyncTask<Void, Void, String[]> {
        IAsyncResponse delegate = (IAsyncResponse) get_calendar_data.this;

        @Override
        protected String[] doInBackground(Void... params) {
            try {
                return loadJSON();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String[] strings) {
            super.onPostExecute(strings);
        }
    }


    // stažení JSON, a volání parsovací metody
    public  String[] loadJSON() throws IOException {

        HttpURLConnection urlConnection = null;
        InputStream inputStream;

        BufferedReader reader = null;

        if (!isOnline()) {
            runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(getApplicationContext(), "Neni pripojeni - ukonceni!", Toast.LENGTH_SHORT).show();

                }
            });
            return null;
        }

        String forecastJsonStr = null;

        try {

            URL JSONPRAHA = new  URL("http://api.openweathermap.org/data/2.5/forecast/city?id=3067696&APPID=5e8416a20b7358b6e6fec9e442a3f606");
            URL JSONTABOR = new  URL("http://api.openweathermap.org/data/2.5/forecast/city?id=3064379&APPID=5e8416a20b7358b6e6fec9e442a3f606");
            URL JSONRUTNOV = new  URL("http://api.openweathermap.org/data/2.5/forecast/city?id=3063907&APPID=5e8416a20b7358b6e6fec9e442a3f606");
            URL JSONOTROKOVICE = new  URL("http://api.openweathermap.org/data/2.5/forecast/city?id=3068690&APPID=5e8416a20b7358b6e6fec9e442a3f606");
            URL JSONHODNIN = new  URL("http://api.openweathermap.org/data/2.5/forecast/city?id=3075654&APPID=5e8416a20b7358b6e6fec9e442a3f606");
            URL JSONPISEK = new  URL("http://api.openweathermap.org/data/2.5/forecast/city?id=3068293&APPID=5e8416a20b7358b6e6fec9e442a3f606");
            URL JSONPLZEN = new  URL("http://api.openweathermap.org/data/2.5/forecast/city?id=3068160&APPID=5e8416a20b7358b6e6fec9e442a3f606");

            URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/city?id=3067696&APPID=5e8416a20b7358b6e6fec9e442a3f606");

            //Vybírání HTML JSON
            String txtFromSpinner = spinnerPlace.getSelectedItem().toString();
            if(txtFromSpinner.equals("Praha") )
            {                url = JSONPRAHA;            }
            else if(txtFromSpinner.equals("Tabor"))
            {              url = JSONTABOR;            }
            else if(txtFromSpinner.equals("Otrokovice"))
            {             url = JSONOTROKOVICE;            }
            else if(txtFromSpinner.equals("Trutnov"))
            {                url = JSONRUTNOV;            }
            else if(txtFromSpinner.equals("Hodonin"))
            {                url = JSONHODNIN;            }
            else if(txtFromSpinner.equals("Pisek"))
            {              url = JSONPISEK;            }
            else if(txtFromSpinner.equals("Plzen"))
            {  url = JSONPLZEN;   }
            else if(txtFromSpinner.equals("Nezadano"))
            { return null;  }




            // Log.v(LOG_TAG, "Built URI " + buildURL().toString());

            inputStream = download(url, urlConnection);

            if (inputStream == null) {
                // inputstream je prazdny - chyba - return null
                return null;
            }

            forecastJsonStr = convertIS(inputStream, reader);
            if (forecastJsonStr == null) {
                return null;
            }

            Log.v(LOG_TAG, "Forecast string: " + forecastJsonStr);

        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error ", e);
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }

        try {
            WeatherJsonParsing weatherJsonParsing = new WeatherJsonParsing();
            return weatherJsonParsing.getWeatherDataFromJson(forecastJsonStr);

        } catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        }

        return null;
    }

    public InputStream download(URL url, HttpURLConnection urlConnection) {
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            return urlConnection.getInputStream();
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            return null;
        }

    }

    public String convertIS(InputStream inputStream, BufferedReader reader)
    {
        StringBuffer buffer = new StringBuffer();
        reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                // Pridanim dalsiho radku, se funkcnost JSONu nezmeni, ale v pripade debuggovani
                // to o mnoho zjednodussi praci
                buffer.append(line + "\n");
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            return null;
        }
        if (buffer.length() == 0) {
            return null;
        }
        return buffer.toString();
    }


    private boolean checkInternetConnection() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        // test for connection
        if (cm.getActiveNetworkInfo() != null
                && cm.getActiveNetworkInfo().isAvailable()
                && cm.getActiveNetworkInfo().isConnected()) {
            return true;
        } else {
            Log.v(LOG_TAG, "Nejsi připojený k internetu");
            Toast.makeText(getApplicationContext(),"Nejsi připojený k Internetu !! Zkus zapnout data nebo wifi připojení",Toast.LENGTH_LONG).show();

            return false;
        }
    }



}

