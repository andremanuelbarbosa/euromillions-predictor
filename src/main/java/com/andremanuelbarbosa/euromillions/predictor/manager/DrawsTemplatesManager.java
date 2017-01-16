package com.andremanuelbarbosa.euromillions.predictor.manager;

import com.andremanuelbarbosa.euromillions.predictor.dao.DrawsTemplatesDao;
import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.domain.Template;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.util.Combinations;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import static com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorProperties.*;

@Singleton
public class DrawsTemplatesManager {

    private final DrawsManager drawsManager;
    private final DrawsTemplatesDao drawsTemplatesDao;

    @Inject
    public DrawsTemplatesManager(DrawsManager drawsManager, DrawsTemplatesDao drawsTemplatesDao) {

        this.drawsManager = drawsManager;
        this.drawsTemplatesDao = drawsTemplatesDao;
    }

    public void deleteStarsDrawsTemplates(int drawId) {

        drawsTemplatesDao.deleteStarsDrawsTemplates(drawId);
    }

    public void deleteNumbersDrawsTemplates(int drawId) {

        drawsTemplatesDao.deleteNumbersDrawsTemplates(drawId);
    }

    public List<Integer> getDrawIdsWithoutTemplates() {

        return drawsTemplatesDao.getDrawIdsWithoutTemplates();
    }

    public List<Set<Integer>> getStarsDrawsTemplates(int drawId) {

        return drawsTemplatesDao.getStarsDrawsTemplates(drawId);
    }

    public List<Set<Integer>> getNumbersDrawsTemplates(int drawId) {

        return drawsTemplatesDao.getNumbersDrawsTemplates(drawId);
    }

