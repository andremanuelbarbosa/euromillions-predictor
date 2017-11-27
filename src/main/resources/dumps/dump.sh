#!/bin/bash

export PGPASSWORD='postgres';
pg_dump -Fc -U postgres -t draws_stats_stars -t draws_stats_numbers -t draws_stats_intervals_stars -t draws_stats_intervals_numbers -f src/main/resources/dumps/DrawStatsUpToDraw1061.dmp euromillions-predictor
pg_dump -Fc -U postgres -t draws_templates_stars -t draws_templates_numbers -f src/main/resources/dumps/DrawTemplatesUpToDraw1061.dmp euromillions-predictor
pg_dump -Fc -U postgres -t formulas_stats -f src/main/resources/dumps/FormulaStatsUpToDraw1061.dmp euromillions-predictor