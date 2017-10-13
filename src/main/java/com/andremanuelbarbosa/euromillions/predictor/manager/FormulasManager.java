package com.andremanuelbarbosa.euromillions.predictor.manager;

import com.andremanuelbarbosa.euromillions.predictor.algorithms.Algorithm;
import com.andremanuelbarbosa.euromillions.predictor.domain.Formula;
import com.andremanuelbarbosa.euromillions.predictor.helper.MathHelper;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.LinkedList;
import java.util.List;

@Singleton
public class FormulasManager {

    private final List<Formula> formulas = new LinkedList<>();
    private final List<Formula.TemplatesMode> templatesModes = Lists.newArrayList(Formula.TemplatesMode.values());

    private final AlgorithmsManager algorithmsManager;

    @Inject
    public FormulasManager(AlgorithmsManager algorithmsManager) {

        this.algorithmsManager = algorithmsManager;

        initializeOneAlgorithmFormulas();
        initializeOneAndTwoAlgorithmsFormulas();
//        initializeOneTwoAndThreeAlgorithmsFormulas();
    }

    private List<List<Algorithm>> getAlgorithmsCombinations(int algorithmsPerCombination) {

        return MathHelper.getCombinations(algorithmsManager.getAlgorithms(), algorithmsPerCombination);
    }

    private void initializeOneAlgorithmFormulas() {

        // One Algorithm per Formula
        algorithmsManager.getAlgorithms().forEach(starsAlgorithm -> {

            algorithmsManager.getAlgorithms().forEach(numbersAlgorithm -> {

                templatesModes.forEach(starsTemplatesMode -> {

                    templatesModes.forEach(numbersTemplateMode -> {

                        formulas.add(new Formula(Lists.newArrayList(starsAlgorithm), Lists.newArrayList(numbersAlgorithm), starsTemplatesMode, numbersTemplateMode));
                    });
                });
            });
        });
    }

    private void initializeOneAndTwoAlgorithmsFormulas() {

        // One and Two Algorithms per Formula
        getAlgorithmsCombinations(2).forEach(algorithmsCombination -> {

            final Algorithm algorithm1 = algorithmsCombination.get(0);
            final Algorithm algorithm2 = algorithmsCombination.get(1);

            algorithmsManager.getAlgorithms().forEach(algorithm -> {

                templatesModes.forEach(starsTemplatesMode -> {

                    templatesModes.forEach(numbersTemplateMode -> {

                        formulas.add(new Formula(
                            Lists.newArrayList(algorithm1, algorithm2),
                            Lists.newArrayList(algorithm),
                            starsTemplatesMode,
                            numbersTemplateMode));

                        formulas.add(new Formula(
                            Lists.newArrayList(algorithm),
                            Lists.newArrayList(algorithm1, algorithm2),
                            starsTemplatesMode,
                            numbersTemplateMode));
                    });
                });
            });

            templatesModes.forEach(starsTemplatesMode -> {

                templatesModes.forEach(numbersTemplateMode -> {

                    formulas.add(new Formula(
                        Lists.newArrayList(algorithm1, algorithm2),
                        Lists.newArrayList(algorithm1, algorithm2),
                        starsTemplatesMode,
                        numbersTemplateMode));
                });
            });
        });
    }

    private void initializeOneTwoAndThreeAlgorithmsFormulas() {

        // One, Two and Three Algorithms per Formula
        getAlgorithmsCombinations(3).forEach(algorithmsCombination3 -> {

            final Algorithm algorithm1Combination3 = algorithmsCombination3.get(0);
            final Algorithm algorithm2Combination3 = algorithmsCombination3.get(1);
            final Algorithm algorithm3Combination3 = algorithmsCombination3.get(2);

            getAlgorithmsCombinations(2).forEach(algorithmsCombination2 -> {

                final Algorithm algorithm1Combination2 = algorithmsCombination2.get(0);
                final Algorithm algorithm2Combination2 = algorithmsCombination2.get(1);

                templatesModes.forEach(starsTemplatesMode -> {

                    templatesModes.forEach(numbersTemplateMode -> {

                        formulas.add(new Formula(
                            Lists.newArrayList(algorithm1Combination3, algorithm2Combination3, algorithm3Combination3),
                            Lists.newArrayList(algorithm1Combination2, algorithm2Combination2),
                            starsTemplatesMode,
                            numbersTemplateMode));

                        formulas.add(new Formula(
                            Lists.newArrayList(algorithm1Combination2, algorithm2Combination2),
                            Lists.newArrayList(algorithm1Combination3, algorithm2Combination3, algorithm3Combination3),
                            starsTemplatesMode,
                            numbersTemplateMode));
                    });
                });
            });

            algorithmsManager.getAlgorithms().forEach(algorithm -> {

                templatesModes.forEach(starsTemplatesMode -> {

                    templatesModes.forEach(numbersTemplateMode -> {

                        formulas.add(new Formula(
                            Lists.newArrayList(algorithm1Combination3, algorithm2Combination3, algorithm3Combination3),
                            Lists.newArrayList(algorithm),
                            starsTemplatesMode,
                            numbersTemplateMode));

                        formulas.add(new Formula(
                            Lists.newArrayList(algorithm),
                            Lists.newArrayList(algorithm1Combination3, algorithm2Combination3, algorithm3Combination3),
                            starsTemplatesMode,
                            numbersTemplateMode));
                    });
                });
            });

            templatesModes.forEach(starsTemplatesMode -> {

                templatesModes.forEach(numbersTemplateMode -> {

                    formulas.add(new Formula(
                        Lists.newArrayList(algorithm1Combination3, algorithm2Combination3, algorithm3Combination3),
                        Lists.newArrayList(algorithm1Combination3, algorithm2Combination3, algorithm3Combination3),
                        starsTemplatesMode,
                        numbersTemplateMode));
                });
            });
        });
    }

    public List<Formula> getFormulas() {

        return formulas;
    }

    public Formula getFormula(String name) {

        return formulas.stream().filter(formula -> formula.getName().equals(name)).findFirst().get();
    }
}
