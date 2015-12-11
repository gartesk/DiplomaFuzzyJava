package com.company.fuzzy.interfaces.implementations;

import com.company.fuzzy.hierarchy.TrapezoidalNumber;
import com.company.fuzzy.hierarchy.TriangularNumber;
import com.company.fuzzy.interfaces.Converter;

import java.util.Random;

public class TriTrapRandomConverter implements Converter< TriangularNumber , TrapezoidalNumber > {

    private static TriTrapRandomConverter instance;

    private TriTrapRandomConverter(){}

    public static TriTrapRandomConverter getInstance() {
        if( instance == null ) {
            instance = new TriTrapRandomConverter();
        }
        return instance;
    }

    @Override
    public TrapezoidalNumber straight( TriangularNumber old ) {
        double dl = old.getLeftDeviation() * (1 + new Random().nextDouble() * 0.1);
        double dr = old.getRightDeviation() * (1 + new Random().nextDouble() * 0.1);
        double dlc = new Random().nextDouble() * 0.1 * old.getLeftDeviation();
        double drc = new Random().nextDouble() * 0.1 * old.getRightDeviation();
        return new TrapezoidalNumber( old.getCenter() - dlc , old.getCenter() + drc , dl , dr );
    }
    @Override
    public TriangularNumber inverse( TrapezoidalNumber old ) {
        return new TriangularNumber( ( old.getLeftTop() + old.getRightTop() ) / 2 ,
                old.getLeftDeviation() * (1 + new Random().nextDouble() * 0.1) ,
                old.getRightDeviation() * (1 + new Random().nextDouble() * 0.1));
    }

}
