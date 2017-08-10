package mybatis.Util;

import org.springframework.context.annotation.Bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by nicola on 7/26/17.
 */
public class DateUtil {

    public static long changeDateFromString(String myDate) {


        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        //sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date date;
        long secs = 0;
        try {
            date = sdf.parse(myDate);
            secs = date.getTime()/1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return secs;
    }

    public static String changeDateFromLong(long seconds) {

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        //System.out.println(sdf);
        //sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        //System.out.println(sdf);
        Date date = new Date(seconds*1000);
        String dateFromSecs = sdf.format(date);

        return dateFromSecs;
    }

    public static String changeDateFromLongWithMin(long seconds) {

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:MM:SS z");
        //System.out.println(sdf);
        //sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        //System.out.println(sdf);
        Date date = new Date(seconds*1000);
        String dateFromSecs = sdf.format(date);

        return dateFromSecs;
    }

    public static Date addSevenDays(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 7);

        return c.getTime();
    }
}
