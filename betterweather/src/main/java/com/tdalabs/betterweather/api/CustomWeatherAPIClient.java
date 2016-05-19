package com.tdalabs.betterweather.api;

import net.imatruck.betterweather.BetterWeatherData;
import net.imatruck.betterweather.BuildConfig;
import net.imatruck.betterweather.LocationInfo;
import net.imatruck.betterweather.utils.JsonReader;
import net.imatruck.betterweather.utils.LogUtils;
import net.imatruck.betterweather.weatherapi.YahooWeatherAPIClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static net.imatruck.betterweather.utils.LogUtils.LOGE;

/**
 * Created by liviu on 2016-03-14.
 */
public class CustomWeatherAPIClient extends YahooWeatherAPIClient {

    private static final String TAG = LogUtils.makeLogTag(CustomWeatherAPIClient.class);

    @Override
    public BetterWeatherData getWeatherDataForLocation(LocationInfo locationInfo) throws IOException {
        BetterWeatherData data = super.getWeatherDataForLocation(locationInfo);

        JSONObject responseCurrent;

        try {
            responseCurrent = JsonReader.readJsonFromUrl(String.format(Locale.getDefault(), BuildConfig.LOCAL_SENSOR_ENDPOINT));
        }
        catch (JSONException je){
            LOGE(TAG, "Error parsing JSON from local sensor", je);
            return new BetterWeatherData(BetterWeatherData.ErrorCodes.API);
        }
        catch (FileNotFoundException fnfe){
            LOGE(TAG, "Error opening JSON stream from local sensor", fnfe);
            return new BetterWeatherData(BetterWeatherData.ErrorCodes.API);
        }

        if (parseCurrentConditionsData(data, responseCurrent)) {
            return new BetterWeatherData(BetterWeatherData.ErrorCodes.API);
        }

        return data;
    }

    private boolean parseCurrentConditionsData(BetterWeatherData data, JSONObject response) {
        if(response != null) {
            try {
                data.temperature = (int) Math.round(response.getDouble("TEMPERATURE"));
                data.humidity = Integer.toString(response.getInt("HUMIDITY"));

                DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date lastValueDate = fmt.parse(response.getString("TIMESTAMP"));

                if (Calendar.getInstance().getTime().getTime() - lastValueDate.getTime() > 1000 * 60 * 15 ) { // Check if we exceeded 15 mins timeout
                    LOGE(TAG, "Last value is older than 15 mins. Check your sensor!");
                    return true;
                }
            } catch (JSONException je) {
                LOGE(TAG, "Error parsing local sensor temperature or humidity");
                return true;
            } catch (ParseException e) {
                LOGE(TAG, "Error parsing local sensor last value timestamp");
                return true;
            }
        }

        return false;
    }
}
