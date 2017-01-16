package com.andremanuelbarbosa.euromillions.predictor.manager;

import com.andremanuelbarbosa.euromillions.predictor.dao.DrawsStatsDao;
import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.domain.DrawStats;
import com.andremanuelbarbosa.euromillions.predictor.domain.NumberDrawStats;
import com.andremanuelbarbosa.euromillions.predictor.domain.StarDrawStats;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorProperties.*;

@Singleton
public class DrawsStatsManager {

    private final DrawsManager drawsManager;
    private final DrawsStatsDao drawsStatsDao;

    @Inject
    public DrawsStatsManager(DrawsManager drawsManager, DrawsStatsDao drawsStatsDao) {

        this.drawsManager = drawsManager;
        this.drawsStatsDao = drawsStatsDao;
    }

    public void deleteDrawStats(int drawId) {

        deleteStarsDrawStats(drawId);

        deleteNumbesDrawStats(drawId);
    }

    public void deleteStarsDrawStats(int drawId) {

        drawsStatsDao.deleteStarsDrawStatsIntervals(drawId);

        drawsStatsDao.deleteStarsDrawStats(drawId);
    }

    public void deleteNumbesDrawStats(int drawId) {

        drawsStatsDao.deleteNumberStarsDrawStatsIntervals(drawId);

        drawsStatsDao.deleteNumbersDrawStats(drawId);
    }

    public List<Integer> getDrawIdsWithoutStats() {

        return drawsStatsDao.getDrawIdsWithoutStats();
    }

    public List<DrawStats> getDrawStats(int drawId) {

        return Stream.concat(getStarsDrawStats(drawId).stream(), getNumbersDrawStats(drawId).stream()).collect(Collectors.toList());
    }

    public List<StarDrawStats> getStarsDrawStats(int drawId) {

        final List<StarDrawStats> starsDrawStats = drawsStatsDao.getStarsDrawStats(drawId);

        starsDrawStats.forEach(starDrawStats -> {

            starDrawStats.getIntervals().addAll(drawsStatsDao.getStarDrawStatsIntervals(starDrawStats.getDrawId(), starDrawStats.getStar()));
        });

        return starsDrawStats;
    }

    public List<NumberDrawStats> getNumbersDrawStats(int drawId) {

        final List<NumberDrawStats> numbersDrawStats = drawsStatsDao.getNumbersDrawStats(drawId);

        numbersDrawStats.forEach(numberDrawStats -> {

            numberDrawStats.getIntervals().addAll(drawsStatsDao.getNumberDrawStatsIntervals(numberDrawStats.getDrawId(), numberDrawStats.getNumber()));
        });

        return numbersDrawStats;
    }

    private int getStarFreq(int star, List<Draw> draws) {

        return (int) draws.stream().filter(draw -> draw.getStars().contains(star)).count();
    }

    private int getNumberFreq(int number, List<Draw> draws) {

        return (int) draws.stream().filter(draw -> draw.getNumbers().contains(number)).count();
    }

    private int getStarInterval(int star, List<Draw> draws) {

        for (int i = 0; i < draws.size(); i++) {

            if (draws.get(i).getStars().contains(star)) {

                return i;
            }
        }

        return getStarNumDraws(star, draws) + (int) getStarsAverageInterval(draws);
    }

    private int getNumberInterval(int number, List<Draw> draws) {

        for (int i = 0; i < draws.size(); i++) {

            if (draws.get(i).getNumbers().contains(number)) {

                return i;
            }
        }

        return draws.size() + (int) getNumbersAverageInterval(draws);
    }

    private int getStarNumDraws(int star, List<Draw> draws) {

        return draws.size() - (star == 12 ? DRAWS_COUNT_BEFORE_TWELVE_STARS : (star == 11 ? DRAWS_COUNT_BEFORE_ELEVEN_STARS : 0));
    }

    private double getStarsAverageInterval(List<Draw> draws) {

        return (double) (draws.size() <= DRAWS_COUNT_BEFORE_ELEVEN_STARS ? 10 : (draws.size() <= DRAWS_COUNT_BEFORE_TWELVE_STARS ? 11 : 12)) / STARS_COUNT_PER_DRAW;
    }

