package logic.DataEncapsulation;

public class DateInfo {

	public static boolean isVacation = false;
	public static boolean isWeekend = false;
	public static boolean isWeekDay = false;

	static void setIsWeekDay(boolean isWeekDay) {
		DateInfo.isWeekDay = isWeekDay;
	}

	static void setIsWeekend(boolean isWeekend) {
		DateInfo.isWeekend = isWeekend;
	}

	static void setIsVacation(boolean isVacation) {
		DateInfo.isVacation = isVacation;
	}

}
