# euromillions-predictor
The EuroMillions Predictor is a simple application that collects some statistics from the past EuroMillions results and tries to guess which numbers/combinations could be more successful on future draws.

## TODO
* review prizes after draw >= 991
* db dump
* fix crons
* move web driver to resources



export PGPASSWORD='postgres';
pg_dump -Fc -U postgres -t draws_stats_stars -t draws_stats_numbers -t draws_stats_intervals_stars -t draws_stats_intervals_numbers -f src/main/resources/dumps/DrawStatsUpToDraw1047.dmp euromillions-predictor
pg_dump -Fc -U postgres -t draws_templates_stars -t draws_templates_numbers -f src/main/resources/dumps/DrawTemplatesUpToDraw1047.dmp euromillions-predictor 
pg_dump -Fc -U postgres -t formulas_stats -t formulas_stats_formulas -t formulas_stats_formulas_stars -t formulas_stats_formulas_numbers -f src/main/resources/dumps/FormulaStatsUpToDraw1047.dmp euromillions-predictor


export PGPASSWORD='postgres';
pg_restore -U postgres -t draws_stats_stars -t draws_stats_numbers -d euromillions-predictor --data-only src/main/resources/dumps/DrawStatsUpToDraw1046.dmp
pg_restore -U postgres -t draws_stats_intervals_stars -t draws_stats_intervals_numbers -d euromillions-predictor --data-only src/main/resources/dumps/DrawStatsUpToDraw1046.dmp
pg_restore -U postgres -t draws_templates_stars -t draws_templates_numbers -d euromillions-predictor --data-only src/main/resources/dumps/DrawTemplatesUpToDraw1046.dmp
pg_restore -U postgres -t formulas_stats -d euromillions-predictor --data-only src/main/resources/dumps/FormulaStatsUpToDraw1046.dmp
pg_restore -U postgres -t formulas_stats_formulas -d euromillions-predictor --data-only src/main/resources/dumps/FormulaStatsUpToDraw1046.dmp
pg_restore -U postgres -t formulas_stats_formulas_stars -t formulas_stats_formulas_numbers -d euromillions-predictor --data-only src/main/resources/dumps/FormulaStatsUpToDraw1046.dmp