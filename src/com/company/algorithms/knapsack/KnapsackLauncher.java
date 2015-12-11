package com.company.algorithms.knapsack;

import com.company.fuzzy.interfaces.implementations.TriAveragePossOps;
import com.company.fuzzy.interfaces.implementations.TriExpOps;
import com.company.fuzzy.hierarchy.TriangularNumber;
import com.company.algorithms.knapsack.FullKnapsack;
import com.company.algorithms.knapsack.GreedyKnapsack;

import java.util.ArrayList;

public class KnapsackLauncher {

    public static void main( String[] args ) {

        ArrayList< TriangularNumber > costs = new ArrayList< TriangularNumber >();
        ArrayList< TriangularNumber > weights = new ArrayList< TriangularNumber >();
        costs.add( new TriangularNumber( 0.4 , 0.01 , 0.01 ) );
        costs.add( new TriangularNumber( 0.62 , 0.03 , 0.02 ) );
        costs.add( new TriangularNumber( 0.4 , 0.01 , 0.04 ) );
        weights.add( new TriangularNumber( 10. , 1. , 1. ) );
        weights.add( new TriangularNumber( 15. , 1. , 1. ) );
        weights.add( new TriangularNumber( 10. , 1. , 1. ) );

        TriangularNumber limit = new TriangularNumber( 20. , 1. , 1. );

        GreedyKnapsack< TriangularNumber , TriExpOps , TriAveragePossOps > gk
                = new GreedyKnapsack< TriangularNumber , TriExpOps , TriAveragePossOps >( costs , weights , limit , TriExpOps.getInstance() , TriAveragePossOps.getInstance() );
        FullKnapsack< TriangularNumber , TriExpOps , TriAveragePossOps > fk
                = new FullKnapsack< TriangularNumber , TriExpOps , TriAveragePossOps >( costs , weights , limit , TriExpOps.getInstance() , TriAveragePossOps.getInstance() );

        ArrayList< Boolean > greedyResult = gk.getResultVector();
        TriangularNumber greedyValue = gk.getCost();

        ArrayList< Boolean > bestResult = fk.getResultVector();
        TriangularNumber bestValue = fk.getCost();

        System.out.println( "Greedy: " + greedyResult + " , " + greedyValue );
        System.out.println( "Best: " + bestResult + " , " + bestValue );

    }
}
