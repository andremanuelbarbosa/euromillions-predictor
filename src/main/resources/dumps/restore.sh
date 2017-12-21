#!/bin/bash

export PGPASSWORD='postgres';

unzip src/main/resources/dumps/DrawStatsUpToDraw*.zip
unzip src/main/resources/dumps/DrawTemplatesUpToDraw*.zip
unzip src/main/resources/dumps/FormulaStatsUpToDraw*.zip

pg_restore -U postgres -t draws_stats_stars -t draws_stats_numbers -d euromillions-predictor --data-only src/main/resources/dumps/DrawStatsUpToDraw*.dmp
pg_restore -U postgres -t draws_stats_intervals_stars -t draws_stats_intervals_numbers -d euromillions-predictor --data-only src/main/resources/dumps/DrawStatsUpToDraw*.dmp
pg_restore -U postgres -t draws_templates_stars -t draws_templates_numbers -d euromillions-predictor --data-only src/main/resources/dumps/DrawTemplatesUpToDraw*.dmp
pg_restore -U postgres -t formulas_stats -d euromillions-predictor --data-only src/main/resources/dumps/FormulaStatsUpToDraw*.dmp

rm -f src/main/resources/dumps/*.dmp