package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.domain.*;
import com.andremanuelbarbosa.euromillions.predictor.domain.Number;
import com.google.common.collect.Lists;

import java.util.SortedSet;

public abstract class Algorithm {

    private final Snapshot snapshot;

    public Algorithm(Snapshot snapshot) {

        this.snapshot = snapshot;
    }

    public Snapshot getSnapshot() {

        return snapshot;
    }

    abstract double getItemWeight(Item item);

    private boolean isUsingTemplates() {

        return Lists.newArrayList(getClass().getAnnotations()).stream().anyMatch(annotation -> annotation.getClass() == TemplatesAlgorithm.class);
    }

    private double getMinimumWeightFromStars(SortedSet<Integer> stars) {

        double starsMinimumWeight = Double.MAX_VALUE;

        for (Integer star : stars) {

            double starWeight = getItemWeight(getSnapshot().getStars().get(star - 1));

            if (starWeight < starsMinimumWeight) {

                starsMinimumWeight = starWeight;
            }
        }

        return starsMinimumWeight;
    }

    private double getMinimumWeightFromNumbers(SortedSet<Integer> numbers) {

        double numbersMinimumWeight = Double.MAX_VALUE;

        for (Integer number : numbers) {

            double numberWeight = getItemWeight(getSnapshot().getNumbers().get(number - 1));

            if (numberWeight < numbersMinimumWeight) {

                numbersMinimumWeight = numberWeight;
            }
        }

        return numbersMinimumWeight;
    }

    double getMinimumWeight(SortedSet<Integer> items, ItemType itemType) {

        double minimumWeight = itemType == ItemType.STAR ? getMinimumWeightFromStars(items) : getMinimumWeightFromNumbers(items);

        return minimumWeight < Double.MAX_VALUE ? minimumWeight : 0.0;
    }

    Integer getMinimumWeightItem(SortedSet<Integer> items, ItemType itemType) {

        double minimumWeight = getMinimumWeight(items, itemType);

        for (Integer item : items) {

            if (minimumWeight == 0.0) {

                return item;
            }

            if (itemType == ItemType.STAR) {

                if (getItemWeight(getSnapshot().getStars().get(item - 1)) == minimumWeight) {

                    return item;
                }
            }

            if (itemType == ItemType.NUMBER) {

                if (getItemWeight(getSnapshot().getNumbers().get(item - 1)) == minimumWeight) {

                    return item;
                }
            }
        }

        throw new IllegalStateException("Unable to find the Minimum Weight Item in " + items.toString());
    }

    public Bet getNextBet() {

        Bet bet = new Bet(this);

        Template starTemplate = null;
        final boolean usingTemplates = isUsingTemplates();

        for (Star star : snapshot.getStars()) {

            if (bet.getStars().size() < Result.STARS_COUNT) {

                if (!usingTemplates || starTemplate == null || starTemplate.getElements().contains(star.getId())) {

                    bet.addStar(star.getId());

                    if (usingTemplates && starTemplate == null) {

                        starTemplate = snapshot.getStarTemplate(star.getId());
                    }
                }

            } else if (getItemWeight(star) > getMinimumWeight(bet.getStars(), ItemType.STAR)) {

                bet.getStars().remove(getMinimumWeightItem(bet.getStars(), ItemType.STAR));

                bet.addStar(star.getId());
            }
        }

        for (Number number : snapshot.getNumbers()) {

            if (bet.getNumbers().size() < Result.NUMBERS_COUNT) {

                bet.addNumber(number.getId());

            } else if (getItemWeight(number) > getMinimumWeight(bet.getNumbers(), ItemType.NUMBER)) {

                bet.getNumbers().remove(getMinimumWeightItem(bet.getNumbers(), ItemType.NUMBER));

                bet.addNumber(number.getId());
            }
        }

        return bet;
    }
}
