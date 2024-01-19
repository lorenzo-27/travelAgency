import java.util.ArrayList;
import java.util.List;

public class travelAgency {

    private List<booking> bookings;
    private bookingMediator mediator;

    public travelAgency(bookingMediator mediator) {
        this.bookings = new ArrayList<>();
        this.mediator = mediator;
    }

    public booking createBooking(customer customer, flight flight, hotel hotel, paymentStrategy strategy, String bookingStatus) {
        booking booking = new booking(customer, flight, hotel, strategy, mediator, bookingStatus);
        this.bookings.add(booking);
        booking.confirmBooking();
        booking.setBookingStatus("Confermata");
        return booking;
    }

    public void cancelBooking(booking booking) {
        this.bookings.remove(booking);
        booking.cancelBooking();
        booking.setBookingStatus("Annullata");
    }

    public void setMediator(bookingMediator mediator) {
        this.mediator = mediator;
    }

    public bookingMediator getMediator() {
        return mediator;
    }

}
