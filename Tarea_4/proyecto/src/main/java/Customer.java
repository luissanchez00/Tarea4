
import java.util.Enumeration;
import java.util.Vector;

public class Customer {

    private String _name;
    private Vector _rentals = new Vector();
    private int _daysRented;
    private Movie _movie;
    private Rental rental = new Rental(_movie,_daysRented);

    public Customer(String name) {
        _name = name;
    }

    public void addRental(Rental arg) {
        _rentals.addElement(arg);
    }

    public String statement() {
        double totalAmount = 0;

        int frequentRenterPoints = 0;
        Enumeration rentals = _rentals.elements();
        String result = "Rental Record for " + getName() + "\n";

        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            double thisAmount = 0;
            // add frequent renter points
            frequentRenterPoints++;
            rental.getCharge(each, thisAmount);
            // add bounus for a two day new release rental
            frequentRenterPoints = getFrequentRenterPoints(each, frequentRenterPoints);
            // show figures for this rental
            result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(thisAmount) + "\n";
            totalAmount += thisAmount;
            // add footer lines
            result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
            result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";

        }
        return result;
    }

    public int getFrequentRenterPoints(Rental each, int frequentRenterPoints) {
        if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) && each.getDaysRented() > 1) {
            return 2;
        }
        else
            return 1;
    }

    public String getName() {
        return _name;
    }

}
