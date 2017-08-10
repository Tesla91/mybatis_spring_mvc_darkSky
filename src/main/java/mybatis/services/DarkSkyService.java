package mybatis.services;

import exceptions.APINotFoundException;
import exceptions.DBNotFoundException;
import mybatis.Util.DateUtil;
import mybatis.mappers.DarkSkyMapper;
import mybatis.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

/**
 * Created by nicola on 7/26/17.
 */
@Service
@SuppressWarnings("unused")
public class DarkSkyService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    DarkSkyMapper darkSkyMapper;

    public Weather getWeather(double latitude, double longitude){
        return restTemplate.getForObject(
                "https://api.darksky.net/forecast/8650ab2281f954862995ea2fabfb4502/"
                        + latitude + "," + longitude, Weather.class);
    }

    public Weather getWeatherWithDate(double latitude, double longitude, String myDate) {
        long timeInSecs = DateUtil.changeDateFromString(myDate);
        System.out.println(timeInSecs);

        return restTemplate.getForObject(
                "https://api.darksky.net/forecast/8650ab2281f954862995ea2fabfb4502/"
                        + latitude + "," + longitude + "," + timeInSecs, Weather.class);
    }

    public ArrayList<Summary> getYearlySummary(double latitude, double longitude, String myDate) {

        long timeInSecs = DateUtil.changeDateFromString(myDate);

        ArrayList<Summary> responses = new ArrayList<>();

        for (long i = timeInSecs; i < 1_483_228_800L; i += 31_557_600L) {

            Weather weatherWithSummary = restTemplate.getForObject(
                    "https://api.darksky.net/forecast/8650ab2281f954862995ea2fabfb4502/"
                            + latitude + "," + longitude + "," + i, Weather.class);

            String sunsetTime = DateUtil.changeDateFromLongWithMin(weatherWithSummary.getDaily().getData()[0].getSunsetTime());
            String sunriseTime = DateUtil.changeDateFromLongWithMin(weatherWithSummary.getDaily().getData()[0].getSunriseTime());
            String newDate = DateUtil.changeDateFromLong(i);

            Summary summary = new Summary();

            summary.setLatitude(latitude);
            summary.setLongitude(longitude);
            summary.setTimezone(weatherWithSummary.getTimezone());
            summary.setDate(newDate);
            summary.setSummary(weatherWithSummary.getDaily().getData()[0].getSummary());
            summary.setSunrise(sunriseTime);
            summary.setSunset(sunsetTime);
            summary.setPrecipProbability(weatherWithSummary.getDaily().getData()[0].getPrecipProbability());
            summary.setTemperatureMax(weatherWithSummary.getDaily().getData()[0].getTemperatureMax());
            summary.setWindSpeed(weatherWithSummary.getDaily().getData()[0].getWindSpeed());
            responses.add(summary);
        }

        return responses;
    }

    public ArrayList<HourlyAverage> getWeatherHourlyAverage(double latitude, double longitude) {

        ArrayList<HourlyAverage> averages = new ArrayList<>();

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        Weather weatherHourlyAverage = restTemplate.getForObject(
                "https://api.darksky.net/forecast/8650ab2281f954862995ea2fabfb4502/"
                        + latitude + "," + longitude, Weather.class);
        HourlyAverage hourlyAverage = new HourlyAverage();
        int i = 0;
        for(i = 0; i < weatherHourlyAverage.getHourly().getData().length; i++){

            hourlyAverage.setAverageTemperature(hourlyAverage.getAverageTemperature() +
                    weatherHourlyAverage.getHourly().getData()[i].getTemperature());
            hourlyAverage.setAverageApparentTemperature(hourlyAverage.getAverageApparentTemperature() +
                    weatherHourlyAverage.getHourly().getData()[i].getApparentTemperature());
            hourlyAverage.setAverageDewPoint(hourlyAverage.getAverageDewPoint() +
                    weatherHourlyAverage.getHourly().getData()[i].getDewPoint());
            hourlyAverage.setAverageHumidity(hourlyAverage.getAverageHumidity() +
                    weatherHourlyAverage.getHourly().getData()[i].getHumidity());
            hourlyAverage.setAverageWindSpeed(hourlyAverage.getAverageWindSpeed() +
                    weatherHourlyAverage.getHourly().getData()[i].getWindSpeed());
            hourlyAverage.setAverageWindBearing(hourlyAverage.getAverageWindBearing() +
                    weatherHourlyAverage.getHourly().getData()[i].getWindBearing());
            hourlyAverage.setAverageVisibility(hourlyAverage.getAverageVisibility() +
                    weatherHourlyAverage.getHourly().getData()[i].getVisibility());
            hourlyAverage.setAverageCloudCover(hourlyAverage.getAverageCloudCover() +
                    weatherHourlyAverage.getHourly().getData()[i].getCloudCover());
            hourlyAverage.setAveragePressure(hourlyAverage.getAveragePressure() +
                    weatherHourlyAverage.getHourly().getData()[i].getPressure());
            hourlyAverage.setAverageUvIndex(hourlyAverage.getAverageUvIndex() +
                    weatherHourlyAverage.getHourly().getData()[i].getUvIndex());
        }

        hourlyAverage.setLatitude(latitude);
        hourlyAverage.setLongitude(longitude);
        hourlyAverage.setTimezone(weatherHourlyAverage.getTimezone());
        hourlyAverage.setAverageTemperature(Math.round((hourlyAverage.getAverageTemperature() / (i+1))* 100d) / 100d);
        hourlyAverage.setAverageApparentTemperature(Math.round((hourlyAverage.getAverageApparentTemperature() / (i+1))* 100d) / 100d);
        hourlyAverage.setAverageDewPoint(Math.round((hourlyAverage.getAverageDewPoint() / (i+1))* 100d) / 100d);
        hourlyAverage.setAverageHumidity(Math.round((hourlyAverage.getAverageHumidity() / (i+1))* 100d) / 100d);
        hourlyAverage.setAverageWindSpeed(Math.round((hourlyAverage.getAverageWindSpeed() / (i+1))* 100d) / 100d);
        hourlyAverage.setAverageWindBearing(Math.round((hourlyAverage.getAverageWindBearing() / (i+1))* 100d) / 100d);
        hourlyAverage.setAverageVisibility(Math.round((hourlyAverage.getAverageVisibility() / (i+1))* 100d) / 100d);
        hourlyAverage.setAverageCloudCover(Math.round((hourlyAverage.getAverageCloudCover() / (i+1))* 100d) / 100d);
        hourlyAverage.setAveragePressure(Math.round((hourlyAverage.getAveragePressure() / (i+1))* 100d) / 100d);
        hourlyAverage.setAverageUvIndex(Math.round((hourlyAverage.getAverageUvIndex() / (i+1))* 100d) / 100d);
        averages.add(hourlyAverage);

        return averages;
    }

    @Cacheable("Forecast")
    public ArrayList<DailyForecast> getWeatherDailyForecast(double latitude, double longitude) throws APINotFoundException, DBNotFoundException{
        //call a method that will check if we have the next 8 days forecast in the db it returns an array
        // list of daily forecasts. If the array list is empty or less than 8 need to call the DarkSky API
        // get the next 8 days and only insert the missing days into mysql and return the list you got from the API
        // if the Array list has 8 elements return it
        ArrayList<DailyForecast> responses = new ArrayList<>();

        //if most recent item in the database is date + to 7 days from today
        if(populateDB()){

            System.out.println("populating");
            Weather weatherForecast;
            try {
                weatherForecast = restTemplate.getForObject(
                        "https://api.darksky.net/forecast/8650ab2281f954862995ea2fabfb4502/"
                                + latitude + "," + longitude, Weather.class);

                DailyForecast dailyForecast;
                int i = 0;
                for (i = 0; i < weatherForecast.getDaily().getData().length; i++) {
                    System.out.println(i);

                    dailyForecast = new DailyForecast();
                    String sunsetTime = DateUtil.changeDateFromLongWithMin(weatherForecast.getDaily().getData()[i].getSunsetTime());
                    String sunriseTime = DateUtil.changeDateFromLongWithMin(weatherForecast.getDaily().getData()[i].getSunriseTime());
                    String newDate = DateUtil.changeDateFromLong(weatherForecast.getDaily().getData()[i].getTime());

                    dailyForecast.setLatitude(latitude);
                    dailyForecast.setLongitude(longitude);
                    dailyForecast.setTimezone(weatherForecast.getTimezone());
                    dailyForecast.setDate(newDate);
                    dailyForecast.setSummary(weatherForecast.getDaily().getData()[i].getSummary());
                    dailyForecast.setSunrise(sunriseTime);
                    dailyForecast.setSunset(sunsetTime);
                    dailyForecast.setPrecipProbability(weatherForecast.getDaily().getData()[i].getPrecipProbability());
                    dailyForecast.setTemperatureMax(weatherForecast.getDaily().getData()[i].getTemperatureMax());
                    dailyForecast.setWindSpeed(weatherForecast.getDaily().getData()[i].getWindSpeed());
                    responses.add(dailyForecast);
                }

                darkSkyMapper.insertWeatherDailyForecast(responses.get(7));



                throw new APINotFoundException("DB not found.");
            }catch (APINotFoundException e){
                e.toString();
            }
        } else{
            try {
                //grab most recent 8 days from DB and return it to browser using weekForecast object
                responses = darkSkyMapper.getDailyForecast();
                Collections.reverse(responses);
                //System.out.println("DB");



                throw new DBNotFoundException("DB not found.");
            }catch (DBNotFoundException e){
                e.toString();
            }

        }
        return responses;
    }

    private boolean populateDB() {
        Date date = new Date();
        date = DateUtil.addSevenDays(date);
        //System.out.println(darkSkyMapper.getMostRecentDate());

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String formatedDate = sdf.format(date);
        //System.out.println(formatedDate);
        //ask DB for most recent forecast date
        int val = darkSkyMapper.getMostRecentDate().compareTo(formatedDate);
        //System.out.println(val);
        if( val == 0) {
            return false;
        }else return true;
    }

    protected static final String DAILY_FORECAST_URL = "https://api.darksky.net/forecast/8650ab2281f954862995ea2fabf" +
                                                        "b4502/{latitude},{longitude}";

    private volatile boolean cacheMiss = false;

    private RestTemplate quoteServiceTemplate = new RestTemplate();

//    public boolean isCacheMiss() {
//        boolean cacheMiss = this.cacheMiss;
//        this.cacheMiss = false;
//        return cacheMiss;
//    }
//
//    protected void setCacheMiss() {
//        this.cacheMiss = true;
//    }

//    @Cacheable("Forecast2")
//    public DailyForecast getForecast(double latitude, double longitude) {
//        setCacheMiss();
//        return requestForecast(DAILY_FORECAST_URL);
//    }

    //@CachePut(cacheNames = "Weather", key = "#lonitude" + "latitude")
    public DailyForecast requestTheForecast() {
       // setCacheMiss();
        return requestForecast(DAILY_FORECAST_URL);
    }

    protected DailyForecast requestForecast(String URL) {
        return requestForecast(URL, Collections.emptyMap());
    }

    protected DailyForecast requestForecast(String URL, Map<String, Object> urlVariables) {
        ForecastResponse quoteResponse = quoteServiceTemplate.getForObject(URL, ForecastResponse.class, urlVariables);
        return quoteResponse.getForecast();
    }


}


