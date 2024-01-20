import java.util.ArrayList;
import java.util.List;

public class TravelAgency {

    private List<Booking> Bookings;
    private ConcreteBookingMediator mediator;

    public TravelAgency(ConcreteBookingMediator mediator) {
        this.Bookings = new ArrayList<>();
        this.mediator = mediator;
    }

    public Booking createBooking(Customer customer, Flight flight, Hotel hotel, PaymentStrategy strategy, String bookingStatus) {
        Booking booking = mediator.createBooking(customer, flight, hotel, strategy, bookingStatus);
        this.Bookings.add(booking);
        return booking;
    }

    public void cancelBooking(Booking booking) {
        this.Bookings.remove(booking);
        mediator.cancelBooking(booking);
    }


    // The following methods are used to add some functionality to the travel agency
    public List<Booking> getAllBookings() {
        return new ArrayList<>(Bookings);
    }

    public List<Booking> getBookingsByCustomer(Customer customer) {
        List<Booking> bookingsByCustomer = new ArrayList<>();
        for (Booking booking : Bookings) {
            if (booking.getCustomer().equals(customer)) {
                bookingsByCustomer.add(booking);
            }
        }
        return bookingsByCustomer;
    }

    public List<Booking> getBookingsByFlight(Flight flight) {
        List<Booking> bookingsByFlight = new ArrayList<>();
        for (Booking booking : Bookings) {
            if (booking.getFlight().equals(flight)) {
                bookingsByFlight.add(booking);
            }
        }
        return bookingsByFlight;
    }

    public List<Booking> getBookingsByHotel(Hotel hotel) {
        List<Booking> bookingsByHotel = new ArrayList<>();
        for (Booking booking : Bookings) {
            if (booking.getHotel().equals(hotel)) {
                bookingsByHotel.add(booking);
            }
        }
        return bookingsByHotel;
    }

    public List<Booking> getBookingsByPaymentStrategy(PaymentStrategy strategy) {
        List<Booking> bookingsByPaymentStrategy = new ArrayList<>();
        for (Booking booking : Bookings) {
            if (booking.getStrategy().equals(strategy)) {
                bookingsByPaymentStrategy.add(booking);
            }
        }
        return bookingsByPaymentStrategy;
    }

    public List<Booking> getBookingsByBookingStatus(String bookingStatus) {
        List<Booking> bookingsByBookingStatuses = new ArrayList<>();
        for (Booking booking : Bookings) {
            if (booking.getBookingStatus().equals(bookingStatus)) {
                bookingsByBookingStatuses.add(booking);
            }
        }
        return bookingsByBookingStatuses;
    }

}
