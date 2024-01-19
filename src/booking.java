public class booking {

    private customer customer;
    private flight flight;
    private hotel hotel;
    private paymentStrategy strategy;
    private bookingMediator mediator;
    private String bookingStatus;

    public booking(customer customer, flight flight, hotel hotel, paymentStrategy strategy, bookingMediator mediator, String bookingStatus) {
        this.customer = customer;
        this.flight = flight;
        this.hotel = hotel;
        this.strategy = strategy;
        this.mediator = mediator;
        this.bookingStatus = bookingStatus;
    }

    public customer getCustomer() {
        return customer;
    }

    public flight getFlight() {
        return flight;
    }

    public hotel getHotel() {
        return hotel;
    }

    public paymentStrategy getStrategy() {
        return new bookingAdapterImpl(this);
    }

    public bookingMediator getMediator() {
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
