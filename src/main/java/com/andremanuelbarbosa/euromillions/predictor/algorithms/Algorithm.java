package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.domain.DrawStats;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

public abstract class Algorithm implements Comparable {

    protected final boolean reverse;

    private final String name;

    public Algorithm(String name, Boolean reverse) {

        this.name = name;
        this.reverse = reverse;
    }

    public String getName() {

        return (reverse ? "R" : "") + name;
    }

    public boolean isReverse() {

        return reverse;
    }

    public double getAdjustedItemWeight(List<Draw> draws, DrawStats drawStats) {

        if (reverse) {

            return Double.MAX_VALUE - getItemWeight(draws, drawStats);
        }

        return getItemWeight(draws, drawStats);
    }

    public abstract double getItemWeight(List<Draw> draws, DrawStats drawStats);

    @Override
    public boolean equals(Object o) {

        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {

        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public int compareTo(Object o) {

        final int classNameComparison = getClass().getSimpleName().compareTo(((Algorithm) o).getClass().getSimpleName());

        if (classNameComparison == 0) {

            return reverse ? 1 : -1;
        }

        return classNameComparison;
    }
}
