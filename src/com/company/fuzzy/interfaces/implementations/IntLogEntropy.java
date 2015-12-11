package com.company.fuzzy.interfaces.implementations;

import com.company.fuzzy.hierarchy.Interval;
import com.company.fuzzy.hierarchy.TriangularNumber;
import com.company.fuzzy.interfaces.Fuzziness;

public class IntLogEntropy implements Fuzziness<Interval> {

    private static IntLogEntropy instance;

    private IntLogEntropy(){}

    public static IntLogEntropy getInstance() {
        if( instance == null ) {
            instance = new IntLogEntropy();
        }
        return instance;
    }

    @Override
    public double getEntropy( Interval interval ) {
        return -(0.75 * Math.log(0.75) + 0.25 * Math.log(0.25)) * interval.getLength();
    }

}
