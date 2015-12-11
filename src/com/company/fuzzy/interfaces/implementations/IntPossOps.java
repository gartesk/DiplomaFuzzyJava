package com.company.fuzzy.interfaces.implementations;

import com.company.fuzzy.hierarchy.Interval;
import com.company.fuzzy.interfaces.Builder;
import com.company.fuzzy.interfaces.Operations;

import java.util.Comparator;
import java.util.Random;

public class IntPossOps implements Operations<Interval>, Builder<Interval>, Comparator<Interval> {

    private static IntPossOps instance;

    private IntPossOps(){}

    public static IntPossOps getInstance() {
        if( instance == null ) {
            instance = new IntPossOps();
        }
        return instance;
    }

    @Override
    public Interval sum( Interval t1 , Interval t2 ) {
        return new Interval( t1.getLeftEnd() + t2.getLeftEnd() - t1.getLeftEnd() * t2.getLeftEnd() ,
                t1.getRightEnd() + t2.getRightEnd() - t1.getRightEnd() * t2.getRightEnd() );
    }
    @Override
    public Interval mult( Interval t1 , Interval t2 ) {
        return new Interval( t1.getLeftEnd() * t2.getLeftEnd(), t1.getRightEnd() * t2.getRightEnd() );
    }
    @Override
    public Interval sumRev( Interval t ) {
        return new Interval( 1. - t.getRightEnd(), 1. - t.getLeftEnd() );
    }
    @Override
    public Interval multRev( Interval t ) {
        return null;
    }
    @Override
    public Interval sub( Interval t1 , Interval t2 ) {
        return null;
    }
    @Override
    public Interval div( Interval t1 , Interval t2 ) {
        return null;
    }

    @Override
    public Interval newMin() {
        return new Interval( 0. , 0.);
    }
    @Override
    public Interval newMax() {
        return new Interval( 1. , 1.);
    }
    @Override
    public Interval newRandom() {
        Random rand = new Random();
        double firstRandom = rand.nextDouble();
        double secondRandom = rand.nextDouble();
        if (firstRandom > secondRandom) {
            return new Interval(secondRandom, firstRandom);
        } else {
            return new Interval(firstRandom, secondRandom);
        }
    }
    @Override
    public Interval build( Interval t ) {
        return new Interval( t.getLeftEnd(), t.getRightEnd() );
    }

    @Override
    public int compare( Interval t1 , Interval t2 ) {
        double diff =  (t1.getLeftEnd() + t1.getRightEnd()) / 2 - (t2.getLeftEnd() + t2.getRightEnd()) / 2;
        if( diff > 0 )
            return 1;
        if( diff < 0 )
            return -1;
        return 0;
    }

}
