package com.andremanuelbarbosa.euromillions.predictor.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Set;

public class Template implements Comparable {

    private final Set<Integer> elements;
    private final int elementsCoOccurrenceSum;

    public Template(Set<Integer> elements, int elementsCoOccurrenceSum) {

        this.elements = elements;
        this.elementsCoOccurrenceSum = elementsCoOccurrenceSum;
    }

    public Set<Integer> getElements() {

        return elements;
    }

    public int getElementsCoOccurrenceSum() {

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
