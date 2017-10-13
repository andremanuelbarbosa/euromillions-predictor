package com.andremanuelbarbosa.euromillions.predictor.helper;

import com.google.common.collect.Lists;
import com.google.common.math.BigIntegerMath;

import java.util.List;

public abstract class MathHelper {

    public static <T> List<List<T>> getCombinations(List<T> elements, int size) {

        if (size > elements.size()) {

            throw new IllegalArgumentException("The number of the Combinations cannot be greater than the number of Elements");
        }

        final List<List<T>> combinations = Lists.newArrayListWithExpectedSize(BigIntegerMath.factorial(elements.size()).divide(
            BigIntegerMath.factorial(elements.size() - size).multiply(BigIntegerMath.factorial(size))).intValue());

        if (size == 1) {

            combinations.add(elements);

        } else {

            for (int i = 0; i <= (elements.size() - size); i++) {

                final List<T> combination = Lists.newArrayList(elements.get(i));

                int index = elements.size() - 1;

                for (int j = (i + 1); j <= elements.size(); j++) {

                    if (combination.size() == size) {

                        combinations.add(Lists.newArrayList(combination));

                        combination.remove(combination.size() - 1);

                        if (j == elements.size() && combination.size() > (elements.size() - index) && i < (elements.size() - size)) {

                            j--;

                            for (int k = index; k < elements.size(); k++) {

                                combination.remove(combination.size() - 1);

                                j--;
                            }

                            index -= elements.size() - index;
                        }
                    }

                    if (j < elements.size()) {

                        combination.add(elements.get(j));
                    }
                }
            }
        }

        return combinations;
    }

    public static double getDoubleWithTwoDecimalPlaces(double rawDouble) {

        return Math.round(rawDouble * 100.0) / 100.0;
    }
}
