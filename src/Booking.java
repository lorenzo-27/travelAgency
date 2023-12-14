public class Booking {

    private Customer customer;
    private Flight flight;
    private Hotel hotel;
    private PaymentStrategy strategy;
    private BookingMediator mediator;
    private String bookingStatus;

    public Booking(Customer customer, Flight flight, Hotel hotel, PaymentStrategy strategy, BookingMediator mediator, String bookingStatus) {
        this.customer = customer;
        this.flight = flight;
        this.hotel = hotel;
        this.strategy = strategy;
        this.mediator = mediator;
        this.bookingStatus = bookingStatus;
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

    public PaymentStrategy getStrategy() {
        return new BookingAdapterImpl(this);
    }

    public BookingMediator getMediator() {
        return mediator;
    }

    public int getTotalPrice() {
        return flight.getPrice() + hotel.getPrice();
    }

    public void pay() {
        strategy.pay(this);
    }

    public void confirmBooking() {
        mediator.bookFlightAndHotel(this);
    }

    public void cancelBooking() {
        mediator.cancelBooking(this);
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

}
