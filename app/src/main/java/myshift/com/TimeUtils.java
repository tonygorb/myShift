package myshift.com;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by cenkgultekin on 10/10/16.
 */

public class TimeUtils {

    //region VARIABLES

    public static final String TAG = "TimeUtils";

    public static final long SECOND = 1000;
    public static final long MINUTE = 60 * SECOND;
    public static final long HOUR = 60 * MINUTE;
    public static final long DAY = 24 * HOUR;

    //endregion

    //region DATES

    public static String getFullDateString() {

        return getFullDateFormat().format(new Date());

    }

    public static String getDDMMYYHHSSTime(Date date) {
        return getDDMMYYHHMMssDateFormat().format(date);
    }

    public static String getDDMMYYhhMMTime(Date date) {
        return getDDMMYYHHMMDateFormat().format(date);
    }

    public static Date getCurrentDate() {

        Date date = getCal().getTime();

        return date;

    }

    public static Date getDateFromFullDateString(String dateString) {
        //dateString should be given in full

        Date date = getCurrentDate();

        try {
            date = getFullDateFormat().parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static Date getDateFromDDMMYYYYString(String dateString) {

        Date date = getCurrentDate();

        try {
            date = getDDMMYYYYFormat().parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;

    }

    public static Date getCustomHourSetOfDate(Date date, int hour) {

        Calendar cal = getCal();

        cal.setTime(date);

        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    public static String getElapsedTimeString(long milis) {

        int lapsedTimeInSecond = (int) (milis / 1000);

        String retTime;

        int secondTime = lapsedTimeInSecond % 60;
        int minuteTime = (lapsedTimeInSecond / 60) % 60;
        int hourTime = lapsedTimeInSecond / (3600);

        if (minuteTime > 0) {

            retTime = minuteTime + " dk.";

            if (hourTime > 0) {

                retTime = hourTime + " sa. ".concat(retTime);

            }
        } else {

            retTime = secondTime + " sn.";

        }

        return retTime;

    }

    //endregion

    //region STRING DATES

    public static String getHHMMString(String fullDateString) {
        return getHHMMFormat().format(getDateFromFullDateString(fullDateString));
    }

    public static String getHHMMString(Date date) {
        return getHHMMFormat().format(date);
    }

    public static String getHHMMSSString(String fullDateString) {
        return getHHMMSSFormat().format(getDateFromFullDateString(fullDateString));
    }

    public static String getHHMMSSString(Date date) {
        return getHHMMSSFormat().format(date);
    }

    public static String getfullDate(Date date) {

        return getFullDateFormat().format(date);

    }

    public static String getDDMMYYYY(Date date) {

        return getDDMMYYYYFormat().format(date);

    }

    public static String getMMYYYY(Date date) {

        return getMMYYYYFormat().format(date);

    }

    public static String getMonth(Date date) {

        return getMMFormat().format(date);
    }


    public static String getYear(Date date) {

        return getYYYYFormat().format(date);
    }

    public static String getDayName(Date date) {

        Calendar cal = getCal();
        cal.setTime(date);

        return cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, new Locale("tr"));
    }

    public static String getMonthYY(Date date) {

        return getMonthName(date) + " " + getYear(date);
    }

    public static String getMonthName(Date date) {
        Calendar cal = getCal();
        cal.setTime(date);

        return cal.getDisplayName(Calendar.MONTH, Calendar.LONG,
                new Locale("tr"));
    }

    public static String getDDMonth(Date date) {

        Calendar cal = getCal();
        cal.setTime(date);

        return getDayOfMonth(date) + " " + getMonthName(date);

    }

    public static String getDDMonthYY(Date date) {

        Calendar cal = getCal();
        cal.setTime(date);

        return getDayOfMonth(date) + " " + getMonthName(date) + " " + cal.get(Calendar.YEAR);

    }

    public static String getDdMonthYyHhMm(Date date) {
        return getDDMonthYY(date) + "-" + getHHMMString(date);
    }

    public static int getHour(Date date) {

        Calendar cal = getCal();
        cal.setTime(date);

        return cal.get(Calendar.HOUR);
    }

    //endregion

    //region FORMATS

    @SuppressLint("SimpleDateFormat")
    private static SimpleDateFormat getDDMMYYHHMMssDateFormat() {

        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    }

    @SuppressLint("SimpleDateFormat")
    private static SimpleDateFormat getDDMMYYHHMMDateFormat() {

        return new SimpleDateFormat("yyyy-MM-dd HH:mm");

    }

    @SuppressLint("SimpleDateFormat")
    private static SimpleDateFormat getFullDateFormat() {

        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    }

    private static SimpleDateFormat getDDMMYYYYFormat() {

        return new SimpleDateFormat("dd-MM-yyyy");

    }

    private static SimpleDateFormat getMMYYYYFormat() {

        return new SimpleDateFormat("MM-yyyy");

    }

    private static SimpleDateFormat getYYYYFormat() {

        return new SimpleDateFormat("yyyy");

    }

    private static SimpleDateFormat getMMFormat() {

        return new SimpleDateFormat("MM");

    }


    @SuppressLint("SimpleDateFormat")
    private static SimpleDateFormat getHHMMFormat() {

        return new SimpleDateFormat("HH:mm");

    }

    @SuppressLint("SimpleDateFormat")
    private static SimpleDateFormat getHHMMSSFormat() {

        return new SimpleDateFormat("HH:mm:ss");

    }

    //endregion

    //region COUNTS

    public static int getMonthIndex(Date date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        return cal.get(Calendar.MONTH);

    }

    private static int getDayOfWeek(Date date) {

        Calendar cal = getCal();
        cal.setTime(date);

        int order = cal.get(Calendar.DAY_OF_WEEK);

        return order > 1 ? order - 1 : 7;
    }

    public static int getTotalDayCountOfMonth(Date date) {

        Calendar cal = getCal();
        cal.setTime(date);

        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);

    }

    public static boolean isDayWeekend(Date date) {

        Calendar cal = getCal();
        cal.setTime(date);

        int dayIndex = getDayOfWeek(date);

        return dayIndex == 6 || dayIndex == 7;
    }

    public static int getDayOfMonth(Date date) {

        Calendar cal = getCal();
        cal.setTime(date);

        return cal.get(Calendar.DAY_OF_MONTH);
    }

    private static Calendar getCal() {

        return Calendar.getInstance();
    }

    public static Date getCustomDayOfMonth(Date date, int day) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        date = cal.getTime();

        return date;
    }

    public static Date getFirstDayOfMonth(Date date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        date = cal.getTime();

        return date;
    }

    public static Date getLastDayOfMonth(Date date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
        cal.set(Calendar.DAY_OF_MONTH, getTotalDayCountOfMonth(date));
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 99);

        date = cal.getTime();

        return date;
    }

    public static Date getMonth(int index) {

        Date date = getCurrentDate();

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MONTH, index);

        date = cal.getTime();

        return date;

    }

    public static Date getMonthOfYear(int index, Date date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MONTH, index);

        return cal.getTime();

    }

    //endregion

}
