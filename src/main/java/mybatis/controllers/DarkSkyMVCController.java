package mybatis.controllers;


import exceptions.APINotFoundException;
import exceptions.DBNotFoundException;
import mybatis.model.DailyForecast;
import mybatis.services.DarkSkyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@Controller
public class DarkSkyMVCController {

    @Autowired
    private DarkSkyService darkSkyService;

    @RequestMapping( value = "/forecast/form", method = RequestMethod.GET )
    public String latlongForm(Model model) {
        model.addAttribute("forecast", new DailyForecast());
        return "form";
    }

    @RequestMapping( value = "/forecast/weekly", method = RequestMethod.POST )
    public String forecastSubmit(Model model, DailyForecast forecast) {

        return "redirect:/forecast/weekly?latitude=" + forecast.getLatitude() + "&longitude=" + forecast.getLongitude();
    }

    @RequestMapping(value = "/forecast/weekly", method = RequestMethod.GET)
    public String dailyForecast(@RequestParam(value = "latitude")double latitude,
                                @RequestParam(value = "longitude")double longitude, Model model)
                                throws APINotFoundException, DBNotFoundException{
        ArrayList<DailyForecast> forecast = darkSkyService.getWeatherDailyForecast(latitude, longitude);
        System.out.println(forecast.size());
        model.addAttribute("forecastArr", forecast);
        System.out.println("returning");

        return "forecast";
    }

}
