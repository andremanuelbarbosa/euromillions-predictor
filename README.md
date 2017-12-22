# EuroMillions Predictor
The EuroMillions Predictor is a simple application that collects some statistics from the past EuroMillions results and tries to guess which numbers/combinations could be more successful on future draws.

## Setup
1. createdb euromillions-predictor -U postgres
1. ... EuroMillionsPredictor db migrate src/main/resources/conf.yaml
1. src/main/resources/dumps/restore.sh


## TODO
* bug in combinations (MathHelper) for 30C3
* check formulas with distinct template or no template return similar results
* review prizes after draw >= 991
* fix crons
* move web driver to resources



src/main/resources/dumps/dump.sh

src/main/resources/dumps/restore.sh


TensorFlow
* https://medium.com/google-cloud/how-to-do-time-series-prediction-using-rnns-and-tensorflow-and-cloud-ml-engine-2ad2eeb189e8
* http://mourafiq.com/2016/05/15/predicting-sequences-using-rnn-in-tensorflow.html


## Highest Wins and Earnings Factor in 5 Draws

| Formula                     | Wins | Winning | Earnings | Earning Factor | Points                                          |
| --------------------------- | ---- | ------- | -------- | -------------- | ----------------------------------------------- |
| S(ICDDIS-RI)N.N(ICDDIS-RI)D | 3    | 32.95   | 20.45    | 1.638          | (0+0) 0 < (1+0) 1 < (3+1) 4 < (2+0) 2 < (2+1) 3 |
| S(IDDIS-RI)N.N(IDDIS-RI)D   |	3    | 32.95   | 20.45    | 1.638          | (0+0) 0 < (1+0) 1 < (3+1) 4 < (2+0) 2 < (2+1) 3 |
| S(ITDDIS-RI)N.N(ITDDIS-RI)D |	3    | 32.95   | 20.45    | 1.638          | (0+0) 0 < (1+0) 1 < (3+1) 4 < (2+0) 2 < (2+1) 3 |
| S(ICLoIS)S.N(ITLoIS-RF)N    |	3    | 22.85   | 10.35    | 0.830          | (1+0) 1 < (2+1) 3 < (2+0) 2 < (1+0) 1 < (2+1) 3 |


SELECT formula_name AS name, COUNT(*) AS draws, SUM(costs) AS costs, SUM(CASE WHEN winnings > 0 THEN 1 ELSE 0 END) AS wins, SUM(winnings) AS winnings, SUM(earnings) AS earnings, AVG(earnings_factor) AS earnings_factor, string_agg(points, ' < ' ORDER BY draw_id DESC) AS points
  FROM formulas_stats
 WHERE draw_id >= 1065 AND draw_id <= 1069
 GROUP BY formula_name
HAVING COUNT(*) = 5 AND AVG(earnings_factor) > 0 AND SUM(CASE WHEN winnings > 0 THEN 1 ELSE 0 END) > 0
 ORDER BY SUM(CASE WHEN winnings > 0 THEN 1 ELSE 0 END) DESC, AVG(earnings_factor) DESC, formula_name ASC
 LIMIT 100;