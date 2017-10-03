# euromillions-predictor
The EuroMillions Predictor is a simple application that collects some statistics from the past EuroMillions results and tries to guess which numbers/combinations could be more successful on future draws.

## TODO
* review prizes after draw >= 991
* fix migrations for formula stats
* db dump
* fix crons
* move web driver to resources



ONLY COMPLETED FOR <= 100:
draws_stats_stars
draws_stats_numbers
draws_stats_intervals_stars
draws_stats_intervals_numbers


pg_dump -Fc -U postgres -t draws_stats_stars -t draws_stats_numbers -t draws_stats_intervals_stars -t draws_stats_intervals_numbers -f DrawStatsUpToDraw1046.dmp euromillions-predictor 