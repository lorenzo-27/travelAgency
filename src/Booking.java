public class Booking {

    private final Customer customer;
    private final Flight flight;
    private final Hotel hotel;
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
        return strategy;
    }

    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public BookingMediator getMediator() {
        return mediator;
    }

    public void setMediator(BookingMediator mediator) {
        this.mediator = mediator;
    }

    public void pay() {
        strategy.pay(this);
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public int calculateTotalPrice(Booking booking) {
        // Simula il calcolo del prezzo totale della prenotazione
        int flightPrice = booking.getFlight().getPrice();
        int hotelPrice = booking.getHotel().getPrice();
        return flightPrice + hotelPrice;
    }

}
