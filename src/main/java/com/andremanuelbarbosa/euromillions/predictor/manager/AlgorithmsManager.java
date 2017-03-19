package com.andremanuelbarbosa.euromillions.predictor.manager;

import com.andremanuelbarbosa.euromillions.predictor.algorithms.Algorithm;
import com.andremanuelbarbosa.euromillions.predictor.algorithms.IgnoreAlgorithm;
import com.google.common.collect.Lists;
import com.google.inject.Singleton;
import org.reflections.Reflections;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class AlgorithmsManager {

    private final List<Algorithm> algorithms = new LinkedList<>();

    private final List<Class<? extends Algorithm>> algorithmClasses = new LinkedList<>();

    public AlgorithmsManager() {

        algorithmClasses.addAll((new Reflections(Algorithm.class.getPackage().getName())).getSubTypesOf(Algorithm.class).stream().filter(algorithmClass ->
            Lists.newArrayList(algorithmClass.getAnnotations()).stream().noneMatch(annotation -> annotation instanceof IgnoreAlgorithm)).collect(Collectors.toList()));

        algorithmClasses.forEach(algorithmClass -> {

            try {

                algorithms.add(algorithmClass.getConstructor(Boolean.class).newInstance(true));
                algorithms.add(algorithmClass.getConstructor(Boolean.class).newInstance(false));

            } catch (Exception e) {

                throw new IllegalStateException(e);
            }
        });

        Collections.sort(algorithms);
    }

    public Algorithm getAlgorithm(String name) {

        return algorithms.stream().filter(algorithm -> algorithm.getName().equals(name)).findFirst().get();
    }

    public List<Algorithm> getAlgorithms() {

        return algorithms;
    }

    public List<Class<? extends Algorithm>> getAlgorithmClasses() {

        return algorithmClasses;
    }
}
