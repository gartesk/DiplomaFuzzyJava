package com.company.fuzzy.interfaces.implementations;

import com.company.fuzzy.hierarchy.Interval;
import com.company.fuzzy.hierarchy.TriangularNumber;
import com.company.fuzzy.interfaces.Converter;

import java.util.Random;

public class IntTriUniConverter implements Converter< Interval , TriangularNumber > {

    private static IntTriUniConverter instance;

    private IntTriUniConverter() {
    }

    public static IntTriUniConverter getInstance() {
        if (instance == null) {
            instance = new IntTriUniConverter();
        }
        return instance;
    }

    @Override
    public TriangularNumber straight( Interval old ) {
        Random r = new Random();
        double center = r.nextDouble() * old.getLength() + old.getLeftEnd();
        return new TriangularNumber( center , center - old.getLeftEnd() , old.getRightEnd() - center );
    }

    @Override
    public Interval inverse( TriangularNumber old ) {
        return new Interval( old.getCenter() - old.getLeftDeviation() , old.getCenter() + old.getRightDeviation() );
    }

}
