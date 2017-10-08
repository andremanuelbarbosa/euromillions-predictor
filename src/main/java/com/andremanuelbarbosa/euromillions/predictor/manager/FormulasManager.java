package com.andremanuelbarbosa.euromillions.predictor.manager;

import com.andremanuelbarbosa.euromillions.predictor.domain.Formula;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Singleton
public class FormulasManager {

    private final List<Formula> formulas = new LinkedList<>();

    @Inject
    public FormulasManager(AlgorithmsManager algorithmsManager) {

        final List<Formula.TemplatesMode> templatesModes = Lists.newArrayList(Formula.TemplatesMode.values());

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

        // One and Two Algorithms per Formula
        algorithmsManager.getAlgorithms().forEach(algorithm1 -> {

            algorithmsManager.getAlgorithms().forEach(algorithm2 -> {

                if (algorithm1.getClass() != algorithm2.getClass()) {

                    templatesModes.forEach(starsTemplatesMode -> {

                        templatesModes.forEach(numbersTemplateMode -> {

                            formulas.add(new Formula(Lists.newArrayList(algorithm1, algorithm2), Lists.newArrayList(algorithm1), starsTemplatesMode, numbersTemplateMode));
                            formulas.add(new Formula(Lists.newArrayList(algorithm1), Lists.newArrayList(algorithm1, algorithm2), starsTemplatesMode, numbersTemplateMode));
                            formulas.add(new Formula(Lists.newArrayList(algorithm1, algorithm2), Lists.newArrayList(algorithm1, algorithm2), starsTemplatesMode, numbersTemplateMode));
                        });
                    });
                }
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
