package com.company.algorithms.knapsack;

import com.company.fuzzy.hierarchy.FuzzyNumber;
import com.company.fuzzy.interfaces.Builder;
import com.company.fuzzy.interfaces.Operations;

import java.util.*;

public class GreedyKnapsack< E extends FuzzyNumber ,
		O extends Comparator< E > & Operations< E > & Builder< E > ,
		P extends Comparator< E > & Operations< E > & Builder< E > > {

    private ArrayList< E > costs;
    private ArrayList< E > weights;
    private E limit;
    O wOps;
    P cOps;

    ArrayList< Boolean > resultVector;
    E cost;

    public GreedyKnapsack( ArrayList< E > _costs , ArrayList< E > _weights , E _limit , O _wops , P _cops ) {
        costs = _costs;
        weights = _weights;
        limit = _limit;
        wOps = _wops;
        cOps = _cops;
        resultVector = null;
        cost = cOps.newMin();

        runAlgorithm();
    }

    private ArrayList< E > getRatios() {

        ArrayList< E > ratios = new ArrayList< E >( costs );

        Iterator< E > rIt = ratios.iterator();
        Iterator< E > wIt = weights.iterator();
        while( rIt.hasNext() ) {
            E elem = rIt.next();
            elem = wOps.div( elem , wIt.next() );
        }

        return ratios;
    }

    private void runAlgorithm () {

        ArrayList< E > ratios = getRatios();

        int n = ratios.size();
        resultVector = new ArrayList< Boolean >( n );
        for( int i = 0 ; i < n ; ++i ) {
            resultVector.add( false );
        }
        E currSum = wOps.newMin();

        while( wOps.compare( currSum , limit ) <= 0 ) {

            E currMax = wOps.newMin();
            int max = -1;

            for( int i = 0 ; i < n ; ++i ) {
                E tempVal = ratios.get( i );
                if( wOps.compare( currMax , tempVal ) < 0 && !resultVector.get( i ) ) {
                    E tempSum = wOps.sum( currSum , weights.get( i ) );
                    if( wOps.compare( tempSum , limit ) <= 0 ) {
                        currMax = tempVal;
                        max = i;
                    }
                }
            }

            if( max >= 0 ) {
                currSum = wOps.sum( currSum, weights.get( max ) );
                cost = cOps.sum( cost , costs.get( max ) );
                resultVector.set( max , true );
            }
            else {
                break;
            }
        }

    }

    public E getCost() {
        return cost;
    }

    public ArrayList< Boolean > getResultVector() {
        return resultVector;
    }



}
