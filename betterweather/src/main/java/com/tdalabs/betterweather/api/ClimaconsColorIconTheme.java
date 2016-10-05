package com.tdalabs.betterweather.api;

import net.imatruck.betterweather.R;
import net.imatruck.betterweather.iconthemes.IIconTheme;
import net.imatruck.betterweather.utils.LogUtils;

import java.text.Format;

import static net.imatruck.betterweather.utils.LogUtils.LOGD;
import static net.imatruck.betterweather.utils.LogUtils.LOGE;

/**
 * Created by Liviu on 2016-05-23.
 */
public class ClimaconsColorIconTheme implements IIconTheme {

    private static final String TAG = LogUtils.makeLogTag(ClimaconsColorIconTheme.class);

    private enum TemperatureRange {
        COLD(100), MILD(200), PLEASANT(300), WARM(400), HOT(500);

        private final int range;

        TemperatureRange(int range) {
            this.range = range;
        }

        public int getValue() {
            return range;
        }
    }

    private TemperatureRange mapTemperatureToRange(int temperature) {

        if (temperature <= 0) {
            return TemperatureRange.COLD;

        } else if (temperature > 0 && temperature <= 10) {
            return TemperatureRange.MILD;

        } else if (temperature > 10 && temperature <= 20) {
            return TemperatureRange.PLEASANT;

        } else if (temperature > 20 && temperature <= 30) {
            return TemperatureRange.WARM;

        } else {
            return TemperatureRange.HOT;
        }

    }

