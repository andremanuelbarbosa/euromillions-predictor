package com.andremanuelbarbosa.euromillions.predictor.domain;

import com.andremanuelbarbosa.euromillions.predictor.algorithms.Algorithm;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.stream.Collectors;

import static com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorProperties.NUMBERS_COUNT_PER_DRAW;
import static com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorProperties.STARS_COUNT_PER_DRAW;

public class Formula {

    private final List<Algorithm> starsAlgorithms;
    private final List<Algorithm> numbersAlgorithms;

    private final TemplatesMode starsTemplatesMode;
    private final TemplatesMode numbersTemplatesMode;

    private final String name;

    public Formula(List<Algorithm> starsAlgorithms, List<Algorithm> numbersAlgorithms, TemplatesMode starsTemplatesMode, TemplatesMode numbersTemplatesMode) {

        this.starsAlgorithms = starsAlgorithms;
        this.numbersAlgorithms = numbersAlgorithms;

        this.starsTemplatesMode = starsTemplatesMode;
        this.numbersTemplatesMode = numbersTemplatesMode;

        name = "S(" + starsAlgorithms.stream().map(starsAlgorithm -> starsAlgorithm.getName()).collect(Collectors.joining("-")) + ")" + starsTemplatesMode.getName() + "." +
            "N(" + numbersAlgorithms.stream().map(numbersAlgorithm -> numbersAlgorithm.getName()).collect(Collectors.joining("-")) + ")" + numbersTemplatesMode.getName();
    }

    public String getName() {

        return name;
    }

    private double getItemWeightFromStars(List<Draw> draws, DrawStats drawStats) {

        double itemWeight = 1;

        for (int i = 0; i < starsAlgorithms.size(); i++) {

            itemWeight *= starsAlgorithms.get(i).getAdjustedItemWeight(draws, drawStats);
        }

        return itemWeight;
    }

    private double getItemWeightFromNumbers(List<Draw> draws, DrawStats drawStats) {

        double itemWeight = 1;

        for (int i = 0; i < numbersAlgorithms.size(); i++) {

            itemWeight *= numbersAlgorithms.get(i).getAdjustedItemWeight(draws, drawStats);
        }

        return itemWeight;
    }

    private double getMinimumWeightFromStars(List<Draw> draws, Draw draw, SortedSet<Integer> stars) {

        double starsMinimumWeight = Double.MAX_VALUE;

        for (Integer star : stars) {

            double starWeight = getItemWeightFromStars(draws, draw.getStarDrawStats().get(star - 1));

            if (starWeight < starsMinimumWeight) {

                starsMinimumWeight = starWeight;
            }
        }

        return starsMinimumWeight;
    }

    private double getMinimumWeightFromStarsSameTemplate(List<Draw> draws, Draw draw, SortedSet<Integer> stars, Set<Integer> starsTemplate) {

        double starsMinimumWeight = Double.MAX_VALUE;

        for (Integer star : stars) {

            double starWeight = getItemWeightFromStars(draws, draw.getStarDrawStats().get(star - 1));

            if (starWeight < starsMinimumWeight && starsTemplate.contains(star)) {

                starsMinimumWeight = starWeight;
            }
        }

        return starsMinimumWeight;
    }

    private double getMinimumWeightFromNumbers(List<Draw> draws, Draw draw, SortedSet<Integer> numbers) {

        double numbersMinimumWeight = Double.MAX_VALUE;

        for (Integer number : numbers) {

            double numberWeight = getItemWeightFromNumbers(draws, draw.getNumberDrawStats().get(number - 1));

            if (numberWeight < numbersMinimumWeight) {

                numbersMinimumWeight = numberWeight;
            }
        }

        return numbersMinimumWeight;
    }

    private double getMinimumWeightFromNumbersSameTemplate(List<Draw> draws, Draw draw, SortedSet<Integer> numbers, Set<Integer> numbersTemplate) {

        double numbersMinimumWeight = Double.MAX_VALUE;

        for (Integer number : numbers) {

            double numberWeight = getItemWeightFromNumbers(draws, draw.getNumberDrawStats().get(number - 1));

            if (numberWeight < numbersMinimumWeight && numbersTemplate.contains(number)) {

                numbersMinimumWeight = numberWeight;
            }
        }

        return numbersMinimumWeight;
    }

    double getMinimumWeight(List<Draw> draws, Draw draw, SortedSet<Integer> items, ItemType itemType) {

        double minimumWeight = itemType == ItemType.STAR ? getMinimumWeightFromStars(draws, draw, items) : getMinimumWeightFromNumbers(draws, draw, items);

        return minimumWeight < Double.MAX_VALUE ? minimumWeight : 0.0;
    }

    double getMinimumWeightSameTemplate(List<Draw> draws, Draw draw, SortedSet<Integer> items, ItemType itemType, Set<Integer> itemsTemplate) {

        double minimumWeight = itemType == ItemType.STAR ? getMinimumWeightFromStarsSameTemplate(draws, draw, items, itemsTemplate) :
            getMinimumWeightFromNumbersSameTemplate(draws, draw, items, itemsTemplate);

        return minimumWeight < Double.MAX_VALUE ? minimumWeight : 0.0;
    }

