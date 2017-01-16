package com.andremanuelbarbosa.euromillions.predictor.domain;

import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;

public class Draw implements Comparable {

    private final int id;
    private final Date date;
    private final double prize;

    private final SortedSet<Integer> stars = new TreeSet<>();
    private final SortedSet<Integer> numbers = new TreeSet<>();

    public Draw(int id, Date date, double prize) {

        this.id = id;
        this.date = date;
        this.prize = prize;
    }

    public int getId() {

        return id;
    }

    public Date getDate() {

        return date;
    }

    public double getPrize() {

        return prize;
    }

    public SortedSet<Integer> getStars() {

        return stars;
    }

    public SortedSet<Integer> getNumbers() {

        return numbers;
    }

    @Override
    public int compareTo(Object o) {

        return id - ((Draw) o).getId();
    }
}
