package data;

import javafx.collections.ObservableList;

public interface CanPullStatsFromDB {

	ObservableList<String> getStatList(int difficulty);
	
	int getAmountOfGamesPlayedByAI(int difficulty);
	
	int getAmountOfGamesLostByAI(int difficulty);
	
	
}
