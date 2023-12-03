public class Booking {

    private Customer customer;
    private Flight flight;
    private Hotel hotel;
    private PaymentStrategy strategy;
    private BookingMediator mediator;

    public Booking(Customer customer, Flight flight, Hotel hotel, PaymentStrategy strategy, BookingMediator mediator) {
        this.customer = customer;
        this.flight = flight;
        this.hotel = hotel;
        this.strategy = strategy;
        this.mediator = mediator;
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

    public void confirmBooking() {
        mediator.bookFlightAndHotel(this);
    }

    public void cancelBooking() {
        mediator.cancelBooking(this);
    }

}
