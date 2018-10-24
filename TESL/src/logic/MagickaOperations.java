package logic;

import data.MagickaData;

public class MagickaOperations {

	public static void increaseMaxMagicka(MagickaData data, int amount) {
		data.setMaxMagicka(data.getMaxMagicka() + amount);
	}
	
	public static void refreshCurrentMagicka(MagickaData data) {
		data.setCurrentMagicka(data.getMaxMagicka());
	}
	
	public static void resetMagicka(MagickaData data) {
		data.setMaxMagicka(0);
		data.setCurrentMagicka(0);
	}
}
