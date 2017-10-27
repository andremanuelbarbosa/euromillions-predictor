# euromillions-predictor
The EuroMillions Predictor is a simple application that collects some statistics from the past EuroMillions results and tries to guess which numbers/combinations could be more successful on future draws.

## TODO
* bug in combinations (MathHelper) for 30C3
* review prizes after draw >= 991
* db dump
* fix crons
* move web driver to resources



src/main/resources/dumps/dump.sh

src/main/resources/dumps/restore.sh



SELECT formula_name AS name, COUNT(*) AS draws, SUM(costs) AS costs, SUM(CASE WHEN winnings > 0 THEN 1 ELSE 0 END) AS wins, SUM(winnings) AS winnings, SUM(earnings) AS earnings, AVG(earnings_factor) AS earnings_factor, string_agg(points, ' | ') AS points
  FROM formulas_stats
 WHERE draw_id >= 1045 AND draw_id <= 1049
 GROUP BY formula_name
HAVING COUNT(*) = 5 AND AVG(earnings_factor) > 0 AND SUM(CASE WHEN winnings > 0 THEN 1 ELSE 0 END) > 1
 ORDER BY AVG(earnings_factor) DESC;