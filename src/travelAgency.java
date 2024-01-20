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


    // The following methods are used to add some functionality to the travel agency
    public List<booking> getAllBookings() {
        return new ArrayList<>(bookings);
    }

    public List<booking> getBookingsByCustomer(customer customer) {
        List<booking> bookingsByCustomer = new ArrayList<>();
        for (booking booking : bookings) {
            if (booking.getCustomer().equals(customer)) {
                bookingsByCustomer.add(booking);
            }
        }
        return bookingsByCustomer;
    }

    public List<booking> getBookingsByFlight(flight flight) {
        List<booking> bookingsByFlight = new ArrayList<>();
        for (booking booking : bookings) {
            if (booking.getFlight().equals(flight)) {
                bookingsByFlight.add(booking);
            }
        }
        return bookingsByFlight;
    }

    public List<booking> getBookingsByHotel(hotel hotel) {
        List<booking> bookingsByHotel = new ArrayList<>();
        for (booking booking : bookings) {
            if (booking.getHotel().equals(hotel)) {
                bookingsByHotel.add(booking);
            }
        }
        return bookingsByHotel;
    }

    public List<booking> getBookingsByPaymentStrategy(paymentStrategy strategy) {
        List<booking> bookingsByPaymentStrategy = new ArrayList<>();
        for (booking booking : bookings) {
            if (booking.getStrategy().equals(strategy)) {
                bookingsByPaymentStrategy.add(booking);
            }
        }
        return bookingsByPaymentStrategy;
    }

    public List<booking> getBookingsByBookingStatus(String bookingStatus) {
        List<booking> bookingsByBookingStatus = new ArrayList<>();
        for (booking booking : bookings) {
            if (booking.getBookingStatus().equals(bookingStatus)) {
                bookingsByBookingStatus.add(booking);
            }
        }
        return bookingsByBookingStatus;
    }

}
