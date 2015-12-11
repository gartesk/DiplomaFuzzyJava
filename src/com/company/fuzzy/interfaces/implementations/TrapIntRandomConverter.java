package com.company.fuzzy.interfaces.implementations;

import com.company.fuzzy.hierarchy.Interval;
import com.company.fuzzy.hierarchy.TrapezoidalNumber;
import com.company.fuzzy.interfaces.Converter;

import java.util.Random;

public class TrapIntRandomConverter implements Converter< TrapezoidalNumber , Interval > {

    private static TrapIntRandomConverter instance;

    private TrapIntRandomConverter() {
    }

    public static TrapIntRandomConverter getInstance() {
        if (instance == null) {
            instance = new TrapIntRandomConverter();
        }
        return instance;
    }

    @Override
    public Interval straight( TrapezoidalNumber old ) {
        return new Interval( old.getLeftTop() * new Random().nextDouble()
                - old.getLeftDeviation() * 0.5 / (-(0.75 * Math.log(0.75) + 0.25 * Math.log(0.25))) ,
                old.getRightTop() * new Random().nextDouble()
                        + old.getRightDeviation() * 0.5 / (-(0.75 * Math.log(0.75) + 0.25 * Math.log(0.25))) );
    }

    @Override
    public TrapezoidalNumber inverse( Interval old ) {
        double dc = new Random().nextDouble() * 0.1 * old.getLength();
        double center = (old.getLeftEnd() + old.getRightEnd()) / 2;
        return new TrapezoidalNumber( center - dc / 2 , center + dc / 2 ,
                -(0.75 * Math.log(0.75) + 0.25 * Math.log(0.25)) * old.getLength() ,
                -(0.75 * Math.log(0.75) + 0.25 * Math.log(0.25)) * old.getLength() );
    }

}
