package com.company.fuzzy.interfaces.implementations;

import com.company.fuzzy.hierarchy.TrapezoidalNumber;
import com.company.fuzzy.interfaces.Builder;
import com.company.fuzzy.interfaces.Operations;

import java.util.Comparator;
import java.util.Random;

public class TrapAveragePossOps implements Operations<TrapezoidalNumber>, Builder< TrapezoidalNumber >, Comparator< TrapezoidalNumber > {

    private static TrapAveragePossOps instance;

    private TrapAveragePossOps(){}

    public static TrapAveragePossOps getInstance() {
        if( instance == null ) {
            instance = new TrapAveragePossOps();
        }
        return instance;
    }

    @Override
    public TrapezoidalNumber sum( TrapezoidalNumber t1 , TrapezoidalNumber t2 ) {
        return new TrapezoidalNumber( t1.getLeftTop() + t2.getLeftTop() - t1.getLeftTop() * t2.getLeftTop()
                , t1.getRightTop() + t2.getRightTop() - t1.getRightTop() * t2.getRightTop()
                , 0.5 * ( t1.getLeftDeviation() + t2.getLeftDeviation() )
                , 0.5 * ( t1.getRightDeviation() + t2.getRightDeviation() ) );
    }
    @Override
    public TrapezoidalNumber mult( TrapezoidalNumber t1 , TrapezoidalNumber t2 ) {
        return new TrapezoidalNumber( t1.getLeftTop() * t2.getLeftTop()
                , t1.getRightTop() * t2.getRightTop()
                , 0.5 * ( t1.getLeftDeviation() + t2.getLeftDeviation() )
                , 0.5 * ( t1.getRightDeviation() + t2.getRightDeviation() ) );
    }
    @Override
    public TrapezoidalNumber sumRev( TrapezoidalNumber t ) {
        return new TrapezoidalNumber( 1. - t.getRightTop()
                , 1. - t.getLeftTop()
                , t.getLeftDeviation()
                , t.getRightDeviation() );
    }
    @Override
    public TrapezoidalNumber multRev( TrapezoidalNumber t ) {
        return null;
    }
    @Override
    public TrapezoidalNumber sub( TrapezoidalNumber t1 , TrapezoidalNumber t2 ) {
        return null;
    }
    @Override
    public TrapezoidalNumber div( TrapezoidalNumber t1 , TrapezoidalNumber t2 ) {
        return null;
    }

    @Override
    public TrapezoidalNumber newMin() {
        return new TrapezoidalNumber( 0., 0. , 0. , 0. );
    }
    @Override
    public TrapezoidalNumber newMax() {
        return new TrapezoidalNumber( 1., 1. , 0. , 0. );
    }
    @Override
    public TrapezoidalNumber newRandom() {
        Random rand = new Random();
        double leftTop = rand.nextDouble();
        double rightTop = rand.nextDouble();
        if (leftTop > rightTop) {
            double temp = leftTop;
            leftTop = rightTop;
            rightTop = temp;
        }
        double leftDeviation = rand.nextDouble() * leftTop;
        double rightDeviation = rand.nextDouble() * (1. - rightTop);
        return new TrapezoidalNumber( leftTop, rightTop , leftDeviation , rightDeviation );
    }
    @Override
    public TrapezoidalNumber build( TrapezoidalNumber t ) {
        return new TrapezoidalNumber( t.getLeftTop(), t.getRightTop() , t.getLeftDeviation() , t.getRightDeviation() );
    }

    @Override
    public int compare( TrapezoidalNumber t1 , TrapezoidalNumber t2 ) {
        double diff =  (t1.getLeftTop() + t1.getRightTop()) / 2 - (t2.getLeftTop() + t2.getRightTop()) / 2;
        if( diff > 0 )
            return 1;
        if( diff < 0 )
            return -1;
        return 0;
    }

}