    private double getNumbersAverageInterval(List<Draw> draws) {

        return NUMBERS_COUNT / NUMBERS_COUNT_PER_DRAW;
    }

    private List<Integer> getStarIntervals(int star, List<Draw> draws) {

        int interval = 0;

        final List<Integer> intervals = new LinkedList<>();

        for (int i = 0; i < draws.size(); i++) {

            if (draws.get(i).getStars().contains(star)) {

                intervals.add(interval);

                interval = 0;

            } else {

                interval++;
            }
        }

        if (interval > 0) {

            intervals.add(interval + (int) getStarsAverageInterval(draws));

        } else if (draws.size() > 1) {

            intervals.add(0);
        }

        return intervals;
    }

    private List<Integer> getNumberIntervals(int number, List<Draw> draws) {

        int interval = 0;

        final List<Integer> intervals = new LinkedList<>();

        for (int i = 0; i < draws.size(); i++) {

            if (draws.get(i).getNumbers().contains(number)) {

                intervals.add(interval);

                interval = 0;

            } else {

                interval++;
            }
        }

        if (interval > 0) {

            intervals.add(interval + (int) getNumbersAverageInterval(draws));

        } else if (draws.size() > 1) {

            intervals.add(0);
        }

        return intervals;
    }

    public List<DrawStats> updateDrawStats(int drawId) {

        return Stream.concat(updateStarsDrawStats(drawId).stream(), updateNumbersDrawStats(drawId).stream()).collect(Collectors.toList());
    }

    public List<StarDrawStats> updateStarsDrawStats(int drawId) {

        deleteStarsDrawStats(drawId);

        final List<Draw> draws = Lists.reverse(drawsManager.getDraws()).subList(0, drawId);

        final int numStars = draws.size() <= DRAWS_COUNT_BEFORE_ELEVEN_STARS ? 10 : (draws.size() <= DRAWS_COUNT_BEFORE_TWELVE_STARS ? 11 : 12);

        final List<StarDrawStats> starsDrawStats = Lists.newArrayListWithExpectedSize(numStars);

        for (int i = 1; i <= numStars; i++) {

            final int freq = getStarFreq(i, draws);

            final StarDrawStats starDrawStats = new StarDrawStats(drawId, freq, getStarInterval(i, draws), (double) freq / getStarNumDraws(i, draws), getStarsAverageInterval(draws), i);

            starsDrawStats.add(starDrawStats);

            drawsStatsDao.insertStarDrawStats(starDrawStats);

            final List<Integer> intervals = getStarIntervals(i, draws);

            starDrawStats.getIntervals().addAll(intervals);

            for (int j = 0; j < intervals.size(); j++) {

                drawsStatsDao.insertStarDrawStatsInterval(drawId, i, j, intervals.get(j));
            }
        }

        return starsDrawStats;
    }

    public List<NumberDrawStats> updateNumbersDrawStats(int drawId) {

        deleteNumbesDrawStats(drawId);

        final List<Draw> draws = Lists.reverse(drawsManager.getDraws()).subList(0, drawId);

        final List<NumberDrawStats> numbersDrawStats = Lists.newArrayListWithExpectedSize(NUMBERS_COUNT);

        for (int i = 1; i <= NUMBERS_COUNT; i++) {

            final int freq = getNumberFreq(i, draws);

            final NumberDrawStats numberDrawStats = new NumberDrawStats(drawId, freq, getNumberInterval(i, draws), (double) freq / draws.size(), getNumbersAverageInterval(draws), i);

            numbersDrawStats.add(numberDrawStats);

            drawsStatsDao.insertNumberDrawStats(numberDrawStats);

            final List<Integer> intervals = getNumberIntervals(i, draws);

            numberDrawStats.getIntervals().addAll(intervals);

            for (int j = 0; j < intervals.size(); j++) {

                drawsStatsDao.insertNumberDrawStatsInterval(drawId, i, j, intervals.get(j));
            }
        }

        return numbersDrawStats;
    }
}
