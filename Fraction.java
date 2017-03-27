// Fraction Class
// A simple tool that will hold fractions in their most reduced form and allow for common fractional mathmeatics 
// 
// @author Elliot Lowe <elowe3@students.towson.edu>
// @version 03/26/2017

package fraction;

public class Fraction {
 private int numer;
 private int denom;

 // alternate constructor
 public Fraction(int numer, int denom) throws
         InvalidDenominatorException
 { 
     this.numer = numer;
     this.denom = denom;
 }
 
 // default constructor 
 // constructs a fraction object set to 1/1
 public Fraction()
 {
     this.numer = 1;
     this.denom = 1;   
 }

 // copy constructor
 // creates a second object of type fraction identical to the first
 public Fraction(Fraction otherFrac)
 { 
   this.numer = otherFrac.numer;
   this.denom = otherFrac.denom;
  
 }

 //getter 
 //returns data stored in numer member variable of the calling fraction
 public int getNumer()
 {
     return numer;
 }

 //getter 
 //returns data stored in denom member variable of the calling fraction
 public int getDenom()
 { 
 return denom;
 }

//converts the values of numer and denom, which are used to represent a fraction, into a String
//example: Fraction(1,2) ---> 1/2 
 public String toString()
 { 
      if(denom == 0)
         throw new InvalidDenominatorException();
      
     String fractionDisplay = (numer + "/" + denom);
     return fractionDisplay;
 }

 //returns a boolean statement comparing two fractions after converting fractions into a double representation of their decimal values
 public boolean equals(Fraction rFrac)
 { 
     if(denom == 0)
         throw new InvalidDenominatorException();
     
     Fraction frac1 = new Fraction(numer, denom);
     Fraction frac2 = new Fraction(rFrac.getNumer(), rFrac.getDenom());
     
     double dec1 = frac1.convertToDecimal();
     double dec2 = frac2.convertToDecimal();
     
     return (dec1 == dec2);
 }

 
//adds togther two fractions and then returns a new fraction which is the sum of the two added fractions
 public Fraction add(Fraction rFrac)
 {
     if((denom ==0 || rFrac.denom == 0))
         throw new InvalidDenominatorException();
     
     Fraction fraction1 = new Fraction((this.numer * rFrac.denom), (this.denom * rFrac.denom));
     Fraction fraction2 = new Fraction((rFrac.numer * this.denom), (rFrac.denom * this.denom));
     
    Fraction fractionSum = new Fraction(fraction1.getNumer() + fraction2.getNumer(), fraction2.getDenom());
    fractionSum = fractionSum.reduce();
    
    return fractionSum; 
 }     

 //subtracts from one fraction the valuee of another fraction and returns a new fraction which is the difference between the two fractions
 public Fraction sub(Fraction rFrac)
 {
     if((denom ==0 || rFrac.denom == 0))
         throw new InvalidDenominatorException();
     
     Fraction fraction1 = new Fraction((numer * rFrac.denom), (denom * rFrac.denom));
     Fraction fraction2 = new Fraction((rFrac.numer * denom), (rFrac.denom * denom));
     
    Fraction fractionSum = new Fraction(fraction1.getNumer() - fraction2.getNumer(), fraction2.getDenom()).reduce();
    
    return fractionSum; 
 }

 //multiplies two fractions together and returns a new fraction which is the product of the two fractions reduced to its simplest form
 public Fraction mult(Fraction rFrac)
 { 
     if(denom == 0)
         throw new InvalidDenominatorException();
     
     int multNum = numer * rFrac.numer;
     int multDen = denom * rFrac.denom;
     Fraction mult = new Fraction(multNum, multDen);
    return mult.reduce();
 }

 //divides one fraction by another fraction and returns a new fraction which is the result of the divsion reduced to its simplest form
 public Fraction div(Fraction rFrac)
 { 
     if(denom == 0)
         throw new InvalidDenominatorException();
     
     int divNum = numer * rFrac.denom;
     int divDen = denom * rFrac.numer;
     return new Fraction(divNum, divDen).reduce();
 }

 //returns a boolean statement which compares a fraction to its simplest form
 public boolean isSimplestForm()
 { 
     Fraction actual = new Fraction(numer, denom);
     Fraction simplest = new Fraction(numer, denom).reduce();
     return (actual.numer == simplest.numer) && (actual.denom == simplest.denom);        
 }

 //takes "this" fraction and returns a new fraction which is the simplest form of "this" fraction
 public Fraction reduce()
 { 
     int gcd = gcd(this.numer, this.denom);
     Fraction reducedFraction = new Fraction((this.numer/gcd), (this.denom/gcd));
     return reducedFraction;
 }
 
///returns a boolean statement after converting a fraction to a new double decimal representation of that fraction and checking that it is between -1 and 1. 
 public boolean properFrac()
 {
     if(denom == 0)
         throw new InvalidDenominatorException();
     
     Fraction testForProperFrac = new Fraction(numer, denom);
     return testForProperFrac.convertToDecimal() < 1 && testForProperFrac.convertToDecimal() > -1;
 }

 //takes "this" fraction and returns a new fraction which is the absolute value of "this" fraction
 public Fraction abs()
 { 
     if(denom == 0)
         throw new InvalidDenominatorException();
     
     Fraction absoluteValue = new Fraction(Math.abs(numer), Math.abs(denom));
     return absoluteValue;
 }

 //returns a boolean statement after converting two fractions to their decimal representation and checking if the first decimal is less than the second
 public boolean lessThan(Fraction rFrac)
 {
     if(denom == 0 || rFrac.denom ==0)
         throw new InvalidDenominatorException();
     
     Fraction fracOne = new Fraction(numer, denom);
     Fraction fracTwo = new Fraction(rFrac.numer, rFrac.denom);
     double decimalOne = fracOne.convertToDecimal();
     double decimalTwo = fracTwo.convertToDecimal();
     return decimalOne < decimalTwo;
 }
         

//returns a boolean statement after converting two fractions to their decimal representation and checking if the first decimal is greater than the second
 public boolean greaterThan(Fraction rFrac)
 {
     if(denom == 0 || rFrac.denom ==0)
         throw new InvalidDenominatorException();
     
     Fraction fracOne = new Fraction(numer, denom);
     Fraction fracTwo = new Fraction(rFrac.numer, rFrac.denom);
     double decimalOne = fracOne.convertToDecimal();
     double decimalTwo = fracTwo.convertToDecimal();
     return decimalOne > decimalTwo;
 }

 //takes "this" fraction and converts it to a type double which is a decimal representation of that fraction
 public double convertToDecimal() 
{
    if(denom == 0)
         throw new InvalidDenominatorException();
    
    double decimalConversion = ((double)numer/denom);
    return decimalConversion;
}

 //takes "this" fraction and returns a new Fraction which is the inversion of "this" fraction
 public Fraction invert()
 {
     if(denom == 0)
         throw new InvalidDenominatorException();
     
     Fraction invertedFraction = new Fraction(denom, numer);
     return invertedFraction; 
 }
     
//private class that returns an int value that is equal to the greatest common denominator of two different int values   
private int gcd(int var1, int var2)
{
    int X, Y, Z;
    
    if(var1>var2)
    {
        Y = var1;
        X = var2;
        
    }
    else
    {
        Y = var2;
        X = var1; 
    }
    
    Z = X%Y;
    
    while (Z!=0)
    {
        X=Y;
        Y=Z;
        Z=X%Y;         
    }
    
    return Y;
}
}
