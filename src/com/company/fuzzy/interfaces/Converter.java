package com.company.fuzzy.interfaces;

import com.company.fuzzy.hierarchy.FuzzyNumber;

public interface Converter< E extends FuzzyNumber , T extends FuzzyNumber > {

    public T straight( E e );
    public E inverse( T t );

}
