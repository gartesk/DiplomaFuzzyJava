package com.company.algorithms.knapsack;

import com.company.fuzzy.hierarchy.FuzzyNumber;
import com.company.fuzzy.interfaces.Builder;
import com.company.fuzzy.interfaces.Operations;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

public class FullKnapsack< E extends FuzzyNumber ,
		O extends Comparator< E > & Operations< E > & Builder< E > ,
		P extends Comparator< E > & Operations< E > & Builder< E > > {

    private class PartialSolution< E extends FuzzyNumber >{
        protected ArrayList< Boolean > result;
        protected E weight;
        protected E cost;

        public < O extends Builder< E > , P extends Builder< E > > PartialSolution( int n , O wops , P cops ) {
            result = new ArrayList< Boolean >( n );
            for( int i = 0 ; i < n ; ++i ) {
                result.add( false );
            }
            weight = wops.newMin();
            cost = cops.newMin();
        }

        public < O extends Builder< E > , P extends Builder< E > >
        PartialSolution( PartialSolution< E > other , int index , E _weight , E _cost , O wops , P cops ) {
            result = new ArrayList< Boolean >( other.result );
            result.set( index , true );
            weight = wops.build( _weight );
            cost = cops.build( _cost );
        }

        public E getWeight() {
            return weight;
        }
        public E getCost() { return cost; }
        public ArrayList< Boolean > getResult() {
            return result;
        }
        public boolean has( int index ) {
            return result.get( index );
        }
        public boolean equals( PartialSolution s ) {
            return result.equals( s.result );
        }
    }

    private ArrayList< E > costs;
    private ArrayList< E > weights;
    private E limit;
    private HashSet< PartialSolution< E > > partialResults;
    private PartialSolution< E > finalResult;
    private O wOps;
    private P cOps;


    public FullKnapsack( ArrayList< E > _costs , ArrayList< E > _weights , E _limit , O _wops , P _cops ) {
        costs = _costs;
        weights = _weights;
        limit = _limit;
        wOps = _wops;
        cOps = _cops;
        partialResults = new HashSet< PartialSolution< E > >();
        int n = weights.size();
        partialResults.add( new PartialSolution< E >( n , wOps , cOps ) );
        finalResult = new PartialSolution< E >( n , wOps , cOps );

        runAlgorithm();
    }

    private boolean ableToAdd( PartialSolution< E > result , int index ) {
        return wOps.compare( wOps.sum( result.getWeight() , weights.get( index ) ) , limit ) <= 0;
    }

    private boolean iterate() {
        int n = weights.size();

        HashSet< PartialSolution< E > > newResults = new HashSet< PartialSolution< E > >();
        for( PartialSolution< E > result : partialResults ) {
            boolean added = false;
            for( int i = 0 ; i < n ; ++i ) {
                if( !result.has( i ) && ableToAdd( result , i ) ) {
                    newResults.add( new PartialSolution< E >( result , i
                            , wOps.sum( result.getWeight() , weights.get( i ) ) , cOps.sum( result.getCost() , costs.get( i ) ) , wOps, cOps) );
                    added = true;
                }
            }
            if( !added && cOps.compare( result.getCost() , finalResult.getCost() ) > 0  ) {
                finalResult = result;
            }
        }
        partialResults = newResults;
        return partialResults.size() != 0;
    }

    private void runAlgorithm() {
        boolean needToChange = true;
        while( needToChange )
            needToChange = iterate();
    }

    public E getCost( ) {
        return finalResult.getCost();
    }

    public ArrayList< Boolean > getResultVector() {
        return finalResult.getResult();
    }

}
