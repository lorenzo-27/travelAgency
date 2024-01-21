import java.util.ArrayList;
import java.util.List;

public class TravelAgency {

    private final List<Booking> bookings;
    private final ConcreteBookingMediator mediator;

    public TravelAgency(ConcreteBookingMediator mediator) {
        this.bookings = new ArrayList<>();
        this.mediator = mediator;
    }

    public Booking createBooking(Customer customer, Flight flight, Hotel hotel, PaymentStrategy strategy, String bookingStatus) {
        Booking booking = mediator.createBooking(customer, flight, hotel, strategy, bookingStatus);
        this.bookings.add(booking);
        return booking;
    }

    public void cancelBooking(Booking booking) {
        this.bookings.remove(booking);
        mediator.cancelBooking(booking);
    }


    // The following methods are used to add some functionality to the travel agency
    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookings);
    }

    public List<Booking> getBookingsByCustomer(Customer customer) {
        List<Booking> bookingsByCustomer = new ArrayList<>();
        for (Booking booking : bookings) {
            if (booking.getCustomer().equals(customer)) {
                bookingsByCustomer.add(booking);
            }
        }
        return bookingsByCustomer;
    }

    public List<Booking> getBookingsByFlight(Flight flight) {
        List<Booking> bookingsByFlight = new ArrayList<>();
        for (Booking booking : bookings) {
            if (booking.getFlight().equals(flight)) {
                bookingsByFlight.add(booking);
            }
        }
        return bookingsByFlight;
    }

    public List<Booking> getBookingsByHotel(Hotel hotel) {
        List<Booking> bookingsByHotel = new ArrayList<>();
        for (Booking booking : bookings) {
            if (booking.getHotel().equals(hotel)) {
                bookingsByHotel.add(booking);
            }
        }
        return bookingsByHotel;
    }

}
