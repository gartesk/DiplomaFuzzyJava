package com.company.fuzzy.hierarchy;

public class TrapezoidalNumber extends FuzzyNumber {

    private double leftTop;
    private double rightTop;
    private double leftDeviation;
    private double rightDeviation;

    public double getLeftTop() {
        return leftTop;
    }
    public double getRightTop() {
        return rightTop;
    }
    public double getLeftDeviation() {
        return leftDeviation;
    }
    public double getRightDeviation() {
        return rightDeviation;
    }

    public TrapezoidalNumber() {
        leftTop = 0.;
        rightTop = 0.;
        leftDeviation = 0.;
        rightDeviation = 0.;
    }
    public TrapezoidalNumber( double leftTop , double rightTop , double leftDeviation , double rightDeviation ) {
        this.leftTop = leftTop;
        this.rightTop = rightTop;
        this.leftDeviation = leftDeviation;
        this.rightDeviation = rightDeviation;
    }
    public TrapezoidalNumber( TrapezoidalNumber other ) {
        leftTop = other.getLeftTop();
        rightTop = other.getRightTop();
        leftDeviation = other.getLeftDeviation();
        rightDeviation = other.getRightDeviation();
    }

    public boolean equals( TrapezoidalNumber other ) {
        return ( leftTop == other.getLeftTop() ) && ( rightTop == other.getRightTop() )
                && ( leftDeviation == other.getLeftDeviation() ) && ( rightDeviation == other.getRightDeviation() );
    }

    @Override
    public String toString() {
        StringBuilder tempString = new StringBuilder();
        tempString.append( "<" ).append( leftDeviation ).append( ",(" ).append( leftTop ).append( "," )
                .append( rightTop ).append("),").append( getRightDeviation() ).append( ">" );
        return tempString.toString();
    }

}
