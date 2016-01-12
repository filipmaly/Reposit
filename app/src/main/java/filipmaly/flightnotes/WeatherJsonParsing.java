package filipmaly.flightnotes;

import android.view.ViewDebug;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

/**
 * Created by filipmaly on 10. 12. 2015.
 */
public class WeatherJsonParsing {
    public static String JSONwind;
    public static String JSONweather;
    public static String JSONtemperature;



    // formatovani teplotniho minima a maxima do pozadovaneho formatu
    private String formatHighLows(double high, double low) {

        long roundedHigh = Math.round(high);
        long roundedLow = Math.round(low);

        String highLowStr = roundedHigh + " až " + roundedLow;
        return highLowStr;
    }

    String[] getWeatherDataFromJson(  String forecastJsonStr)
            throws JSONException

    {


        JSONObject forecastJson = new JSONObject(forecastJsonStr);
        JSONArray weatherArray = forecastJson.getJSONArray("list");
        String[] resultStrs = new String[40];
        for (int i = 0; i < weatherArray.length(); i++) {
            String highAndLow;
            JSONObject dayForecast = weatherArray.getJSONObject(0);
            JSONObject temperatureObject = dayForecast.getJSONObject("main");
            JSONArray weather = dayForecast.getJSONArray("weather");
            JSONObject weatherr = weather.getJSONObject(0);
            String weath = weatherr.getString("main");
            double high = temperatureObject.getDouble("temp_min");
            double low = temperatureObject.getDouble("temp_max");
            JSONObject windd = dayForecast.getJSONObject("wind");
            double wind = windd.getDouble("speed");
            int wind1 = windd.getInt("speed");

            low = low - 273.15;
            high = high - 273.15;
            highAndLow = formatHighLows(high, low);

            wind = (wind * 3.6);
            DecimalFormat df = new DecimalFormat("#,##");
            double wind2 = Double.valueOf(df.format(wind));
            JSONwind = Double.toString(wind2) + " km/h";
            JSONtemperature = highAndLow + " °C";
            JSONweather = weath;
            resultStrs[i] = highAndLow+"°C" + weath + wind ;



        }
        return resultStrs;
    }



}
