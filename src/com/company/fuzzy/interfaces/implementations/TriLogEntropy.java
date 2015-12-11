package com.company.fuzzy.interfaces.implementations;

import com.company.fuzzy.hierarchy.TriangularNumber;
import com.company.fuzzy.interfaces.Fuzziness;

public class TriLogEntropy implements Fuzziness< TriangularNumber > {

    private static TriLogEntropy instance;

    private TriLogEntropy(){}

    public static TriLogEntropy getInstance() {
        if( instance == null ) {
            instance = new TriLogEntropy();
        }
        return instance;
    }

    @Override
    public double getEntropy( TriangularNumber triangularNumber ) {
        return 0.5 * ( triangularNumber.getLeftDeviation() + triangularNumber.getRightDeviation() );
    }

}
