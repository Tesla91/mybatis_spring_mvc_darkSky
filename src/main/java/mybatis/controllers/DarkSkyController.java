package mybatis.controllers;

import exceptions.APINotFoundException;
import exceptions.DBNotFoundException;
import mybatis.model.HourlyAverage;
import mybatis.model.Summary;
import mybatis.model.Weather;
import mybatis.model.DailyForecast;
import mybatis.services.DarkSkyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by nicola on 7/26/17.
 */
@RestController
@RequestMapping("/darksky")
public class DarkSkyController {

    @Autowired
    DarkSkyService darkSkyService;

    @RequestMapping("/weather")
    public Weather getWeather(@RequestParam(value = "lat")double latitude,
                                 @RequestParam(value = "long")double longitude) {
        return darkSkyService.getWeather(latitude, longitude);
    }

    @RequestMapping("/date")
    public Weather getWeatherWithDate(@RequestParam(value = "lat")double latitude,
                                        @RequestParam(value = "long")double longitude,
                                            @RequestParam(value = "date")String myDate) {
        return darkSkyService.getWeatherWithDate(latitude, longitude, myDate);
    }

    @RequestMapping("/sum")
    public ArrayList<Summary> getWeatherWithDateSummary(@RequestParam(value = "lat")double latitude,
                                                       @RequestParam(value = "long")double longitude,
                                                       @RequestParam(value = "date")String myDate) {
        return darkSkyService.getYearlySummary(latitude, longitude, myDate);
    }

    @RequestMapping("/hourly")
    public ArrayList<HourlyAverage> getWeatherHourlyAverage(@RequestParam(value = "lat")double latitude,
                                                            @RequestParam(value = "long")double longitude) {
        return darkSkyService.getWeatherHourlyAverage(latitude, longitude);
    }

    @RequestMapping(value = "/forecastOrig", method = RequestMethod.GET)
    public ArrayList<DailyForecast> getWeatherWeekForecast(@RequestParam(value = "lat")double latitude,
                                                           @RequestParam(value = "long")double longitude)
                                                            throws APINotFoundException, DBNotFoundException {

        return darkSkyService.getWeatherDailyForecast(latitude, longitude);
    }

}
