package com.andremanuelbarbosa.euromillions.predictor.dao.jdbi;

import com.andremanuelbarbosa.euromillions.predictor.dao.DrawsTemplatesDao;
import com.andremanuelbarbosa.euromillions.predictor.dao.mapper.DrawTemplateSetMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;
import java.util.Set;

public interface DrawsTemplatesDaoJdbi extends DrawsTemplatesDao {

    @Override
    @SqlUpdate("DELETE FROM draws_templates_stars WHERE draw_id = :drawId")
    void deleteStarsDrawsTemplates(@Bind("drawId") int drawId);

    @Override
    @SqlUpdate("DELETE FROM draws_templates_numbers WHERE draw_id = :drawId")
    void deleteNumbersDrawsTemplates(@Bind("drawId") int drawId);

    @Override
    @SqlQuery("SELECT d.id FROM draws d WHERE NOT EXISTS ( SELECT 1 FROM draws_templates_stars dts WHERE dts.draw_id = d.id ) AND NOT EXISTS ( SELECT 1 FROM draws_templates_numbers dtn WHERE dtn.draw_id = d.id ) ORDER BY d.id ASC")
    List<Integer> getDrawIdsWithoutTemplates();

    @Override
    @RegisterMapper(DrawTemplateSetMapper.class)
    @SqlQuery("SELECT template, string_agg('' || star,',') AS items FROM draws_templates_stars WHERE draw_id = :drawId GROUP BY template ORDER BY template ASC")
    List<Set<Integer>> getStarsDrawsTemplates(@Bind("drawId") int drawId);

    @Override
    @RegisterMapper(DrawTemplateSetMapper.class)
    @SqlQuery("SELECT template, string_agg('' || number,',') AS items FROM draws_templates_numbers WHERE draw_id = :drawId GROUP BY template ORDER BY template ASC")
    List<Set<Integer>> getNumbersDrawsTemplates(@Bind("drawId") int drawId);

    @Override
    @SqlUpdate("INSERT INTO draws_templates_stars ( draw_id, template, star ) VALUES ( :drawId, :template, :star )")
    void insertStarDrawsTemplate(@Bind("drawId") int drawId, @Bind("template") int template, @Bind("star") int star);

    @Override
    @SqlUpdate("INSERT INTO draws_templates_numbers ( draw_id, template, number ) VALUES ( :drawId, :template, :number )")
    void insertNumberDrawsTemplate(@Bind("drawId") int drawId, @Bind("template") int template, @Bind("number") int number);
}
