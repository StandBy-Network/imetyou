package i.met.you;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class ApplicationConstants {

	public static Locale DEFAULT_LOCALE = new Locale("EN");
	public static boolean DEBUG_ENABLED = false;
	public static boolean CACHE_ENABLED = true;
	public static String PAGE_POSTFIX = ".html";
	public static String PAGE_LOCATION = "/WEB-INF/pages";
	public static String CONFIG_LOCATION = "/opt/app/imetyou/config";
	public static String HOME_PAGE = "/walkthrough";

	public static String DATE_FORMAT = "yyyy.MM.dd";
	public static String DATE_FORMAT_USA = "yyyy-MM-dd";
	public static String TIME_FORMAT = "yyyy.MM.dd k:mm:ss";
	public static String TIME_SHORT_FORMAT = "yyyy.MM.dd k:mm";
	public static String DCELL_PROCESSED_FILE = "yyyy_MM_dd_HHmmss";
	public static Integer ACTIVATION_TOKEN_VALID_IN_DAYS = 3;

	public static final Integer BATCH_SIZE = 1000;

	/** dátum formázások */
	public static SimpleDateFormat DATE_FORMATTER_FULL = new SimpleDateFormat("yyyy.MM.dd. HH:mm:ss:SSS");
	public static SimpleDateFormat DATE_FORMATTER_SECOND = new SimpleDateFormat("yyyy.MM.dd. HH:mm:ss");
	public static SimpleDateFormat DATE_FORMATTER_SECOND_EDGE = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	public static SimpleDateFormat DATE_FORMATTER_MINUTE = new SimpleDateFormat("yyyy.MM.dd. HH:mm");
	public static SimpleDateFormat DATE_FORMATTER_HOUR = new SimpleDateFormat("yyyy.MM.dd. HH");
	public static SimpleDateFormat DATE_FORMATTER_DAY = new SimpleDateFormat("yyyy.MM.dd");
	public static SimpleDateFormat DATE_FORMATTER_DAY_NUMBER = new SimpleDateFormat("yyyyMMdd");
	public static SimpleDateFormat DATE_FORMATTER_MONTH = new SimpleDateFormat("yyyy.MM");
	public static SimpleDateFormat DATE_FORMATTER_YEAR = new SimpleDateFormat("yyyy");
	public static SimpleDateFormat DATE_FORMATTER_DIRECTORY = new SimpleDateFormat("yyyy/MM/dd/");
	public static SimpleDateFormat DATE_FORMATTER_FILENAME = new SimpleDateFormat("yyyy_MM_dd");
	public static SimpleDateFormat DATE_FORMATTER_EXPORT_COMPARE = new SimpleDateFormat("dd.MM.yyyy");
	public static SimpleDateFormat DATE_FORMATTER_EXPORT_COMPARE_NAT = new SimpleDateFormat("dd/MM/yyyy");
	public static SimpleDateFormat DATE_FORMATTER_EXPORT_FILENAME = new SimpleDateFormat("yyMMdd");
	public static SimpleDateFormat DATE_FORMATTER_JUSTHOURMIN = new SimpleDateFormat("HH:mm");
	public static SimpleDateFormat DATE_FORMATTER_HOURMIN_NUMBER = new SimpleDateFormat("HHmm");
	public static SimpleDateFormat DATE_FORMATTER_SECOND_FILE_NAME = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
	public static SimpleDateFormat DATE_FORMATTER_EXPORT_PLASMA = new SimpleDateFormat("MM/dd/yyyy");

	public static SimpleDateFormat DATE_FORMATTER_SECOND_UND = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat DATE_FORMATTER_POSTGRE_DATE = new SimpleDateFormat("yyyy-MM-dd");

	public static final String formatDate(Date date, String pattern) {
		String value = "";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		value = format.format(date);

		return value;
	}

	public static final String formatDate(Date date, SimpleDateFormat format) {
		String value = "";

		value = format.format(date);

		return value;
	}

	/**
	 * a metódus visszaadja, hogy hány nap van egy hónapban
	 * 
	 * @param month
	 * @param year
	 * @return
	 */
	public static final int numberOfDaysInMonth(int month, int year) {
		// hozzá kell adni mert nullától kezdi
		Calendar monthStart = new GregorianCalendar(year, month - 1, 1);
		return monthStart.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * a metódus visszaadja, hogy melyik napon kezdődik a megadott hónap
	 * 
	 * @param month
	 * @param year
	 * @return
	 */
	public static final int firstDayOfMonth(int month, int year) {
		Calendar cal = new GregorianCalendar(year, month - 1, 1);
		int day = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (day == 0) {
			day = 7;
		}
		return day;
	}

	public static final Date getCalendarStartDate(int month, int year) {
		Date startDate = null;
		int firstDay = ApplicationConstants.firstDayOfMonth(month, year);
		firstDay = (firstDay * -1) + 1;
		Calendar cal = new GregorianCalendar(year, month - 1, 1);
		cal.add(Calendar.DATE, firstDay);
		startDate = cal.getTime();
		return startDate;
	}

	public static final Date getCalendarEndDate(Date startDate) {
		Date endDate = null;

		if (startDate != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(startDate);
			cal.add(Calendar.DATE, 41);
			endDate = cal.getTime();

		}

		return endDate;
	}

	public static final java.sql.Date convertJavaDateToSqlDate(java.util.Date date) {
		return new java.sql.Date(date.getTime());
	}

	public static String getBasePath() {
		String path = ApplicationConstants.class.getResource("").getPath();
		System.out.println(path);
		return path;
	}

	public static void main(String[] args) {
		System.out.println(ApplicationConstants.getBasePath());

	}

}
