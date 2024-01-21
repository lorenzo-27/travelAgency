import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TravelAgencyTest {
    private ConcreteBookingMediator mediator;
    private TravelAgency travelAgency;
    private Customer customer;
    private Flight flight;
    private Hotel hotel;
    private PaymentStrategy paymentStrategy;

    @Before
    public void setUp() throws Exception {
        mediator = new ConcreteBookingMediator();
        travelAgency = new TravelAgency(mediator);
        customer = new Customer("John Doe", "john@example.com", "123456789", "paymentInfo");
        flight = new Flight("F123", "Airline", "Departure", "Arrival", null, null, 2, 100, 100);
        hotel = new Hotel("H456", "Hotel", "Address", "City", "Country", 30, 100);
        paymentStrategy = new PayPalStrategy();
    }

    @After
    public void tearDown() throws Exception {
        mediator = null;
        travelAgency = null;
        customer = null;
        flight = null;
        hotel = null;
        paymentStrategy = null;
    }

    @Test
    public void createBooking_ValidBooking_BookingAddedToList() {
        Booking booking = travelAgency.createBooking(customer, flight, hotel, paymentStrategy, "confermata");
        assertTrue(travelAgency.getAllBookings().contains(booking));
    }

    @Test
    public void cancelBooking_ExistingBooking_BookingRemovedFromList() {
        Booking booking = travelAgency.createBooking(customer, flight, hotel, paymentStrategy, "confermata");
        travelAgency.cancelBooking(booking);
        assertFalse(travelAgency.getAllBookings().contains(booking));
    }

    @Test
    public void getBookingsByCustomer_ValidCustomer_ReturnsCorrectBookings() {
        Booking booking1 = travelAgency.createBooking(customer, flight, hotel, paymentStrategy, "confermata");
        Booking booking2 = travelAgency.createBooking(new Customer("Jane Doe", "jane@example.com", "987654321", "paymentInfo"), flight, hotel, paymentStrategy, "confermata");

        List<Booking> bookings = travelAgency.getBookingsByCustomer(customer);

        assertEquals(1, bookings.size());
        assertTrue(bookings.contains(booking1));
        assertFalse(bookings.contains(booking2));
    }
}