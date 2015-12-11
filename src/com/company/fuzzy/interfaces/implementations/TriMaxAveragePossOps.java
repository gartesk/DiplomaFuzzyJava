package com.company.fuzzy.interfaces.implementations;

import com.company.fuzzy.hierarchy.TriangularNumber;
import com.company.fuzzy.interfaces.Builder;
import com.company.fuzzy.interfaces.Operations;

import java.util.Comparator;
import java.util.Random;

public class TriMaxAveragePossOps implements Operations<TriangularNumber>, Builder< TriangularNumber >, Comparator< TriangularNumber > {

    private static TriMaxAveragePossOps instance;

    private TriMaxAveragePossOps(){}

    public static TriMaxAveragePossOps getInstance() {
        if( instance == null ) {
            instance = new TriMaxAveragePossOps();
        }
        return instance;
    }

    @Override
    public TriangularNumber sum( TriangularNumber t1 , TriangularNumber t2 ) {
        return new TriangularNumber( t1.getCenter() + t2.getCenter() - t1.getCenter() * t2.getCenter()
                , 0.5 * ( t1.getLeftDeviation() + t2.getLeftDeviation() )
                , 0.5 * ( t1.getRightDeviation() + t2.getRightDeviation() ) );
    }
    @Override
    public TriangularNumber mult( TriangularNumber t1 , TriangularNumber t2 ) {
        return new TriangularNumber( t1.getCenter() * t2.getCenter()
                , Math.max(t1.getLeftDeviation(), t2.getLeftDeviation())
                , Math.max(t1.getRightDeviation(), t2.getRightDeviation()) );
    }
    @Override
    public TriangularNumber sumRev( TriangularNumber t ) {
        return new TriangularNumber( 1. - t.getCenter()
                , t.getLeftDeviation()
                , t.getRightDeviation() );
    }
    @Override
    public TriangularNumber multRev( TriangularNumber t ) {
        return null;
    }
    @Override
    public TriangularNumber sub( TriangularNumber t1 , TriangularNumber t2 ) {
        return null;
    }
    @Override
    public TriangularNumber div( TriangularNumber t1 , TriangularNumber t2 ) {
        return null;
    }

    @Override
    public TriangularNumber newMin() {
        return new TriangularNumber( 0. , 0. , 0. );
    }
    @Override
    public TriangularNumber newMax() {
        return new TriangularNumber( 1. , 0. , 0. );
    }
    @Override
    public TriangularNumber newRandom() {
        Random rand = new Random();
        double center = rand.nextDouble();
        double leftDeviation = rand.nextDouble() * center;
        double rightDeviation = rand.nextDouble() * (1. - center);
        return new TriangularNumber( center , leftDeviation , rightDeviation );
    }
    @Override
    public TriangularNumber build( TriangularNumber t ) {
        return new TriangularNumber( t.getCenter() , t.getLeftDeviation() , t.getRightDeviation() );
    }

    @Override
    public int compare( TriangularNumber t1 , TriangularNumber t2 ) {
        double diff =  t1.getCenter() - t2.getCenter();
        if( diff > 0 )
            return 1;
        if( diff < 0 )
            return -1;
        return 0;
    }

}
