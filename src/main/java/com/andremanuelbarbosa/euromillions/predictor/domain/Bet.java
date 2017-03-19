package com.andremanuelbarbosa.euromillions.predictor.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Bet extends Result {

    private final Long id;
    private final int drawId;
    private final String formulaName;

    public Bet(int drawId, String formulaName) {

        this(null, drawId, formulaName);
    }

    @JsonCreator
    public Bet(@JsonProperty("id") Long id, @JsonProperty("drawId") int drawId, @JsonProperty("formulaName") String formulaName) {

        this.id = id;
        this.drawId = drawId;
        this.formulaName = formulaName;
    }

    public Long getId() {

        return id;
    }

    public int getDrawId() {

        return drawId;
    }

    public String getFormulaName() {

        return formulaName;
    }

    public void addStar(Integer star) {

        if (stars.size() >= STARS_COUNT) {

            throw new IllegalStateException("Reached the limit of Stars in the Bet : " + stars.toString());
        }

        stars.add(star);
    }

    public void addNumber(Integer number) {

        if (numbers.size() >= NUMBERS_COUNT) {

            throw new IllegalStateException("Reached the limit of Numbers in the Bet : " + numbers.toString());
        }

        numbers.add(number);
    }

    public int getStarsPoints(Draw draw) {

        return Sets.intersection(stars, draw.getStars()).size();
    }

    public int getNumbersPoints(Draw draw) {

        return Sets.intersection(numbers, draw.getNumbers()).size();
    }

    public int getPoints(Draw draw) {

        return getStarsPoints(draw) + getNumbersPoints(draw);
    }

    public boolean isWinner(Draw draw) {

        final long matchingStars = stars.stream().filter(star -> draw.getStars().contains(star)).count();
        final long matchingNumbers = numbers.stream().filter(number -> draw.getNumbers().contains(number)).count();

        return matchingNumbers >= 2 || (matchingStars == 2 && (matchingNumbers >= 1 || draw.getStarsCount() > 10));
    }

    @Override
    public boolean equals(Object o) {

        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {

        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {

        if (stars.size() != STARS_COUNT || numbers.size() != NUMBERS_COUNT) {

            throw new IllegalStateException("Bet does not have the correct amount of Stars or Numbers : " + stars.toString()
                + " - " + numbers.toString());
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (Integer number : numbers) {

            stringBuilder.append(String.format("%2s", number).replace(' ', '0'));
            stringBuilder.append(" ");
        }

        int startIndex = 0;

        stringBuilder.append("*");

        for (Integer star : stars) {

            stringBuilder.append(String.format("%2s", star).replace(' ', '0'));

            if (++startIndex > 1) {

                stringBuilder.append("*");

            } else {

                stringBuilder.append(" ");
            }
        }

        return stringBuilder.toString();
    }
}
