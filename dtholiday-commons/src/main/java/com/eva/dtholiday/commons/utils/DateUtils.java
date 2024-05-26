package com.eva.dtholiday.commons.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/4/26 1:41
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
public class DateUtils {
    public static LocalDateTime convertDateToLocalDateTime(Date date) {
        if (Objects.isNull(date)) {
            date = new Date();
        }
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();

        return instant.atZone(zoneId).toLocalDateTime();
    }

    public static List<Date> getDateList(Date startDate, Date endDate) {
        List<Date> dateList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        dateList.add(startDate);
        while(calendar.getTime().before(endDate)){
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            dateList.add(calendar.getTime());
        }
        return dateList;
    }

    public static Integer getDateNum(Date startDate, Date endDate) {
        // date相减得到天数
        long daysBetween = ChronoUnit.DAYS.between(convertDateToLocalDateTime(startDate), convertDateToLocalDateTime(endDate));
        return (int) daysBetween;
    }

    public static void main(String[] args) {
        Date startDate = new Date();
        Date endDate = new Date();
        String date1 = "2024-05-01";
        String date2 = "2024-05-05";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            startDate = dateFormat.parse(date1);
            endDate = dateFormat.parse(date2);
        }catch (ParseException e){
            e.printStackTrace();
        }
        System.out.println(getDateNum(startDate, endDate));
    }
}
