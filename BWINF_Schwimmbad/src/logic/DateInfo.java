package logic;

public class DateInfo {

	static boolean isVacation = false;
	static boolean isWeekend = false;
	static boolean isWeekDay = false;

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
