package com.company.fuzzy.interfaces;

import com.company.fuzzy.hierarchy.FuzzyNumber;

public interface Builder< E extends FuzzyNumber > {

    public E newMin();

    public E newMax();

    public E newRandom();

    public E build( E e );

}
