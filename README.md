# euromillions-predictor
The EuroMillions Predictor is a simple application that collects some statistics from the past EuroMillions results and tries to guess which numbers/combinations could be more successful on future draws.

## TODO
* review prizes after draw >= 991
* db dump
* fix crons
* move web driver to resources



export PGPASSWORD='postgres';
pg_dump -Fc -U postgres -t draws_stats_stars -t draws_stats_numbers -t draws_stats_intervals_stars -t draws_stats_intervals_numbers -f src/main/resources/dumps/DrawStatsUpToDraw1048.dmp euromillions-predictor
pg_dump -Fc -U postgres -t draws_templates_stars -t draws_templates_numbers -f src/main/resources/dumps/DrawTemplatesUpToDraw1048.dmp euromillions-predictor 
pg_dump -Fc -U postgres -t formulas_stats -f src/main/resources/dumps/FormulaStatsUpToDraw1048.dmp euromillions-predictor

pg_dump -Fc -U postgres -t formulas_stats -f src/main/resources/dumps/FormulaStatsUpToDraw1048OneTwoAndThreeAlgorithms.dmp euromillions-predictor


export PGPASSWORD='postgres';
pg_restore -U postgres -t draws_stats_stars -t draws_stats_numbers -d euromillions-predictor --data-only src/main/resources/dumps/DrawStatsUpToDraw1048.dmp
pg_restore -U postgres -t draws_stats_intervals_stars -t draws_stats_intervals_numbers -d euromillions-predictor --data-only src/main/resources/dumps/DrawStatsUpToDraw1048.dmp
pg_restore -U postgres -t draws_templates_stars -t draws_templates_numbers -d euromillions-predictor --data-only src/main/resources/dumps/DrawTemplatesUpToDraw1048.dmp
pg_restore -U postgres -t formulas_stats -d euromillions-predictor --data-only src/main/resources/dumps/FormulaStatsUpToDraw1048.dmp