    private void loadTemplates(List<Set<Integer>> templates, int numTemplates, int numElements, Combinations combinations, int[][] coOccurrenceMatrix, Set<Integer> elementsInTemplates, int[] combinationsElementsMap) {

        Template template = null;

        final Iterator<int[]> combinationsIterator = combinations.iterator();

        while (combinationsIterator.hasNext()) {

            final int[] combination = combinationsIterator.next();

            int elementsCoOccurrenceSum = 0;

            for (int i = 0; i < combination.length - 1; i++) {

                for (int j = i + 1; j < combination.length; j++) {

                    elementsCoOccurrenceSum += coOccurrenceMatrix[combinationsElementsMap[combination[i]] - 1][combinationsElementsMap[combination[j]] - 1];
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

        templates.add(template.getElements());
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

    private void saveStarTemplates(int drawId, int numTemplates, int numElements, Combinations combinations, int[][] coOccurrenceMatrix, Set<Integer> elementsInTemplates, int[] combinationsElementsMap) {

        final List<Set<Integer>> starTemplates = Lists.newArrayListWithExpectedSize(numTemplates);

        loadTemplates(starTemplates, numTemplates, numElements, combinations, coOccurrenceMatrix, elementsInTemplates, combinationsElementsMap);

        for (int i = 0; i < starTemplates.size(); i++) {

            final int template = i + 1;

            starTemplates.get(i).forEach(star -> {

                drawsTemplatesDao.insertStarDrawsTemplate(drawId, template, star);
            });
        }
    }

    private void saveNumberTemplates(int drawId, int numTemplates, int numElements, Combinations combinations, int[][] coOccurrenceMatrix, Set<Integer> elementsInTemplates, int[] combinationsElementsMap) {

        final List<Set<Integer>> numberTemplates = Lists.newArrayListWithExpectedSize(numTemplates);

        loadTemplates(numberTemplates, numTemplates, numElements, combinations, coOccurrenceMatrix, elementsInTemplates, combinationsElementsMap);

        for (int i = 0; i < numberTemplates.size(); i++) {

            final int template = i + 1;

            numberTemplates.get(i).forEach(number -> {

                drawsTemplatesDao.insertNumberDrawsTemplate(drawId, template, number);
            });
        }
    }

    public void updateDrawsTemplates(int drawId) {

        updateDrawsTemplates(drawId, true);
    }

    public void updateDrawsTemplates(int drawId, boolean newThread) {

        updateStarsDrawsTemplates(drawId, newThread);
        updateNumbersDrawsTemplates(drawId, newThread);
    }

    public void updateStarsDrawsTemplates(int drawId) {

        updateNumbersDrawsTemplates(drawId, true);
    }

    public void updateStarsDrawsTemplates(int drawId, boolean newThread) {

        deleteStarsDrawsTemplates(drawId);

        final List<Draw> draws = Lists.reverse(drawsManager.getDraws()).subList(0, drawId);

        final int numStars = draws.size() <= DRAWS_COUNT_BEFORE_ELEVEN_STARS ? 10 : (draws.size() <= DRAWS_COUNT_BEFORE_TWELVE_STARS ? 11 : STARS_COUNT);

        final int[][] starsCoOccurrenceMatrix = new int[numStars][numStars];

        draws.forEach(draw -> {

            final SortedSet<Integer> drawStars = draw.getStars();

            drawStars.forEach(star -> {

                drawStars.stream().filter(drawStar -> drawStar != star).forEach(drawStar -> {

                    starsCoOccurrenceMatrix[star - 1][drawStar - 1]++;
                });
            });
        });

        final int[] starCombinationsElementsMap = new int[numStars];

        for (int i = 0; i < starCombinationsElementsMap.length; i++) {

            starCombinationsElementsMap[i] = i + 1;
        }

        if (newThread) {

            new Thread(new StarTemplatesBuilderThread(drawId, STARS_COUNT_PER_DRAW, numStars, new Combinations(numStars, numStars / STARS_COUNT_PER_DRAW), starsCoOccurrenceMatrix, Sets.newHashSet(), starCombinationsElementsMap)).start();

        } else {

            saveStarTemplates(drawId, STARS_COUNT_PER_DRAW, numStars, new Combinations(numStars, numStars / STARS_COUNT_PER_DRAW), starsCoOccurrenceMatrix, Sets.newHashSet(), starCombinationsElementsMap);
        }
    }

    public void updateNumbersDrawsTemplates(int drawId) {

        updateNumbersDrawsTemplates(drawId, true);
    }

    public void updateNumbersDrawsTemplates(int drawId, boolean newThread) {

        deleteNumbersDrawsTemplates(drawId);

        final List<Draw> draws = Lists.reverse(drawsManager.getDraws()).subList(0, drawId);

        final int[][] numbersCoOccurrenceMatrix = new int[NUMBERS_COUNT][NUMBERS_COUNT];

        draws.forEach(draw -> {

            final SortedSet<Integer> drawNumbers = draw.getNumbers();

            drawNumbers.forEach(star -> {

                drawNumbers.stream().filter(drawNumber -> drawNumber != star).forEach(drawNumber -> {

                    numbersCoOccurrenceMatrix[star - 1][drawNumber - 1]++;
                });
            });
        });

        final int[] numberCombinationsElementsMap = new int[NUMBERS_COUNT];

        for (int i = 0; i < numberCombinationsElementsMap.length; i++) {

            numberCombinationsElementsMap[i] = i + 1;
        }

        if (newThread) {

            new Thread(new NumberTemplatesBuilderThread(drawId, NUMBERS_COUNT_PER_DRAW, NUMBERS_COUNT, new Combinations(NUMBERS_COUNT, NUMBERS_COUNT / NUMBERS_COUNT_PER_DRAW), numbersCoOccurrenceMatrix, Sets.newHashSet(), numberCombinationsElementsMap)).start();

        } else {

            saveNumberTemplates(drawId, NUMBERS_COUNT_PER_DRAW, NUMBERS_COUNT, new Combinations(NUMBERS_COUNT, NUMBERS_COUNT / NUMBERS_COUNT_PER_DRAW), numbersCoOccurrenceMatrix, Sets.newHashSet(), numberCombinationsElementsMap);
        }
    }

    abstract class TemplatesBuilderThread implements Runnable {

        final int drawId;
        final int numTemplates;
        final int numElements;
        final Combinations combinations;
        final int[][] coOccurrenceMatrix;
        final Set<Integer> elementsInTemplates;
        final int[] combinationsElementsMap;

        public TemplatesBuilderThread(int drawId, int numTemplates, int numElements, Combinations combinations, int[][] coOccurrenceMatrix, Set<Integer> elementsInTemplates, int[] combinationsElementsMap) {

            this.drawId = drawId;
            this.numTemplates = numTemplates;
            this.numElements = numElements;
            this.combinations = combinations;
            this.coOccurrenceMatrix = coOccurrenceMatrix;
            this.elementsInTemplates = elementsInTemplates;
            this.combinationsElementsMap = combinationsElementsMap;
        }
    }

    class StarTemplatesBuilderThread extends TemplatesBuilderThread {

        public StarTemplatesBuilderThread(int drawId, int numTemplates, int numElements, Combinations combinations, int[][] coOccurrenceMatrix, Set<Integer> elementsInTemplates, int[] combinationsElementsMap) {

            super(drawId, numTemplates, numElements, combinations, coOccurrenceMatrix, elementsInTemplates, combinationsElementsMap);
        }

        @Override
        public void run() {

            saveStarTemplates(drawId, numTemplates, numElements, combinations, coOccurrenceMatrix, elementsInTemplates, combinationsElementsMap);
        }
    }

    class NumberTemplatesBuilderThread extends TemplatesBuilderThread {

        public NumberTemplatesBuilderThread(int drawId, int numTemplates, int numElements, Combinations combinations, int[][] coOccurrenceMatrix, Set<Integer> elementsInTemplates, int[] combinationsElementsMap) {

            super(drawId, numTemplates, numElements, combinations, coOccurrenceMatrix, elementsInTemplates, combinationsElementsMap);
        }

        @Override
        public void run() {

            saveNumberTemplates(drawId, numTemplates, numElements, combinations, coOccurrenceMatrix, elementsInTemplates, combinationsElementsMap);
        }
    }
}
