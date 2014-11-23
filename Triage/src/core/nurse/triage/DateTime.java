package core.nurse.triage;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * A class with methods to return year, month, date, hour (12), hour(24), minute, second, timestamp, and epoch time.
 * 
 * @author Alvi Habib
 * 
 * @version 2014-11-08
 */
public class DateTime
{
    /**
     * Empty constructor for objects of class DateTime.
     */
    public DateTime()
    {
    }

    /**
     * Get current year.
     *
     * @param      none
     *
     * @return     the current year in yyyy 
     */
    public String getYear()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        Date dateObj = new Date();
        return dateFormat.format(dateObj); 
    }
    
    /**
     * Get current month.
     *
     * @param      none
     *
     * @return     the current month in MM 
     */
    public String getMonth()
    {
        DateFormat dateFormat = new SimpleDateFormat("MM");
        Date dateObj = new Date();
        return dateFormat.format(dateObj); 
    }
    
    /**
     * Get current day.
     *
     * @param      none
     *
     * @return     the current day in dd 
     */
    public String getDay()
    {
        DateFormat dateFormat = new SimpleDateFormat("dd");
        Date dateObj = new Date();
        return dateFormat.format(dateObj); 
    }
    
    /**
     * Get AM or PM.
     * 
     * @param      none
     *
     * @return     AM or PM 
     */
    public String getAMOrPM()
    {
        DateFormat dateFormat = new SimpleDateFormat("a");
        Date dateObj = new Date();
        return dateFormat.format(dateObj); 
    }
    
    /**
     * Get current hour in 12 hr format.
     * 
     * @param      none
     *
     * @return     the current hour (12) in hh 
     */
    public String getHour12()
    {
        DateFormat dateFormat = new SimpleDateFormat("hh");
        Date dateObj = new Date();
        return dateFormat.format(dateObj); 
    }
    
    /**
     * Get current hour in 24 hr format.
     * 
     * @param      none
     *
     * @return     the current hour (24) in kk 
     */
    public String getHour24()
    {
        DateFormat dateFormat = new SimpleDateFormat("kk");
        Date dateObj = new Date();
        return dateFormat.format(dateObj); 
    }
    
    /**
     * Get current minute.
     * 
     * @param      none
     *
     * @return     the current minute in mm 
     */
    public String getMinute()
    {
        DateFormat dateFormat = new SimpleDateFormat("mm");
        Date dateObj = new Date();
        return dateFormat.format(dateObj); 
    }
    
    /**
     * Get current second.
     * 
     * @param      none
     *
     * @return     the current minute in ss 
     */
    public String getSecond()
    {
        DateFormat dateFormat = new SimpleDateFormat("ss");
        Date dateObj = new Date();
        return dateFormat.format(dateObj); 
    }
    
    /**
     * Get current timestamp.
     * 
     * @param      none
     *
     * @return     the current timestamp in yyyy-MM-dd 
     */
    public String getTimeStamp()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateObj = new Date();
        return dateFormat.format(dateObj); 
    }
    
    /**
     * Get current seconds from Jan 1 1970.
     * 
     * @param      none
     *
     * @return     the current epoch seconds 
     */
    public long getEpoch()
    {
        return System.currentTimeMillis()/1000;
    }
}