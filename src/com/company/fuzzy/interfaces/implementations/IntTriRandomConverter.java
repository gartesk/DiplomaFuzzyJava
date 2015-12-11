package com.company.fuzzy.interfaces.implementations;

import com.company.fuzzy.hierarchy.Interval;
import com.company.fuzzy.hierarchy.TriangularNumber;
import com.company.fuzzy.interfaces.Converter;

import java.util.Random;

public class IntTriRandomConverter implements Converter< Interval , TriangularNumber > {

    private static IntTriRandomConverter instance;

    private IntTriRandomConverter() {
    }

    public static IntTriRandomConverter getInstance() {
        if (instance == null) {
            instance = new IntTriRandomConverter();
        }
        return instance;
    }

    @Override
    public TriangularNumber straight( Interval old ) {
        double delta = old.getLength() * (-(0.75 * Math.log(0.75) + 0.25 * Math.log(0.25))) * (1 + new Random().nextDouble() * 0.1);
        return new TriangularNumber( (old.getLeftEnd() + old.getRightEnd() ) / 2. , delta , delta );
    }

    @Override
    public Interval inverse( TriangularNumber old ) {
        double multiplier = -2 * (0.75 * Math.log(0.75) + 0.25 * Math.log(0.25)) * (1 + new Random().nextDouble() * 0.1);
        return new Interval( old.getCenter() - old.getLeftDeviation() * multiplier, old.getCenter() + old.getRightDeviation() * multiplier );
    }

}
