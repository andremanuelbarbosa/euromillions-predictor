package com.andremanuelbarbosa.euromillions.predictor.domain;

import com.google.common.collect.Sets;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class Template implements Comparable {

//    private final AtomicInteger elementsCoOccurrenceSum = new AtomicInteger(0);

    private final Set<Integer> elements;
    private final int elementsCoOccurrenceSum;

    public Template(Set<Integer> elements, int elementsCoOccurrenceSum) {

        this.elements = elements;
        this.elementsCoOccurrenceSum = elementsCoOccurrenceSum;
    }

    //    public Template(int[][] coOccurrenceMatrix, Integer... elements) {
//
//        this.elements = Sets.newHashSet(elements);
//
//        for (int i = 0; i < elements.length - 1; i++) {
//
//            for (int j = i + 1; j < elements.length; j++) {
//
//                elementsCoOccurrenceSum.addAndGet(coOccurrenceMatrix[elements[i] - 1][elements[j] - 1]);
//            }
//        }
//    }

    public Set<Integer> getElements() {

        return elements;
    }

    public int getElementsCoOccurrenceSum() {

//        return elementsCoOccurrenceSum.get();
        return elementsCoOccurrenceSum;
    }

    @Override
    public int compareTo(Object o) {

        return ((Template) o).getElementsCoOccurrenceSum() - getElementsCoOccurrenceSum();
    }

    @Override
    public String toString() {

        return ToStringBuilder.reflectionToString(this);
    }
}
