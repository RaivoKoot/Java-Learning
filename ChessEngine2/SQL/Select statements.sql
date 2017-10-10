/* Selects all tuples from a specific difficulty that have at least one game played */
SELECT * FROM t_player_game_counts
WHERE games_started > 0 AND pk_difficulty = 5
ORDER BY wins DESC, games_started DESC

/* Selects the complete amount of games played and wins by all users on a specific difficulty*/
SELECT SUM(games_started), SUM(wins) FROM t_player_game_counts
WHERE pk_difficulty = 1

/* Selects the amount of games played and wins by all users on every difficulty */
SELECT SUM(games_started), SUM(wins) FROM t_player_game_counts