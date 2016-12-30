package com.andremanuelbarbosa.euromillions.predictor.domain;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public abstract class RealDraws {

    private static final String DRAWS_CSV = "src/main/resources/draws.csv";

    private static List<RealDraw> realDraws;

    static {

        loadRealDraws();
    }

    public static List<RealDraw> getRealDraws() {

        return realDraws;
    }

    private static void loadRealDraws() {

        List<RealDraw> unorderedRealDraws = new LinkedList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(DRAWS_CSV))) {

            for (String line; (line = bufferedReader.readLine()) != null; ) {

                if (!StringUtils.isEmpty(line)) {

                    unorderedRealDraws.add(new RealDraw(line));
                }
            }

        } catch (Exception e) {

            throw new IllegalStateException(e);
        }

        realDraws = orderRealDraws(unorderedRealDraws);
    }

    private static List<RealDraw> orderRealDraws(List<RealDraw> unorderedRealDraws) {

        List<RealDraw> orderedRealDraws = new LinkedList<>();

        orderedRealDraws.add(unorderedRealDraws.get(0));

        for (int i = 1; i < unorderedRealDraws.size(); i++) {

            orderedRealDraws.add(unorderedRealDraws.get(i));

            int previousIndex = i - 1;

            while (previousIndex >= 0
                && unorderedRealDraws.get(i).getIndex() < orderedRealDraws.get(previousIndex).getIndex()) {

                orderedRealDraws.set(previousIndex + 1, orderedRealDraws.get(previousIndex));
                orderedRealDraws.set(previousIndex--, unorderedRealDraws.get(i));
            }
        }

        return orderedRealDraws;
    }
}
