package com.example.project;
public class LinearCalculator{
    //FOR EXTRA CREDIT 
    //you should copy and paste all of your code from the LinearCalculator class
    // but NOT printInfo(). Please update it below
    //INSTANCE VARIABLES 
    //4 INTEGER variables (name them: x1,x2,y1,y2) 
    private int x1;
    private int x2;
    private int y1;
    private int y2;


    //CONSTRUCTOR
    //1 constructor with 2 String parameters. Each parameter represents a coordinate. 
    //For example, "(1,2)" and "(3,4)" would be two parameter values 
    //You will have to parse the string into 4 integers, representing the 2 points.
    public LinearCalculator(String coord1, String coord2){ // <--add 2 string parameters to this constructor
        int commaIndx1 = coord1.indexOf(","); // Finds out where the comma is (center of string)
        int commaIndx2 = coord2.indexOf(","); // Same as above
        x1 = Integer.parseInt(coord1, 1,commaIndx1,10); // Gets the numbers between the "(" and before the ","
        y1 = Integer.parseInt(coord1, commaIndx1+1,coord1.length()- 1,10); // Gets the numbers between the "," and before the ")"
        x2 = Integer.parseInt(coord2, 1,commaIndx2,10); // Gets the numbers between the "(" and before the ","
        y2 = Integer.parseInt(coord2, commaIndx2+1,coord2.length()- 1,10); // Gets the numbers between the "," and before the ")"
    }



    //METHODS
    //getters and setters for the 4 instance variables (8 methods total) 
    public int getX1(){return x1;}
    public int getY1(){return y1;}
    public int getX2(){return x2;}
    public int getY2(){return y2;}
    public void setX1(int newx1){x1 = newx1;}
    public void setY1(int newy1){y1 = newy1;}
    public void setX2(int newx2){x2 = newx2;}
    public void setY2(int newy2){y2 = newy2;}


    //distance() -> returns a double. sqrt(pow(x2-x1,2) + pow(y2-y1,2))
    //calculates the distance between the two points to the nearest HUNDREDTH and returns the value.
    public double distance(){
        double distance = Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2)); // Uses the distance formula
        return roundedToHundredth(distance);
    }
    //yInt() -> returns a double.
    //calculates the y intercept of the equation and returns the value to the nearest HUNDREDTH
    //if y-int if undefined, should return -999.99
    public double yInt(){
        if ((x2-x1) == 0) {
            return -999.99;
        } else {
            double yInt = (double)y1 - (slope() * x1); // y=mx+b --> y-mx = b, b is the y-intercept
            return roundedToHundredth(yInt);
        }
    }

    //slope() -> returns a double. (y2-y1)/(x2-x1)
    //calculates the slope of the equations and returns the value to the nearest HUNDREDTH
    //if slope is undefined, should return -999.99
    public double slope(){
        if ((x2-x1) == 0){
            return -999.99;
        } else {
            double slope = (double)(y2-y1)/(x2-x1); // I casted (y2-y1) to a double because if if I dont it sometimes returns 0.0 for certain numbers, uses slope formula
            return roundedToHundredth(slope);
        }
    }

    //equations() -> returns a String.
    //calculates the final equation in y=mx+b form and returns the string
    //if the equation has no slope, the equation should return -> "undefined"
    //HINT: You may need other custom methods to decrease the amount of code in the equations() method
    public String equation(){
        if (slope() == -999.99) { // If the slope is equal to -999.99 that means it is undefined
            return "undefined";
        } else if (yInt() == 0 && slope() == 0) { // If both the slope and the y intercept is zero then it shouldn't include those in the equation
            return "y=x";
        } else if (yInt() == 0)  { // If the y intercept is 0 then it should not be included
            return "y=" + slope() + "x";
        }else if (slope() == 0)  { // If the slope is 0 then it should not be included
            return "y=" + yInt();
        } else if (yInt() < 0) { // If the y int is negative then it should be x-num instead of x+-num
            return "y=" + slope() + "x" + yInt(); 
        } else { 
            return "y=" + slope() + "x+" + yInt(); // Returns full y=mx+b
        } 
    }


    //roundedToHundredth(double x)-> returns double
    //calculates the input to the nearest hundredth and returns that value
    public double roundedToHundredth(double x){
        return Math.round(x*100.00)/100.00; // Multiplies the num by 100 so that anything less than the hundredths place is still a decimal, rounds to the nearest whole number, divides by 100 again so the number returns to it's original places.
    } // example: original number is 1.235, 1.235*100 = 123.5, Math.round(123.5) = 123, 123/100 = 1.23, returns the number rounded to the nearest hundredth.

    //You will need to concatenate to the string 
    //the results from findSymmetry() and Midpoint()
    public String printInfo(){
        String str = "The two points are: (" + x1 + "," + y1  + ")";
        str += " and " + "(" + x2 + "," + y2 + ")";
        str += "\nThe equation of the line between these points is: " + equation() ;
        str += "\nThe slope of this line is: " + slope();
        str += "\nThe y-intercept of the line is: " + yInt();
        str += "\nThe distance between the two points is: " + distance();
        str += "\n"+findSymmetry();
        str += "\n"+Midpoint();
        return str;
    }

    //findSymmetry()-> returns a string 
    //the method should determine if there is symmetry between the two points
    // there should be  4 return statements 
    // return "Symmetric about the x-axis"; // x,-y
    // return "Symmetric about the y-axis"; // -x,y
    //return "Symmetric about the origin"; //-x,-y
    //return "No symmetry"; //x,y

    public String findSymmetry(){
        if (x1 == (x2*-1)) { // ex: x1 = 3 and x2 = -3
            if (y1 == (y2*-1)) { // y1 = 2 and y2 = -2
                return "Symmetric about the origin"; // if x1 and y1 = -x1 and -y1
            } else {
                return "Symmetric about the y-axis"; // if x1 and y1 = -x1 and y1
            }
        } else {
            if (y1 == (y2*-1)) {
                return "Symmetric about the x-axis"; // if x1 and y1 = x1 and -y1
            }
            return "No symmetry"; // if x1 and y1 = x1 and y1
        }
    }

    //Midpoint()->return a string 
    //the method should calculate the midpoint between the two points
    //it should return "The midpoint of this line is: (0,0)";
    public String Midpoint(){ // (x1+x2)/2,(y1+y2)/2 
        double xMidPoint = (double)(x1 + x2)/2; // uses midpoint formula for x
        double yMidPoint = (double)(y1 + y2)/2; // uses midpoint formula for y
        return "The midpoint of this line is: (" + xMidPoint + "," + yMidPoint + ")"; // combines x midpoint and y midpoint into (x,y) format
    }

}