    @Override
    public int getConditionIcon(int conditionCode, int temperature) {

        // Get temperature range
        TemperatureRange range = this.mapTemperatureToRange(temperature);
        LOGD(TAG, String.format("Temperature range %s - %s", range, conditionCode + range.getValue()));

        switch (conditionCode + range.getValue()) {

            // TemperatureRange.COLD
            case 120: // foggy
                return R.drawable.climacons_foggy_cold;
            case 121: // haze
            case 119: // dust
            case 122: // smoky
                return R.drawable.climacons_smoky_cold;
            case 125: // cold
                return R.drawable.climacons_cold_cold;
            case 126: // cloudy
                return R.drawable.climacons_cloudy_cold;
            case 127: // mostly cloudy (night)
            case 129: // partly cloudy (night)
                return R.drawable.climacons_partly_cloudy_night_cold;
            case 128: // mostly cloudy (day)
            case 130: // partly cloudy (day)
            case 144: // partly cloudy
                return R.drawable.climacons_partly_cloudy_cold;
            case 131: // clear (night)
            case 133: // fair (night)
                return R.drawable.climacons_clear_night_cold;
            case 134: // fair (day)
            case 132: // sunny
            case 123: // blustery
            case 136: // hot
                return R.drawable.climacons_sunny_cold;
            case 100: // tornado
            case 101: // tropical storm
            case 102: // hurricane
            case 124: // windy
                return R.drawable.climacons_windy_cold;
            case 105: // mixed rain and snow
            case 106: // mixed rain and sleet
            case 107: // mixed snow and sleet
            case 118: // sleet
            case 108: // freezing drizzle
            case 110: // freezing rain
            case 114: // light snow showers
                return R.drawable.climacons_mixed_rain_and_snow_cold;
            case 117: // hail
            case 135: // mixed rain and hail
                return R.drawable.climacons_hail_cold;
            case 109: // drizzle
                return R.drawable.climacons_drizzle_cold;
            case 111: // showers
            case 112: // showers
                return R.drawable.climacons_showers_cold;
            case 140: // scattered showers
                return R.drawable.climacons_scattered_showers_cold;
            case 103: // severe thunderstorms
            case 104: // thunderstorms
            case 137: // isolated thunderstorms
            case 145: // thundershowers
            case 147: // isolated thundershowers
                return R.drawable.climacons_thunderstorms_cold;
            case 138: // scattered thunderstorms
            case 139: // scattered thunderstorms
                return R.drawable.climacons_scattered_thunderstorms_cold;
            case 115: // blowing snow
                return R.drawable.climacons_blowing_snow_cold;
            case 116: // snow
            case 141: // heavy snow
            case 143: // heavy snow
            case 146: // snow showers
            case 113: // snow flurries
            case 142: // scattered snow showers
                return R.drawable.climacons_heavy_snow_cold;

            // TemperatureRange.MILD
            case 220: // foggy
                return R.drawable.climacons_foggy_mild;
            case 221: // haze
            case 219: // dust
            case 222: // smoky
                return R.drawable.climacons_smoky_mild;
            case 225: // cold
                return R.drawable.climacons_cold_mild;
            case 226: // cloudy
                return R.drawable.climacons_cloudy_mild;
            case 227: // mostly cloudy (night)
            case 229: // partly cloudy (night)
                return R.drawable.climacons_partly_cloudy_night_mild;
            case 228: // mostly cloudy (day)
            case 230: // partly cloudy (day)
            case 244: // partly cloudy
                return R.drawable.climacons_partly_cloudy_mild;
            case 231: // clear (night)
            case 233: // fair (night)
                return R.drawable.climacons_clear_night_mild;
            case 234: // fair (day)
            case 232: // sunny
            case 223: // blustery
            case 236: // hot
                return R.drawable.climacons_sunny_mild;
            case 200: // tornado
            case 201: // tropical storm
            case 202: // hurricane
            case 224: // windy
                return R.drawable.climacons_windy_mild;
            case 205: // mixed rain and snow
            case 206: // mixed rain and sleet
            case 207: // mixed snow and sleet
            case 218: // sleet
            case 208: // freezing drizzle
            case 210: // freezing rain
            case 214: // light snow showers
                return R.drawable.climacons_mixed_rain_and_snow_mild;
            case 217: // hail
            case 235: // mixed rain and hail
                return R.drawable.climacons_hail_mild;
            case 209: // drizzle
                return R.drawable.climacons_drizzle_mild;
            case 211: // showers
            case 212: // showers
                return R.drawable.climacons_showers_mild;
            case 240: // scattered showers
                return R.drawable.climacons_scattered_showers_mild;
            case 203: // severe thunderstorms
            case 204: // thunderstorms
            case 237: // isolated thunderstorms
            case 245: // thundershowers
            case 247: // isolated thundershowers
                return R.drawable.climacons_thunderstorms_mild;
            case 238: // scattered thunderstorms
            case 239: // scattered thunderstorms
                return R.drawable.climacons_scattered_thunderstorms_mild;
            case 215: // blowing snow
                return R.drawable.climacons_blowing_snow_mild;
            case 216: // snow
            case 241: // heavy snow
            case 243: // heavy snow
            case 246: // snow showers
            case 213: // snow flurries
            case 242: // scattered snow showers
                return R.drawable.climacons_heavy_snow_mild;

            // TemperatureRange.PLEASANT
            case 320: // foggy
                return R.drawable.climacons_foggy_pleasant;
            case 321: // haze
            case 319: // dust
            case 322: // smoky
                return R.drawable.climacons_smoky_pleasant;
            case 325: // cold
                return R.drawable.climacons_cold_pleasant;
            case 326: // cloudy
                return R.drawable.climacons_cloudy_pleasant;
            case 327: // mostly cloudy (night)
            case 329: // partly cloudy (night)
                return R.drawable.climacons_partly_cloudy_night_pleasant;
            case 328: // mostly cloudy (day)
            case 330: // partly cloudy (day)
            case 344: // partly cloudy
                return R.drawable.climacons_partly_cloudy_pleasant;
            case 331: // clear (night)
            case 333: // fair (night)
                return R.drawable.climacons_clear_night_pleasant;
            case 334: // fair (day)
            case 332: // sunny
            case 323: // blustery
            case 336: // hot
                return R.drawable.climacons_sunny_pleasant;
            case 300: // tornado
            case 301: // tropical storm
            case 302: // hurricane
            case 324: // windy
                return R.drawable.climacons_windy_pleasant;
            case 305: // mixed rain and snow
            case 306: // mixed rain and sleet
            case 307: // mixed snow and sleet
            case 318: // sleet
            case 308: // freezing drizzle
            case 310: // freezing rain
            case 314: // light snow showers
                return R.drawable.climacons_mixed_rain_and_snow_pleasant;
            case 317: // hail
            case 335: // mixed rain and hail
                return R.drawable.climacons_hail_pleasant;
            case 309: // drizzle
                return R.drawable.climacons_drizzle_pleasant;
            case 311: // showers
            case 312: // showers
                return R.drawable.climacons_showers_pleasant;
            case 340: // scattered showers
                return R.drawable.climacons_scattered_showers_pleasant;
            case 303: // severe thunderstorms
            case 304: // thunderstorms
            case 337: // isolated thunderstorms
            case 345: // thundershowers
            case 347: // isolated thundershowers
                return R.drawable.climacons_thunderstorms_pleasant;
            case 338: // scattered thunderstorms
            case 339: // scattered thunderstorms
                return R.drawable.climacons_scattered_thunderstorms_pleasant;
            case 315: // blowing snow
                return R.drawable.climacons_blowing_snow_pleasant;
            case 316: // snow
            case 341: // heavy snow
            case 343: // heavy snow
            case 346: // snow showers
            case 313: // snow flurries
            case 342: // scattered snow showers
                return R.drawable.climacons_heavy_snow_pleasant;

            // TemperatureRange.WARM
            case 420: // foggy
                return R.drawable.climacons_foggy_warm;
            case 421: // haze
            case 419: // dust
            case 422: // smoky
                return R.drawable.climacons_smoky_warm;
            case 425: // cold
                return R.drawable.climacons_cold_warm;
            case 426: // cloudy
                return R.drawable.climacons_cloudy_warm;
            case 427: // mostly cloudy (night)
            case 429: // partly cloudy (night)
                return R.drawable.climacons_partly_cloudy_night_warm;
            case 428: // mostly cloudy (day)
            case 430: // partly cloudy (day)
            case 444: // partly cloudy
                return R.drawable.climacons_partly_cloudy_warm;
            case 431: // clear (night)
            case 433: // fair (night)
                return R.drawable.climacons_clear_night_warm;
            case 434: // fair (day)
            case 432: // sunny
            case 423: // blustery
            case 436: // hot
                return R.drawable.climacons_sunny_warm;
            case 400: // tornado
            case 401: // tropical storm
            case 402: // hurricane
            case 424: // windy
                return R.drawable.climacons_windy_warm;
            case 405: // mixed rain and snow
            case 406: // mixed rain and sleet
            case 407: // mixed snow and sleet
            case 418: // sleet
            case 408: // freezing drizzle
            case 410: // freezing rain
            case 414: // light snow showers
                return R.drawable.climacons_mixed_rain_and_snow_warm;
            case 417: // hail
            case 435: // mixed rain and hail
                return R.drawable.climacons_hail_warm;
            case 409: // drizzle
                return R.drawable.climacons_drizzle_warm;
            case 411: // showers
            case 412: // showers
                return R.drawable.climacons_showers_warm;
            case 440: // scattered showers
                return R.drawable.climacons_scattered_showers_warm;
            case 403: // severe thunderstorms
            case 404: // thunderstorms
            case 437: // isolated thunderstorms
            case 445: // thundershowers
            case 447: // isolated thundershowers
                return R.drawable.climacons_thunderstorms_warm;
            case 438: // scattered thunderstorms
            case 439: // scattered thunderstorms
                return R.drawable.climacons_scattered_thunderstorms_warm;
            case 415: // blowing snow
                return R.drawable.climacons_blowing_snow_warm;
            case 416: // snow
            case 441: // heavy snow
            case 443: // heavy snow
            case 446: // snow showers
            case 413: // snow flurries
            case 442: // scattered snow showers
                return R.drawable.climacons_heavy_snow_warm;

            // TemperatureRange.HOT
            case 520: // foggy
                return R.drawable.climacons_foggy_hot;
            case 521: // haze
            case 519: // dust
            case 522: // smoky
                return R.drawable.climacons_smoky_hot;
            case 525: // cold
                return R.drawable.climacons_cold_hot;
            case 526: // cloudy
                return R.drawable.climacons_cloudy_hot;
            case 527: // mostly cloudy (night)
            case 529: // partly cloudy (night)
                return R.drawable.climacons_partly_cloudy_night_hot;
            case 528: // mostly cloudy (day)
            case 530: // partly cloudy (day)
            case 544: // partly cloudy
                return R.drawable.climacons_partly_cloudy_hot;
            case 531: // clear (night)
            case 533: // fair (night)
                return R.drawable.climacons_clear_night_hot;
            case 534: // fair (day)
            case 532: // sunny
            case 523: // blustery
            case 536: // hot
                return R.drawable.climacons_sunny_hot;
            case 500: // tornado
            case 501: // tropical storm
            case 502: // hurricane
            case 524: // windy
                return R.drawable.climacons_windy_hot;
            case 505: // mixed rain and snow
            case 506: // mixed rain and sleet
            case 507: // mixed snow and sleet
            case 518: // sleet
            case 508: // freezing drizzle
            case 510: // freezing rain
            case 514: // light snow showers
                return R.drawable.climacons_mixed_rain_and_snow_hot;
            case 517: // hail
            case 535: // mixed rain and hail
                return R.drawable.climacons_hail_hot;
            case 509: // drizzle
                return R.drawable.climacons_drizzle_hot;
            case 511: // showers
            case 512: // showers
                return R.drawable.climacons_showers_hot;
            case 540: // scattered showers
                return R.drawable.climacons_scattered_showers_hot;
            case 503: // severe thunderstorms
            case 504: // thunderstorms
            case 537: // isolated thunderstorms
            case 545: // thundershowers
            case 547: // isolated thundershowers
                return R.drawable.climacons_thunderstorms_hot;
            case 538: // scattered thunderstorms
            case 539: // scattered thunderstorms
                return R.drawable.climacons_scattered_thunderstorms_hot;
            case 515: // blowing snow
                return R.drawable.climacons_blowing_snow_hot;
            case 516: // snow
            case 541: // heavy snow
            case 543: // heavy snow
            case 546: // snow showers
            case 513: // snow flurries
            case 542: // scattered snow showers
                return R.drawable.climacons_heavy_snow_hot;

            default:
                return R.drawable.climacons_sunny;
        }

    }

}
