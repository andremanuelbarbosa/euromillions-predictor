#!/bin/bash

DRAW_ID="1078"

export PGPASSWORD='postgres';

pg_dump -Fc -U postgres -t draws_stats_stars -t draws_stats_numbers -t draws_stats_intervals_stars -t draws_stats_intervals_numbers -f src/main/resources/dumps/DrawStatsUpToDraw"$DRAW_ID".dmp euromillions-predictor
pg_dump -Fc -U postgres -t draws_templates_stars -t draws_templates_numbers -f src/main/resources/dumps/DrawTemplatesUpToDraw"$DRAW_ID".dmp euromillions-predictor
pg_dump -Fc -U postgres -t formulas_stats -f src/main/resources/dumps/FormulaStatsUpToDraw"$DRAW_ID".dmp euromillions-predictor