package mybatis.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("unused")
public class ForecastResponse {

    @JsonProperty("value")
    private DailyForecast forecast;

    public DailyForecast getForecast() {
        return forecast;
    }

    public void setForecast(final DailyForecast forecast) {
        this.forecast = forecast;
    }

    @Override
    public String toString() {
        return "ForecastResponse{" +
                "forecast=" + forecast +
                '}';
    }


    //        @Override
//    public String toString() {
//        return String.format("{ @type = %1$s, quote = '%2$s', status = %3$s }",
//                getClass().getName(), getQuote(), getStatus());
//    }

}
