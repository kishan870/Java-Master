//This program will compute area of triangle and rectangle using method overloading
public class AreaCalculator {

    //This method will compute area of rectangle
    public static double getArea(double l, double b) {
        return l*b;
    }

    public static double getArea(double a, double b, double c) {
        double s = (a+b+c)/2;
        return Math.sqrt(s*(s-a)*(s-b)*(s-c));
    }

    public static void main(String[] args) {
        System.out.println("Area of the rectangle with sides 5.32 and 6.77 is " + getArea(5.32, 6.77));
        System.out.println("Area of the " +
                "triangle with sides 5.34, 7.54, and 6.88 is " + getArea(5.34, 7.54, 6.88));
    }
}
