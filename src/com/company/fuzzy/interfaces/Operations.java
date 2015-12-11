package com.company.fuzzy.interfaces;

import com.company.fuzzy.hierarchy.FuzzyNumber;

public interface Operations< T extends FuzzyNumber > {

    public  T sum( T t1 , T t2 );
    public T mult( T t1 , T t2 );
    public T sumRev( T t );
    public T multRev( T t );
    public T sub( T t1 , T t2 );
    public T div( T t1 , T t2 );

}
