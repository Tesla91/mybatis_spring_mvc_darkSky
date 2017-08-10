package mybatis.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by nicola on 7/27/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class HourlyAverage {


    double latitude;
    double longitude;
    String timezone;
    double averageTemperature;
    double averageApparentTemperature;
    double averageDewPoint;
    double averageHumidity;
    double averageWindSpeed;
    double averageWindBearing;
    double averageVisibility;
    double averageCloudCover;
    double averagePressure;
    double averageUvIndex;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public double getAverageTemperature() {
        return averageTemperature;
    }

    public void setAverageTemperature(double averageTemperature) {
        this.averageTemperature = averageTemperature;
    }

    public double getAverageApparentTemperature() {
        return averageApparentTemperature;
    }

    public void setAverageApparentTemperature(double averageApparentTemperature) {
        this.averageApparentTemperature = averageApparentTemperature;
    }

    public double getAverageDewPoint() {
        return averageDewPoint;
    }

    public void setAverageDewPoint(double averageDewPoint) {
        this.averageDewPoint = averageDewPoint;
    }

    public double getAverageHumidity() {
        return averageHumidity;
    }

    public void setAverageHumidity(double averageHumidity) {
        this.averageHumidity = averageHumidity;
    }

    public double getAverageWindSpeed() {
        return averageWindSpeed;
    }

    public void setAverageWindSpeed(double averageWindSpeed) {
        this.averageWindSpeed = averageWindSpeed;
    }

    public double getAverageWindBearing() {
        return averageWindBearing;
    }

    public void setAverageWindBearing(double averageWindBearing) {
        this.averageWindBearing = averageWindBearing;
    }

    public double getAverageVisibility() {
        return averageVisibility;
    }

    public void setAverageVisibility(double averageVisibility) {
        this.averageVisibility = averageVisibility;
    }

    public double getAverageCloudCover() {
        return averageCloudCover;
    }

    public void setAverageCloudCover(double averageCloudCover) {
        this.averageCloudCover = averageCloudCover;
    }

    public double getAveragePressure() {
        return averagePressure;
    }

    public void setAveragePressure(double averagePressure) {
        this.averagePressure = averagePressure;
    }

    public double getAverageUvIndex() {
        return averageUvIndex;
    }

    public void setAverageUvIndex(double averageUvIndex) {
        this.averageUvIndex = averageUvIndex;
    }
}
