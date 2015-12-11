package com.company.fuzzy.hierarchy;


public class TriangularNumber extends FuzzyNumber {

    private double center;
    private double leftDeviation;
    private double rightDeviation;

    public double getCenter() {
        return center;
    }
    public double getLeftDeviation() {
        return leftDeviation;
    }
    public double getRightDeviation() {
        return rightDeviation;
    }

    public TriangularNumber() {
        center = 0.;
        leftDeviation = 0.;
        rightDeviation = 0.;
    }
    public TriangularNumber( double center , double leftDeviation , double rightDeviation ) {
        this.center = center;
        this.leftDeviation = leftDeviation;
        this.rightDeviation = rightDeviation;
    }
    public TriangularNumber( TriangularNumber other ) {
        center = other.getCenter();
        leftDeviation = other.getLeftDeviation();
        rightDeviation = other.getRightDeviation();
    }

    public boolean equals( TriangularNumber other ) {
        return ( center == other.getCenter() ) && ( leftDeviation == other.getLeftDeviation() ) && ( rightDeviation == other.getRightDeviation() );
    }

    @Override
    public String toString() {
        StringBuilder tempString = new StringBuilder();
        tempString.append( "<" ).append( leftDeviation ).append( "," ).append( center )
                .append(",").append( rightDeviation ).append( ">" );
        return tempString.toString();
    }

}
