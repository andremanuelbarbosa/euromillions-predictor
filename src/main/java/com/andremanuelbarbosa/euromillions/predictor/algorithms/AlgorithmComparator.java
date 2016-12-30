package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class AlgorithmComparator implements Comparator<Class<? extends Algorithm>> {

    @Override
    public int compare(Class<? extends Algorithm> algorithmClass1, Class<? extends Algorithm> algorithmClass2) {

        return algorithmClass1.getSimpleName().compareTo(algorithmClass2.getSimpleName());
    }

    @Override
    public Comparator<Class<? extends Algorithm>> reversed() {

        return null;
    }

    @Override
    public Comparator<Class<? extends Algorithm>> thenComparing(Comparator<? super Class<? extends Algorithm>> other) {

        return null;
    }

    @Override
    public <U> Comparator<Class<? extends Algorithm>> thenComparing(Function<? super Class<? extends Algorithm>, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {

        return null;
    }

    @Override
    public <U extends Comparable<? super U>> Comparator<Class<? extends Algorithm>> thenComparing(Function<? super Class<? extends Algorithm>, ? extends U> keyExtractor) {

        return null;
    }

    @Override
    public Comparator<Class<? extends Algorithm>> thenComparingInt(ToIntFunction<? super Class<? extends Algorithm>> keyExtractor) {

        return null;
    }

    @Override
    public Comparator<Class<? extends Algorithm>> thenComparingLong(ToLongFunction<? super Class<? extends Algorithm>> keyExtractor) {

        return null;
    }

    @Override
    public Comparator<Class<? extends Algorithm>> thenComparingDouble(ToDoubleFunction<? super Class<? extends Algorithm>> keyExtractor) {

        return null;
    }
}
