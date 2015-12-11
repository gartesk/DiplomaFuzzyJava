package com.company.fuzzy.hierarchy;

public class Interval extends FuzzyNumber {

    private double leftEnd;
    private double rightEnd;

    public double getLeftEnd() {
        return leftEnd;
    }
    public double getRightEnd() {
        return rightEnd;
    }

    public double getLength() {
        return rightEnd - leftEnd;
    }

    public Interval() {
        leftEnd = 0;
        rightEnd = 0;
    }
    public Interval( double leftEnd , double rightEnd ) {
        this.leftEnd = leftEnd;
        this.rightEnd = rightEnd;
    }
    public Interval( Interval other ) {
        leftEnd = other.getLeftEnd();
        rightEnd = other.getRightEnd();
    }

    public boolean equals( Interval other ) {
        return ( leftEnd == other.getLeftEnd() ) && ( rightEnd == other.getRightEnd() );
    }

    @Override
    public String toString() {
        StringBuilder tempString = new StringBuilder();
        tempString.append( "[" ).append( leftEnd ).append( "," ).append( rightEnd ).append( "]" );
        return tempString.toString();
    }

}
