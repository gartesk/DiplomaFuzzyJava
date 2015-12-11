package com.company.fuzzy.interfaces.implementations;

import com.company.fuzzy.hierarchy.TrapezoidalNumber;
import com.company.fuzzy.hierarchy.TriangularNumber;
import com.company.fuzzy.interfaces.Fuzziness;

public class TrapLogEntropy implements Fuzziness<TrapezoidalNumber> {

    private static TrapLogEntropy instance;

    private TrapLogEntropy(){}

    public static TrapLogEntropy getInstance() {
        if( instance == null ) {
            instance = new TrapLogEntropy();
        }
        return instance;
    }

    @Override
    public double getEntropy( TrapezoidalNumber trapezoidalNumber ) {
        return 0.5 * ( trapezoidalNumber.getLeftDeviation() + trapezoidalNumber.getRightDeviation() );
    }

}