    Integer getMinimumWeightItem(List<Draw> draws, Draw draw, SortedSet<Integer> items, ItemType itemType) {

        double minimumWeight = getMinimumWeight(draws, draw, items, itemType);

        for (Integer item : items) {

            if (minimumWeight == 0.0) {

                return item;
            }

            if (itemType == ItemType.STAR) {

                if (getItemWeightFromStars(draws, draw.getStarDrawStats().get(item - 1)) == minimumWeight) {

                    return item;
                }
            }

            if (itemType == ItemType.NUMBER) {

                if (getItemWeightFromNumbers(draws, draw.getNumberDrawStats().get(item - 1)) == minimumWeight) {

                    return item;
                }
            }
        }

        throw new IllegalStateException("Unable to find the Minimum Weight Item in " + items.toString());
    }

    private Set<Integer> getStarTemplate(List<Set<Integer>> starsDrawsTemplates, int star) {

        return starsDrawsTemplates.stream().filter(starsDrawsTemplate -> starsDrawsTemplate.contains(star)).findFirst().get();
    }

    private Set<Integer> getNumberTemplate(List<Set<Integer>> numbersDrawsTemplates, int number) {

        return numbersDrawsTemplates.stream().filter(numbersDrawsTemplate -> numbersDrawsTemplate.contains(number)).findFirst().get();
    }

    public Bet getNextBet(List<Draw> draws) {

        final Bet bet = new Bet(draws.size() + 1, name);
        final Draw draw = Iterables.getLast(draws);

        Set<Integer> starsTemplate = null;
        Set<Integer> numbersTemplate = null;

        final Set<Set<Integer>> starsTemplates = Sets.newHashSetWithExpectedSize(STARS_COUNT_PER_DRAW);
        final Set<Set<Integer>> numbersTemplates = Sets.newHashSetWithExpectedSize(NUMBERS_COUNT_PER_DRAW);

        for (StarDrawStats starDrawStats : draw.getStarDrawStats()) {

            final Set<Integer> starTemplate = getStarTemplate(draw.getStarsDrawsTemplates(), starDrawStats.getStar());

            if (bet.getStars().size() < STARS_COUNT_PER_DRAW) {

                if (starsTemplatesMode == TemplatesMode.NONE
                    || (starsTemplatesMode == TemplatesMode.SAME && (starsTemplate == null || starsTemplate.contains(starDrawStats.getStar())))
                    || (starsTemplatesMode == TemplatesMode.DISTINCT && !starsTemplates.contains(starTemplate))) {

                    bet.addStar(starDrawStats.getStar());

                    if (starsTemplatesMode == TemplatesMode.SAME && starsTemplate == null) {

                        starsTemplate = starTemplate;

                    } else if (starsTemplatesMode == TemplatesMode.DISTINCT) {

                        starsTemplates.add(starTemplate);
                    }
                }

            } else {

                final double adjustedItemWeight = getItemWeightFromStars(draws, starDrawStats);

                if ((adjustedItemWeight > getMinimumWeight(draws, draw, bet.getStars(), ItemType.STAR)
                    && (starsTemplatesMode == TemplatesMode.NONE || (starsTemplatesMode == TemplatesMode.SAME && starsTemplate.contains(starDrawStats.getStar()))))
                    || (starsTemplatesMode == TemplatesMode.DISTINCT && adjustedItemWeight > getMinimumWeightSameTemplate(draws, draw, bet.getStars(), ItemType.STAR, starTemplate))) {

                    bet.getStars().remove(getMinimumWeightItem(draws, draw, bet.getStars(), ItemType.STAR));

                    bet.addStar(starDrawStats.getStar());
                }
            }
        }

        for (NumberDrawStats numberDrawStats : draw.getNumberDrawStats()) {

            final Set<Integer> numberTemplate = getNumberTemplate(draw.getNumbersDrawsTemplates(), numberDrawStats.getNumber());

            if (bet.getNumbers().size() < NUMBERS_COUNT_PER_DRAW) {

                if (numbersTemplatesMode == TemplatesMode.NONE
                    || (numbersTemplatesMode == TemplatesMode.SAME && (numbersTemplate == null || numbersTemplate.contains(numberDrawStats.getNumber())))
                    || (numbersTemplatesMode == TemplatesMode.DISTINCT && !numbersTemplates.contains(numberTemplate))) {

                    bet.addNumber(numberDrawStats.getNumber());

                    if (numbersTemplatesMode == TemplatesMode.SAME && numbersTemplate == null) {

                        numbersTemplate = numberTemplate;

                    } else if (numbersTemplatesMode == TemplatesMode.DISTINCT) {

                        numbersTemplates.add(numberTemplate);
                    }
                }

            } else {

                final double adjustedItemWeight = getItemWeightFromNumbers(draws, numberDrawStats);

                if ((adjustedItemWeight > getMinimumWeight(draws, draw, bet.getNumbers(), ItemType.NUMBER)
                    && (numbersTemplatesMode == TemplatesMode.NONE || (numbersTemplatesMode == TemplatesMode.SAME && numbersTemplate.contains(numberDrawStats.getNumber()))))
                    || (numbersTemplatesMode == TemplatesMode.DISTINCT && adjustedItemWeight > getMinimumWeightSameTemplate(draws, draw, bet.getStars(), ItemType.NUMBER, numberTemplate))) {

                    bet.getNumbers().remove(getMinimumWeightItem(draws, draw, bet.getNumbers(), ItemType.NUMBER));

                    bet.addNumber(numberDrawStats.getNumber());
                }
            }
        }

        return bet;
    }

    @Override
    public int hashCode() {

        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {

        return EqualsBuilder.reflectionEquals(this, obj);
    }

    public enum TemplatesMode {

        NONE("N"), SAME("S"), DISTINCT("D");

        private final String name;

        TemplatesMode(String name) {

            this.name = name;
        }

        public String getName() {

            return name;
        }
    }
}
