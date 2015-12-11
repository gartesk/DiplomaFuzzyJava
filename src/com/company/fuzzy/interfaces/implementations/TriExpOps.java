package com.company.fuzzy.interfaces.implementations;

import com.company.fuzzy.hierarchy.TriangularNumber;
import com.company.fuzzy.interfaces.Builder;
import com.company.fuzzy.interfaces.Operations;

import java.util.Comparator;
import java.util.Random;

public class TriExpOps implements Operations< TriangularNumber >, Comparator< TriangularNumber > , Builder< TriangularNumber > {

    private static TriExpOps instance;

    private TriExpOps(){}

    public static TriExpOps getInstance() {
        if( instance == null ) {
            instance = new TriExpOps();
        }
        return instance;
    }

    @Override
    public TriangularNumber sum( TriangularNumber t1 , TriangularNumber t2 ) {
        return new TriangularNumber( t1.getCenter() + t2.getCenter()
                , t1.getLeftDeviation() * t2.getLeftDeviation()
                , t1.getRightDeviation() * t2.getRightDeviation()  );
    }
    @Override
    public TriangularNumber sumRev( TriangularNumber t ) {
        return new TriangularNumber( - t.getCenter()
                , 1. / t.getLeftDeviation()
                , 1. / t.getLeftDeviation() );
    }
    @Override
    public TriangularNumber sub( TriangularNumber t1 , TriangularNumber t2 ) {
        return sum( t1 , sumRev( t2 ) );
    }
    @Override
    public TriangularNumber mult( TriangularNumber t1 , TriangularNumber t2 ) {
        return new TriangularNumber( t1.getCenter() * t2.getCenter()
                , Math.exp( Math.log( t1.getLeftDeviation() ) * Math.log( t2.getLeftDeviation() ) )
                , Math.exp( Math.log( t1.getRightDeviation() ) * Math.log( t2.getRightDeviation() ) ) );
    }
    @Override
    public TriangularNumber multRev( TriangularNumber t ) {
        return new TriangularNumber( 1. / t.getCenter()
                , Math.exp( 1. / Math.log( t.getLeftDeviation() ) )
                , Math.exp( 1. / Math.log( t.getLeftDeviation() ) ) );
    }
    @Override
    public TriangularNumber div( TriangularNumber t1 , TriangularNumber t2 ) {
        return mult(t1, multRev(t2));
    }

    @Override
    public TriangularNumber newMin() {
        return new TriangularNumber( 0. , 1. , 1. );
    }
    @Override
    public TriangularNumber newMax() {
        return new TriangularNumber( Double.MAX_VALUE, 0., 0. );
    }
    @Override
    public  TriangularNumber newRandom() {
        Random rand = new Random();
        double center = rand.nextDouble() * Double.MAX_VALUE;
        double leftDeviation = rand.nextDouble() * center;
        double rightDeviation = rand.nextDouble() * (Double.MAX_VALUE - center);
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
