-- draws_stats_stars
SELECT (CASE WHEN star = 1 THEN CHR(13) ELSE '' END) || '        <insert tableName="draws_stats_stars"><column name="draw_id" value="' || draw_id ||'"></column><column name="star" value="' || star ||'"></column><column name="freq" value="' || freq ||'"></column><column name="interval" value="' || interval ||'"></column><column name="relative_freq" value="' || relative_freq ||'"></column><column name="average_interval" value="' || average_interval ||'"></column></insert>'
  FROM draws_stats_stars
 WHERE draw_id >= 801 AND draw_id <= 900
 ORDER BY draw_id ASC, star ASC;

-- draws_stats_numbers
SELECT (CASE WHEN number = 1 THEN CHR(13) ELSE '' END) || '        <insert tableName="draws_stats_numbers"><column name="draw_id" value="' || draw_id ||'"></column><column name="number" value="' || number ||'"></column><column name="freq" value="' || freq ||'"></column><column name="interval" value="' || interval ||'"></column><column name="relative_freq" value="' || relative_freq ||'"></column><column name="average_interval" value="' || average_interval ||'"></column></insert>'
  FROM draws_stats_numbers
 WHERE draw_id >= 1 AND draw_id <= 1
 ORDER BY draw_id ASC, number ASC;

-- draws_stats_intervals_stars
SELECT (CASE WHEN star = 1 AND id = 0 THEN CHR(13) ELSE '' END) || '        <insert tableName="draws_stats_intervals_stars"><column name="draw_id" value="' || draw_id ||'"></column><column name="star" value="' || star ||'"></column><column name="id" value="' || id ||'"></column><column name="interval" value="' || interval ||'"></column></insert>'
  FROM draws_stats_intervals_stars
 WHERE draw_id >= 1 AND draw_id <= 10
 ORDER BY draw_id ASC, star ASC, id ASC;

-- draws_stats_intervals_numbers
SELECT (CASE WHEN number = 1 AND id = 0 THEN CHR(13) ELSE '' END) || '        <insert tableName="draws_stats_intervals_numbers"><column name="draw_id" value="' || draw_id ||'"></column><column name="number" value="' || number ||'"></column><column name="id" value="' || id ||'"></column><column name="interval" value="' || interval ||'"></column></insert>'
  FROM draws_stats_intervals_numbers
 WHERE draw_id >= 1 AND draw_id <= 10
 ORDER BY draw_id ASC, number ASC, id ASC;

-- draws_templates_stars (draw_id >= 1 AND draw_id <= 378)
SELECT ( CASE WHEN (ROW_NUMBER() OVER() - 1) % 9 = 0 THEN CHR(13) ELSE '' END) || '        <insert tableName="draws_templates_stars"><column name="draw_id" value="' || draw_id ||'"></column><column name="template" value="' || template ||'"></column><column name="star" value="' || star ||'"></column></insert>'
  FROM draws_templates_stars
 WHERE draw_id >= 1 AND draw_id <= 378
 ORDER BY draw_id ASC, template ASC, star ASC;

-- draws_templates_stars (draw_id >= 379 AND draw_id <= 940)
SELECT ( CASE WHEN (ROW_NUMBER() OVER() - 1) % 11 = 0 THEN CHR(13) ELSE '' END) || '        <insert tableName="draws_templates_stars"><column name="draw_id" value="' || draw_id ||'"></column><column name="template" value="' || template ||'"></column><column name="star" value="' || star ||'"></column></insert>'
  FROM draws_templates_stars
 WHERE draw_id >= 379 AND draw_id <= 940
 ORDER BY draw_id ASC, template ASC, star ASC;

-- draws_templates_stars (draw_id >= 379 AND draw_id <= 940)
SELECT ( CASE WHEN (ROW_NUMBER() OVER() - 1) % 12 = 0 THEN CHR(13) ELSE '' END) || '        <insert tableName="draws_templates_stars"><column name="draw_id" value="' || draw_id ||'"></column><column name="template" value="' || template ||'"></column><column name="star" value="' || star ||'"></column></insert>'
  FROM draws_templates_stars
 WHERE draw_id >= 941
 ORDER BY draw_id ASC, template ASC, star ASC;

-- draws_templates_numbers
SELECT ( CASE WHEN (ROW_NUMBER() OVER() - 1) % 50 = 0 THEN CHR(13) ELSE '' END) || '        <insert tableName="draws_templates_numbers"><column name="draw_id" value="' || draw_id ||'"></column><column name="template" value="' || template ||'"></column><column name="number" value="' || number ||'"></column></insert>'
  FROM draws_templates_numbers
 WHERE draw_id >= 1 AND draw_id <= 100
 ORDER BY draw_id ASC, template ASC, number ASC;
