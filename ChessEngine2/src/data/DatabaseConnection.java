package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseConnection implements CanInsertDataIntoDB {

	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost/chess";
	private String user = "root";
	private String password = "";

	private Connection connection;
	private Statement statement;

	private static int difficulty;
	private static String playerName;

	private void createConnection() {
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setDifficulty(int difficulty) {
		DatabaseConnection.difficulty = difficulty;
	}

	public static void setPlayerName(String playerName) {
		DatabaseConnection.playerName = playerName;
	}

	public static String getPlayerName() {
		return playerName;
	}

	public static int getDifficulty() {
		return difficulty;
	}

	@Override
	public void enterNewPlayer(String playerName) {

		if (checkIfNameExists(playerName))
			return;

		try {
			createConnection();

			for (int difficulty = 1; difficulty <= 5; difficulty++) {
				String sqlCommand = SQLCommands.getInsertPlayerSQLCommand(playerName, difficulty);

				statement.executeUpdate(sqlCommand);
			}

			connection.close();
			statement.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private boolean checkIfNameExists(String playerName) {
		String sqlCommand = SQLCommands.getCheckIfNameExistsSQLCommand(playerName);
		boolean result = true;

		try {
			createConnection();

			ResultSet resultSet = statement.executeQuery(sqlCommand);

			resultSet.next();
			if (resultSet.getInt(1) == 0)
				result = false;

			connection.close();
			statement.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;

	}

	@Override
	public void incrementGamesPlayedAmount(int difficulty, String playerName) {
		try {
			createConnection();

			String sqlCommand = SQLCommands.getIncrementGamesStartedSQLCommand(playerName, difficulty);

			statement.executeUpdate(sqlCommand);

			connection.close();
			statement.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void incrementPlayerWins(int difficulty, String playerName) {
		try {
			createConnection();

			String sqlCommand = SQLCommands.getIncrementWinsSQLCommand(playerName, difficulty);

			statement.executeUpdate(sqlCommand);

			connection.close();
			statement.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void incrementGamesWithMoreThanTenTurnsAmount(int difficulty, String playerName) {
		try {
			createConnection();

			String sqlCommand = SQLCommands.getIncrementGamesTenPlusTurnsSQLCommand(playerName, difficulty);

			statement.executeUpdate(sqlCommand);

			connection.close();
			statement.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
