package com.andremanuelbarbosa.euromillions.predictor.domain;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.util.Combinations;

import java.util.*;

public class TemplatesFinder {

    private static final Combinations STAR_COMBINATIONS = new Combinations(Star.COUNT, Star.COUNT / Star.COUNT_PER_DRAW);
    private static final Combinations NUMBER_COMBINATIONS = new Combinations(Number.COUNT, Number.COUNT / Number.COUNT_PER_DRAW);

    private static final Iterator<int[]> STAR_COMBINATIONS_ITERATOR = STAR_COMBINATIONS.iterator();
    private static final Iterator<int[]> NUMBER_COMBINATIONS_ITERATOR = NUMBER_COMBINATIONS.iterator();

    private final List<Template> starTemplates = Lists.newArrayListWithExpectedSize(Star.COUNT_PER_DRAW);
    private final List<Template> numberTemplates = Lists.newArrayListWithExpectedSize(Number.COUNT_PER_DRAW);

    public TemplatesFinder(List<? extends Draw> draws) {

        final int numStars = draws.size() <= Snapshot.DRAWS_COUNT_BEFORE_ELEVEN_STARS ? 10 : (draws.size() <= Snapshot.DRAWS_COUNT_BEFORE_TWELVE_STARS ? 11 : 12);

        final int[][] starsCoOccurrenceMatrix = new int[numStars][numStars];
        final int[][] numbersCoOccurrenceMatrix = new int[Number.COUNT][Number.COUNT];

        draws.forEach(draw -> {

            final SortedSet<Integer> drawStars = draw.getStars();

            drawStars.forEach(star -> {

                drawStars.stream().filter(drawStar -> drawStar != star).forEach(drawStar -> {

                    starsCoOccurrenceMatrix[star - 1][drawStar - 1]++;
                });
            });

            final SortedSet<Integer> drawNumbers = draw.getNumbers();

            drawNumbers.forEach(star -> {

                drawNumbers.stream().filter(drawNumber -> drawNumber != star).forEach(drawNumber -> {

                    numbersCoOccurrenceMatrix[star - 1][drawNumber - 1]++;
                });
            });
        });

        final int[] starCombinationsElementsMap = new int[numStars];
        final int[] numberCombinationsElementsMap = new int[Number.COUNT];

        for (int i = 0; i < starCombinationsElementsMap.length; i++) {

            starCombinationsElementsMap[i] = i + 1;
        }

        for (int i = 0; i < numberCombinationsElementsMap.length; i++) {

            numberCombinationsElementsMap[i] = i + 1;
        }

        loadTemplates(starTemplates, Star.COUNT_PER_DRAW, numStars, new Combinations(numStars, numStars / Star.COUNT_PER_DRAW), starsCoOccurrenceMatrix, Sets.newHashSet(), starCombinationsElementsMap);
//        loadTemplates(numberTemplates, Number.COUNT_PER_DRAW, Number.COUNT, NUMBER_COMBINATIONS, numbersCoOccurrenceMatrix, Sets.newHashSet(), numberCombinationsElementsMap);

//        starTemplates = loadTemplates(Star.COUNT_PER_DRAW, STAR_COMBINATIONS_ITERATOR, starsCoOccurrenceMatrix);
//        numberTemplates = loadTemplates(Number.COUNT_PER_DRAW, NUMBER_COMBINATIONS_ITERATOR, numbersCoOccurrenceMatrix);
//        starTemplates = loadTemplates(Star.COUNT_PER_DRAW, numStars, numStars / Star.COUNT_PER_DRAW, starsCoOccurrenceMatrix);
//        numberTemplates = loadTemplates(Number.COUNT_PER_DRAW, Number.COUNT, Number.COUNT / Number.COUNT_PER_DRAW, numbersCoOccurrenceMatrix);
    }

    private void loadTemplates(List<Template> templates, int numTemplates, int numElements, Combinations combinations, int[][] coOccurrenceMatrix, Set<Integer> elementsInTemplates, int[] combinationsElementsMap) {

        Template template = null;

        final Iterator<int[]> combinationsIterator = combinations.iterator();

        while (combinationsIterator.hasNext()) {

            final int[] combination = combinationsIterator.next();

            int elementsCoOccurrenceSum = 0;

            for (int i = 0; i < combination.length - 1; i++) {

                for (int j = i + 1; j < combination.length; j++) {

                    try {

                        elementsCoOccurrenceSum += coOccurrenceMatrix[combinationsElementsMap[combination[i]] - 1][combinationsElementsMap[combination[j]] - 1];
                    }catch (ArrayIndexOutOfBoundsException e) {

                        e.printStackTrace();
                    }
                }
            }

            if (template == null || elementsCoOccurrenceSum > template.getElementsCoOccurrenceSum()) {

                for (int i = 0; i < combination.length; i++) {

                    combination[i] = combinationsElementsMap[combination[i]];
                }

                final Set<Integer> elements = Sets.newHashSet(ArrayUtils.toObject(combination));

                if (Sets.intersection(elements, elementsInTemplates).size() == 0) {

                    template = new Template(elements, elementsCoOccurrenceSum);
                }
            }
        }

        if (template == null) {

            throw new IllegalStateException("No Template has been found.");
        }

        templates.add(template);
        elementsInTemplates.addAll(template.getElements());

        if (templates.size() < numTemplates) {

            int combinationsElementsMapIndex = 0;

            final int numElementsReduced = numElements - elementsInTemplates.size();
            final int[] combinationsElementsMapReduced = new int[numElementsReduced];

            for (int i = 1; i <= numElements; i++) {

                if (!elementsInTemplates.contains(i)) {

                    combinationsElementsMapReduced[combinationsElementsMapIndex++] = i;
                }
            }

            loadTemplates(templates, numTemplates, numElements, new Combinations(numElementsReduced, combinations.getK()), coOccurrenceMatrix, elementsInTemplates, combinationsElementsMapReduced);
        }
    }

    public List<Template> getStarTemplates() {

        return starTemplates;
    }

    public List<Template> getNumberTemplates() {

        return numberTemplates;
    }
}
