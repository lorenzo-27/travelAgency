public class Booking {

    private Customer customer;
    private Flight flight;
    private Hotel hotel;

    public Booking(Customer customer, Flight flight, Hotel hotel) {
        this.customer = customer;
        this.flight = flight;
        this.hotel = hotel;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Flight getFlight() {
        return flight;
    }

    public Hotel getHotel() {
        return hotel;
    }

}
