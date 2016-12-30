package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.domain.Item;
import com.andremanuelbarbosa.euromillions.predictor.domain.Number;
import com.andremanuelbarbosa.euromillions.predictor.domain.Snapshot;
import com.andremanuelbarbosa.euromillions.predictor.domain.Star;

import java.util.HashMap;
import java.util.Map;

@IgnoreAlgorithm
public class IntervalSequenceLoessInterpolationRandomAlgorithm extends IntervalSequenceLoessInterpolationAlgorithm {

    private final Map<Item, Double> itemsWeight = new HashMap<>(Star.COUNT + Number.COUNT);

    public IntervalSequenceLoessInterpolationRandomAlgorithm(Snapshot snapshot) {

        super(snapshot);
    }

    @Override
    double getItemWeight(Item item) {

        if (!itemsWeight.containsKey(item)) {

            itemsWeight.put(item, RANDOM.nextDouble() * super.getItemWeight(item));
        }

        return itemsWeight.get(item);
    }
}
