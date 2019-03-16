package com.starter.medicalcommon.util;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2018-12-05 20:12
 **/
public class DateUtil {

    private static final DateTimeFormatter STD_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter STD_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter SIM_MONTH_FORMAT = DateTimeFormatter.ofPattern("yyyyMM");
    private static final DateTimeFormatter SIM_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter SIM_U_MONTH_DAY_FORMAT = DateTimeFormatter.ofPattern("MMdd");
    private static final DateTimeFormatter SIM_HOUR_MIN_FORMAT = DateTimeFormatter.ofPattern("HHmm");
    private static final DateTimeFormatter STD_HOUR_MIN_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

    /**
     * Date 转换为 LocalDateTime
     * @param date 时间
     * @return 本地时间
     */
    public static LocalDateTime convertDateToLDT(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), getDefaultZoneId());
    }

    /**
     * LocalDateTime 转换为 Date
     *
     * @param time 本地时间
     * @return Date 对象
     */
    public static Date convertLDTToDate(LocalDateTime time) {
        return Date.from(getDefaultZonedDateTime(time).toInstant());
    }

    /**
     * 将 Date 转为指定的格式
     *
     * @param date    日期
     * @param pattern 格式
     * @return 格式化日期
     */
    public static String formatDate(Date date, String pattern) {
        return formatTime(convertDateToLDT(date), pattern);
    }

    /**
     * 将时间转为固定格式
     *
     * @param time    时间
     * @param pattern 格式
     * @return 格式化时间
     */
    public static String formatTime(LocalDateTime time, String pattern) {
        return time.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 设置时区
     *
     * @param time 本地时间
     * @return 时区
     */
    public static ZonedDateTime getDefaultZonedDateTime(LocalDateTime time) {
        return time.atZone(getDefaultZoneId());
    }

    /**
     * 初始化时区
     * @return 时区
     */
    public static ZoneId getDefaultZoneId() {
        return TimeZone.getTimeZone("GMT+8:00").toZoneId();
    }

    /**
     * 获取Date格式的当前时间
     *
     * @return Date 获取Date格式的当前时间
     */
    public static Date getNowTimeForDate() {
        return Date.from(LocalDateTime.now().atZone(getChinaTimeZoneId()).toInstant());
    }

    /**
     * 获取Date格式的昨天时间
     *
     * @return Date 获取Date格式的当前时间
     */
    public static Date getYestdayForDate() {
        return Date.from(LocalDateTime.now().minusDays(1L).atZone(getChinaTimeZoneId()).toInstant());
    }

    /**
     * 获取Date格式的当天的凌晨时间
     *
     * @return Date 获取Date格式的当天的凌晨时间
     */
    public static Date getTodayStartTimeForDate() {
        LocalDate nowDate = LocalDate.now();
        return Date.from(LocalDateTime.of(nowDate.getYear(), nowDate.getMonth(), nowDate.getDayOfMonth(), 00, 00, 00).atZone(getChinaTimeZoneId()).toInstant());
    }

    /**
     * 获取指定日期的的凌晨时间 yyyy-MM-dd HH:mm:ss
     *
     * @param date      指定的时间
     * @param minusDays 递减的天数 0 为当天
     * @return Date 获取Date格式的指定日期的凌晨时间
     */
    public static Date getDayStartTimeByDate(Date date, Long minusDays) {
        LocalDateTime dateTime = date.toInstant().atZone(getChinaTimeZoneId()).toLocalDateTime().plusDays(minusDays);
        return Date.from(LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth(), 00, 00, 00).atZone(getChinaTimeZoneId()).toInstant());
    }

    /**
     * 判断当前时间是否为第二天的凌晨时间范围
     *
     * @return boolean 是/否
     */
    public static boolean isEndTimeOfDay() {
        return getFormatCurrentDateTime().contains("00:00:");
    }

    /**
     * 判断当前时间是否为第二天的凌晨时间范围，忽略分钟数
     *
     * @return boolean 是/否
     */
    public static boolean isEndTimeOfDayIgnoreMinutes() {
        return getFormatCurrentDateTime().contains(" 00:0");
    }

    /**
     * 按照指定的格式获取今天的日期和时间 yyyy-MM-dd HH:mm:ss
     *
     * @return 格式化的日期和时间
     */
    public static String getFormatCurrentDateTime() {
        return LocalDateTime.now().atZone(getChinaTimeZoneId()).format(STD_DATE_TIME_FORMAT);
    }

    /**
     * 按照指定的格式获取今天的日期 yyyy-MM-dd
     *
     * @return 格式化的日期
     */
    public static String getFormatCurrentDate() {
        return LocalDate.now().format(STD_DATE_FORMAT);
    }

    /**
     * 按照指定的格式获取昨天的日期 yyyy-MM-dd
     *
     * @return 格式化的日期
     */
    public static String getFormatYesterdayDate() {
        return LocalDate.now().minusDays(1L).format(STD_DATE_FORMAT);
    }

    public static String getFormatYesterdayDateTime() {
        return LocalDate.now().minusDays(1L).format(STD_DATE_TIME_FORMAT);
    }

    /**
     * 按照指定的格式获取与今天相差指定天数的日期 yyyy-MM-dd
     *
     * @param minusDays 递减的天数
     * @return 格式化的日期
     */
    public static String getFormatAnyDate(Long minusDays) {
        return LocalDate.now().minusDays(minusDays).format(STD_DATE_FORMAT);
    }

    /**
     * 按照指定的格式获取与指定日期相差指定天数的日期 yyyy-MM-dd
     *
     * @param date      指定的日期串
     * @param minusDays 递减的天数
     * @return 格式化的日期
     */
    public static String getFormatAnyDate(String date, Long minusDays) {
        return LocalDate.parse(date, SIM_DATE_FORMAT).minusDays(minusDays).format(STD_DATE_FORMAT);
    }

    /**
     * 按照指定的格式获取当前简单年月数据 yyyyMM
     *
     * @return 格式化的简单年月数据
     */
    public static String getFormateCurrentSimMonth() {
        return LocalDateTime.now().atZone(getChinaTimeZoneId()).format(SIM_MONTH_FORMAT);
    }

    /**
     * 按照指定的格式获取简单年月数据 yyyyMM
     *
     * @param date 当前的时间
     * @return 格式化的简单年月数据
     */
    public static String getFormateSimMonth(Date date) {
        return date.toInstant().atZone(getChinaTimeZoneId()).toLocalDateTime().format(SIM_MONTH_FORMAT);
    }

    /**
     * 按照指定的格式获取简单日期数据 yyyyMMdd
     *
     * @param date 当前的时间
     * @return 格式化的简单日期数据
     */
    public static String getFormateSimDate(Date date) {
        return date.toInstant().atZone(getChinaTimeZoneId()).toLocalDateTime().format(SIM_DATE_FORMAT);
    }

    /**
     * 按照指定的格式获取当前所属的十分钟时间 HHmm
     *
     * @param date 时间
     * @return 格式化的十分钟数据
     */
    public static String getTenMinute(Date date) {
        return date.toInstant().atZone(getChinaTimeZoneId()).toLocalDateTime().format(SIM_HOUR_MIN_FORMAT).substring(0, 3).concat("0");
    }

    /**
     * 按照指定的格式获取当前时间的前十分钟时间 HHmm
     *
     * @return 格式化的十分钟数据
     */
    public static String getNowTenMinuteBefore() {
        return LocalDateTime.now().atZone(getChinaTimeZoneId()).minusMinutes(10L).format(SIM_HOUR_MIN_FORMAT).substring(0, 3).concat("0");
    }

    /**
     * 按照指定的格式获取与当前时间相差指定分钟数的时间 HH:mm
     *
     * @param minusMinutes 递减的分钟数
     * @return 格式化的时间
     */
    public static String getFormatAnyTenMinute(Long minusMinutes) {
        return LocalDateTime.now().atZone(getChinaTimeZoneId()).minusMinutes(minusMinutes).format(STD_HOUR_MIN_FORMAT).substring(0, 4).concat("0");
    }

    /**
     * 按照指定的格式获取指定日期所属的月份和天数据
     *
     * @param date      时间
     * @param formatter 时间格式
     * @return 格式化的时间数据串
     */
    public static String getMonthAndDayString(Date date, DateTimeFormatter formatter) {
        return date.toInstant().atZone(getChinaTimeZoneId()).toLocalDateTime().format(formatter);
    }

    /**
     * 按照指定的格式获取指定日期所属的月份和天数据 MMdd
     *
     * @param date 时间
     * @return 格式化的月份和天数据
     */
    public static String getMonthAndDay(Date date) {
        return date.toInstant().atZone(getChinaTimeZoneId()).toLocalDateTime().format(SIM_U_MONTH_DAY_FORMAT);
    }

    /**
     * 按照指定的格式获取当前所属的月份和天数据 MMdd
     *
     * @return 格式化的月份和天数据
     */
    public static String getNowMonthAndDay() {
        return LocalDateTime.now().atZone(getChinaTimeZoneId()).format(SIM_U_MONTH_DAY_FORMAT);
    }

    /**
     * 按照指定的格式获取昨天所属的月份和天数据 MMdd
     *
     * @return 格式化的月份和天数据
     */
    public static String getYesMonthAndDay() {
        return LocalDateTime.now().atZone(getChinaTimeZoneId()).minusDays(1L).format(SIM_U_MONTH_DAY_FORMAT);
    }

    /**
     * 按照指定的格式获取前天所属的月份和天数据 MMdd
     *
     * @return 格式化的月份和天数据
     */
    public static String getBeforYesMonthAndDay() {
        return LocalDateTime.now().atZone(getChinaTimeZoneId()).minusDays(2L).format(SIM_U_MONTH_DAY_FORMAT);
    }

    /**
     * 按照固定格式获取两个日期之间的日期列表 MM/dd
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @param formatter 指定日期格式
     * @return List<String> 处理后获取的日期列表
     */
    public static List<String> getSimDateSignBetween(String startDate, String endDate, DateTimeFormatter formatter) {
        List<String> dateList = new ArrayList<>();
        LocalDate startLocalDate = LocalDate.parse(startDate, formatter);
        LocalDate endLocalDate = LocalDate.parse(endDate, formatter);
        do {
            dateList.add(startLocalDate.format(formatter));
            startLocalDate = startLocalDate.plusDays(1L);
        } while (startLocalDate.isBefore(endLocalDate) || startLocalDate.isEqual(endLocalDate));
        return dateList;
    }

    /**
     * 按照固定格式对传入的日期串进行转换 yyyy-MM-dd
     *
     * @param dateSrc 开始日期
     * @return String 处理后获取的日期串
     */
    public static String changeDateFormat(String dateSrc) {
        StringBuilder dateTemp = new StringBuilder();
        if (isFormatSame(dateSrc, SIM_DATE_FORMAT)) {
            dateTemp.append(dateSrc, 0, 4);
            dateTemp.append("-");
            dateTemp.append(dateSrc, 4, 6);
            dateTemp.append("-");
            dateTemp.append(dateSrc, 6, 8);
        } else if (isFormatSame(dateSrc, STD_DATE_FORMAT)) {
            dateTemp.append(dateSrc, 5, 7);
            dateTemp.append("/");
            dateTemp.append(dateSrc, 8, 10);
        }
        return dateTemp.toString();
    }

    /**
     * 按照固定格式对传入的日期串进行转换 yyyy-MM-dd
     *
     * @param dateSrc 开始日期
     * @return String 处理后获取的日期串
     */
    public static String changeDateFormat(String dateSrc, Integer minusDays) {
        StringBuilder dateTemp = new StringBuilder();
        if (isFormatSame(dateSrc, SIM_DATE_FORMAT)) {
            String date = LocalDate.parse(dateSrc, SIM_DATE_FORMAT).minusDays(minusDays).format(SIM_DATE_FORMAT);
            dateTemp.append(date, 0, 4);
            dateTemp.append("-");
            dateTemp.append(date, 4, 6);
            dateTemp.append("-");
            dateTemp.append(date, 6, 8);
        } else if (isFormatSame(dateSrc, STD_DATE_FORMAT)) {
            String date = LocalDate.parse(dateSrc, STD_DATE_FORMAT).minusDays(minusDays).format(STD_DATE_FORMAT);
            dateTemp.append(date, 5, 7);
            dateTemp.append("/");
            dateTemp.append(date, 8, 10);
        }

        return dateTemp.toString();
    }

    public static boolean isFormatSame(String dateSrc, DateTimeFormatter formatter) {
        String dateTemplate = LocalDateTime.now().format(formatter);
        if (dateSrc.length() == dateTemplate.length()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isOneDay(String startDate, String endDate) {
        if (startDate.substring(0, 10).equals(endDate.substring(0, 10))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * FUN 此方法描述的是：获取东八区时间
     */
    public static ZoneId getChinaTimeZoneId() {
        TimeZone tz = TimeZone.getTimeZone("GMT+8:00");
        return tz.toZoneId();
    }

    /**
     * 按照固定格式获取两个日期之间的日期列表 MM/dd
     *
     * @param formatter 指定日期格式
     * @return List<String> 处理后获取的日期列表
     */
    public static List<String> getTenMinutesList(DateTimeFormatter formatter) {
        return getTenMinutesList(formatter, false);
    }

    /**
     * 按照固定格式获取两个日期之间的日期列表 MM/dd
     *
     * @param formatter 指定日期格式
     * @param allZone   是否截至到现在
     * @return List<String> 处理后获取的日期列表
     */
    public static List<String> getTenMinutesList(DateTimeFormatter formatter, boolean allZone) {
        List<String> timeList = new ArrayList<>();
        LocalDateTime startLocalDateTime = getTodayStartTimeForDate().toInstant().atZone(getChinaTimeZoneId()).toLocalDateTime();
        LocalDateTime endLocalDateTime = allZone
                ? startLocalDateTime.plusDays(1L).minusMinutes(10L)
                : LocalDateTime.now().plusMinutes(-10L).atZone(getChinaTimeZoneId()).toLocalDateTime();
        do {
            timeList.add(startLocalDateTime.format(formatter));
            startLocalDateTime = startLocalDateTime.plusMinutes(10L);
        } while (startLocalDateTime.isBefore(endLocalDateTime) || startLocalDateTime.isEqual(endLocalDateTime));
        return timeList;
    }

    /**
     * 时间转integer
     *
     * @param dateTime yyyy-MM-dd hh:mm:ss
     * @return yyyyMMdd
     */
    public static Integer datetimeToInteger(String dateTime) {
        if (StringUtils.isEmpty(dateTime) || dateTime.trim().length() < "yyyy-MM-dd hh:mm:ss".length()) {
            return null;
        }
        return Integer.parseInt(dateTime.substring(0, 10).replaceAll("-", ""));
    }

    /**
     * 时间转integer
     *
     * @param date yyyy-MM-dd
     * @return yyyyMMdd
     */
    public static Integer dateToInteger(String date) {
        if (StringUtils.isEmpty(date) || date.trim().length() < "yyyy-MM-dd".length()) {
            return null;
        }
        return Integer.parseInt(date.replaceAll("-", ""));
    }

    /**
     * integer转时间
     *
     * @param dateSign yyyyMMdd
     * @return dateTime yyyy-MM-dd
     */
    public static String integerToDate(Integer dateSign) {
        if (dateSign == null) {
            return null;
        }
        String dateSignStr = String.valueOf(dateSign);
        return new StringBuilder(dateSignStr.substring(0, 4)).append("-")
                .append(dateSignStr.substring(4, 6)).append("-")
                .append(dateSignStr.substring(6, 8)).toString();
    }

    /**
     * 获取以前某天的dateSign
     *
     * @param dateSign  20181227
     * @param minusDays 1
     */
    public static Integer getAnyDateSign(Integer dateSign, long minusDays) {
        String targetDate = LocalDate.parse(String.valueOf(dateSign), SIM_DATE_FORMAT)
                .minusDays(minusDays)
                .format(SIM_DATE_FORMAT);
        return Integer.parseInt(targetDate);
    }

    public static Integer dateSignToDateMonth(Integer dateSign) {
        return Integer.parseInt(String.valueOf(dateSign).substring(0, 6));
    }

    public static String strToTenMinute(String str) {
        // 补0
        int bitCount = 4 - str.length();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < bitCount; i++) {
            builder.append("0");
        }
        // 转化
        return builder.append(str).insert(2, ":").toString();
    }

    public static Integer getCurrentDateSign() {
        return Integer.parseInt(LocalDate.now().format(SIM_DATE_FORMAT));
    }

    public static String dateToString(Date date) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    public static Date stringToDate(String datetime) {

        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = sdf.parse(datetime);
        } catch (Exception e) {
        }
        return date;
    }

}
