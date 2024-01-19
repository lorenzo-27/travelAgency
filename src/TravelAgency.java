import java.util.ArrayList;
import java.util.List;

public class TravelAgency {

    private List<Booking> bookings;
    private BookingMediator mediator;

    public TravelAgency(BookingMediator mediator) {
        this.bookings = new ArrayList<>();
        this.mediator = mediator;
    }

    public Booking createBooking(Customer customer, Flight flight, Hotel hotel, PaymentStrategy strategy, String bookingStatus) {
        Booking booking = new Booking(customer, flight, hotel, strategy, mediator, bookingStatus);
        this.bookings.add(booking);
        return booking;
    }

    public void cancelBooking(Booking booking) {
        this.bookings.remove(booking);
        booking.cancelBooking();
    }

    public void setMediator(BookingMediator mediator) {
        this.mediator = mediator;
    }

    public BookingMediator getMediator() {
        return mediator;
    }

}
