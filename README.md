# euromillions-predictor
The EuroMillions Predictor is a simple application that collects some statistics from the past EuroMillions results and tries to guess which numbers/combinations could be more successful on future draws.

## TODO
* review prizes after draw >= 991
* db dump
* fix crons
* move web driver to resources



pg_dump -Fc -U postgres -t draws_stats_stars -t draws_stats_numbers -t draws_stats_intervals_stars -t draws_stats_intervals_numbers -f src/main/resources/dumps/DrawStatsUpToDraw1046.dmp euromillions-predictor
pg_dump -Fc -U postgres -t draws_templates_stars -t draws_templates_numbers -f src/main/resources/dumps/DrawTemplatesUpToDraw1046.dmp euromillions-predictor 
