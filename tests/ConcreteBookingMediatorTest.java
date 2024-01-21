import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConcreteBookingMediatorTest {

    private ConcreteBookingMediator mediator;
    private Customer customer;
    private Flight flight;
    private Hotel hotel;
    private PaymentStrategy paymentStrategy;

    @Before
    public void setUp() throws Exception {
        mediator = new ConcreteBookingMediator();
        customer = new Customer("John Doe", "john@example.com", "123456789", "paypal@example.com");
        flight = new Flight("F1", "Airline", "Departure", "Arrival", null, null, 100, 200, 100);
        hotel = new Hotel("H1", "Hotel", "Address", "City", "Country", 4, 100);
        paymentStrategy = new PayPalStrategy();
    }

    @After
    public void tearDown() throws Exception {
        mediator = null;
        customer = null;
        flight = null;
        hotel = null;
        paymentStrategy = null;
    }

    @Test
    public void createBooking_SuccessfulBooking() {
        Booking booking = mediator.createBooking(customer, flight, hotel, paymentStrategy, "pending");

        assertNotNull(booking);

        assertEquals("confermata", booking.getBookingStatus());
        assertEquals(99, booking.getFlight().getNAvailableSeats()); // 1 seat decreased
        assertEquals(99, booking.getHotel().getNAvailableRooms()); // 1 room decreased
    }

    @Test
    public void createBooking_UnsuccessfulBooking() {
        flight.setNAvailableSeats(0);
        hotel.setNAvailableRooms(0);

        Booking booking = mediator.createBooking(customer, flight, hotel, paymentStrategy, "pending");

        assertNotNull(booking);

        assertEquals("annullata", booking.getBookingStatus());
        assertEquals(0, booking.getFlight().getNAvailableSeats()); // no change
        assertEquals(0, booking.getHotel().getNAvailableRooms()); // no change
    }

    @Test
    public void cancelBooking_SuccessfulCancellation() {
        Booking booking = mediator.createBooking(customer, flight, hotel, paymentStrategy, "pending");

        mediator.cancelBooking(booking);

        assertEquals("annullata", booking.getBookingStatus());
        assertEquals(100, flight.getNAvailableSeats()); // 1 seat increased
        assertEquals(100, hotel.getNAvailableRooms()); // 1 room increased
    }

    @Test
    public void cancelBooking_AlreadyCancelled() {
        Booking booking = mediator.createBooking(customer, flight, hotel, paymentStrategy, "pending");
        mediator.cancelBooking(booking); // Cancel once

        assertEquals("annullata", booking.getBookingStatus());

        // Attempt to cancel again
        mediator.cancelBooking(booking);

        assertEquals("annullata", booking.getBookingStatus()); // Status remains the same
    }

    @Test
    public void cancelBooking_NotFound() {
        Booking booking = new Booking(customer, flight, hotel, paymentStrategy, mediator, "pending");

        // Attempt to cancel a booking that was not created through the mediator
        mediator.cancelBooking(booking);

        assertEquals(100, flight.getNAvailableSeats()); // No change
        assertEquals(100, hotel.getNAvailableRooms()); // No change
    }
}