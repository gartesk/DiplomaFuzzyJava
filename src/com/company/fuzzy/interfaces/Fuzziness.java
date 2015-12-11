package com.company.fuzzy.interfaces;

import com.company.fuzzy.hierarchy.FuzzyNumber;

public interface Fuzziness< T extends FuzzyNumber> {

    public double getEntropy( T t );

}
