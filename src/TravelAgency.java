import java.util.ArrayList;
import java.util.List;

public class TravelAgency {

    private List<Booking> bookings;

    public TravelAgency() {
        this.bookings = new ArrayList<>();
    }

    public Booking createBooking(Customer customer, Flight flight, Hotel hotel) {
        Booking booking = new Booking(customer, flight, hotel);
        this.bookings.add(booking);
        return booking;
    }

    public void cancelBooking(Booking booking) {
        this.bookings.remove(booking);
    }

}
