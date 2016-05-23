package com.tdalabs.betterweather.api;

import net.imatruck.betterweather.BetterWeatherData;
import net.imatruck.betterweather.BetterWeatherExtension;
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

        if(responseCurrent != null) {
            try {
                int tempC = (int) Math.round(responseCurrent.getDouble("temperature"));

                switch (BetterWeatherExtension.getWeatherUnits()) {
                    case "f":
                        data.temperature = (int) Math.round((tempC * 9 / 5.0) + 32);
                        break;
                    case "c":
                        data.temperature = tempC;
                        break;
                    default:
                        throw new UndetectableTempUnitsException();
                }

                data.humidity = Integer.toString(responseCurrent.getInt("humidity"));

                DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date lastValueDate = fmt.parse(responseCurrent.getString("timestamp"));

                if (Calendar.getInstance().getTime().getTime() - lastValueDate.getTime() > 1000 * 60 * 15 ) { // Check if we exceeded 15 mins timeout
                    LOGE(TAG, "Last value is older than 15 mins. Check your sensor!");
                    throw new StaleAPIDataException();
                }
            } catch (JSONException e) {
                LOGE(TAG, "Error parsing local sensor temperature or humidity");
                return new BetterWeatherData(BetterWeatherData.ErrorCodes.API);
            } catch (ParseException e) {
                LOGE(TAG, "Error parsing local sensor last value timestamp");
                return new BetterWeatherData(BetterWeatherData.ErrorCodes.API);
            } catch (UndetectableTempUnitsException e) {
                LOGE(TAG, "Not able to detect temperature units");
                return new BetterWeatherData(BetterWeatherData.ErrorCodes.API);
            } catch (StaleAPIDataException e) {
                LOGE(TAG, "API returned stale data");
                return new BetterWeatherData(BetterWeatherData.ErrorCodes.STALE);
            }
        } else {
            LOGE(TAG, "Null response from API");
            return new BetterWeatherData(BetterWeatherData.ErrorCodes.API);
        }

        return data;
    }
}
