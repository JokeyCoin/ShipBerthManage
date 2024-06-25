import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DaysBetweenDates {
    public static void main(String[] args) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date1 = sdf.parse("2023-01-01 18:00:00");
            Date date2 = sdf.parse("2023-01-01 19:00:00");

            long diffInMillies = Math.abs(date2.getTime() - date1.getTime());
            long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS)+1;
            double fee=diffInDays*1111.5*0.02;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date=dateFormat.format(new Date());
            System.out.println("两个日期之间的天数为: " + diffInDays+","+date);
            System.out.println(dateFormat.format(new Date()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}