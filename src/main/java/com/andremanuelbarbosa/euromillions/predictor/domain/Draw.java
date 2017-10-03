package com.andremanuelbarbosa.euromillions.predictor.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;

import java.util.*;

import static com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorProperties.*;

public class Draw implements Comparable {

    private final int id;
    private final Date date;
    private final double cost;
    private final int starsCount;
    private final int numbersCount;

    private final SortedSet<Integer> stars = new TreeSet<>();
    private final SortedSet<Integer> numbers = new TreeSet<>();

    private final List<DrawPrize> prizes = Lists.newArrayList();

    private final List<StarDrawStats> starDrawStats = Lists.newArrayList();
    private final List<NumberDrawStats> numberDrawStats = Lists.newArrayList();

    private final List<Set<Integer>> starsDrawsTemplates = Lists.newArrayListWithExpectedSize(STARS_COUNT_PER_DRAW);
    private final List<Set<Integer>> numbersDrawsTemplates = Lists.newArrayListWithExpectedSize(NUMBERS_COUNT_PER_DRAW);

    @JsonCreator
    public Draw(@JsonProperty("id") int id, @JsonProperty("date") Date date, @JsonProperty("cost") double cost, @JsonProperty("starsCount") int starsCount, @JsonProperty("numbersCount") int numbersCount) {

        this.id = id;
        this.date = date;
        this.cost = cost;
        this.starsCount = starsCount;
        this.numbersCount = numbersCount;
    }

    public int getId() {

        return id;
    }

    public Date getDate() {

        return date;
    }

    public double getCost() {

        return cost;
    }

    public int getStarsCount() {

        return starsCount;
    }

    public int getNumbersCount() {

        return numbersCount;
    }

    public SortedSet<Integer> getStars() {

        return stars;
    }

    public SortedSet<Integer> getNumbers() {

        return numbers;
    }

    public List<DrawPrize> getPrizes() {

        return prizes;
    }

    public List<StarDrawStats> getStarDrawStats() {

        return starDrawStats;
    }

    public List<NumberDrawStats> getNumberDrawStats() {

        return numberDrawStats;
    }

    public List<Set<Integer>> getStarsDrawsTemplates() {

        return starsDrawsTemplates;
    }

    public List<Set<Integer>> getNumbersDrawsTemplates() {

        return numbersDrawsTemplates;
    }

    public double getPrize(int stars, int numbers) {

        final Optional<DrawPrize> drawPrizeOptional = prizes.stream().filter(drawPrize -> drawPrize.getStars() == stars && drawPrize.getNumbers() == numbers).findFirst();

        if (drawPrizeOptional.isPresent()) {

            return drawPrizeOptional.get().getPrize();
        }

        return 0.0;
    }

    @Override
    public int compareTo(Object o) {

        return id - ((Draw) o).getId();
    }
}
