import java.util.ArrayList;
import java.util.List;

public class travelAgency {

    private List<booking> bookings;
    private concreteBookingMediator mediator;

    public travelAgency(concreteBookingMediator mediator) {
        this.bookings = new ArrayList<>();
        this.mediator = mediator;
    }

    public booking createBooking(customer customer, flight flight, hotel hotel, paymentStrategy strategy, String bookingStatus) {
        booking booking = mediator.createBooking(customer, flight, hotel, strategy, bookingStatus);
        this.bookings.add(booking);
        return booking;
    }

    public void cancelBooking(booking booking) {
        this.bookings.remove(booking);
        mediator.cancelBooking(booking);
    }

    public List<booking> getAllBookings() {
        return new ArrayList<>(bookings);
    }

}
