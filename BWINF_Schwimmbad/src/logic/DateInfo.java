package logic;

public class DateInfo {

	private boolean isVacation;
	private boolean isWeekend;
	private boolean isWeekDay;

	public DateInfo() {
		setVacation(false);
		setWeekend(false);
		setWeekDay(false);
	}

	public boolean isWeekDay() {
		return isWeekDay;
	}

	public void setWeekDay(boolean isWeekDay) {
		this.isWeekDay = isWeekDay;
	}

	public boolean isWeekend() {
		return isWeekend;
	}

	public void setWeekend(boolean isWeekend) {
		this.isWeekend = isWeekend;
	}

	public boolean isVacation() {
		return isVacation;
	}

	public void setVacation(boolean isVacation) {
		this.isVacation = isVacation;
	}

}
