import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class BookingTest {

    private Booking booking;

    @Before
    public void setUp() throws Exception {
        Customer customer = new Customer("John Doe", "john@example.com", "123456789", "paymentInfo");
        Flight flight = new Flight("F123", "Airline", "Departure", "Arrival", null, null, 2, 100, 100);
        Hotel hotel = new Hotel("H456", "Hotel", "Address", "City", "Country", 30, 100);
        PaymentStrategy paymentStrategy = Mockito.mock(PaymentStrategy.class);
        BookingMediator mediator = Mockito.mock(BookingMediator.class);

        booking = new Booking(customer, flight, hotel, paymentStrategy, mediator, "confermata");
    }

    @After
    public void tearDown() throws Exception {
        booking = null;
    }

    @Test
    public void setStrategy_ChangePaymentStrategy_SuccessfullyChanged() {
        PaymentStrategy originalStrategy = Mockito.mock(PaymentStrategy.class);
        PaymentStrategy newStrategy = Mockito.mock(PaymentStrategy.class);

        booking.setStrategy(originalStrategy);
        assertEquals(originalStrategy, booking.getStrategy());

        booking.setStrategy(newStrategy);
        assertSame(newStrategy, booking.getStrategy());
    }

    @Test
    public void setMediator_ChangeMediator_SuccessfullyChanged() {
        BookingMediator originalMediator = Mockito.mock(BookingMediator.class);
        BookingMediator newMediator = Mockito.mock(BookingMediator.class);

        booking.setMediator(originalMediator);
        assertEquals(originalMediator, booking.getMediator());

        booking.setMediator(newMediator);
        assertSame(newMediator, booking.getMediator());
    }

    @Test
    public void calculateTotalPrice_ValidBooking_CorrectTotalPrice() {
        int totalPrice = booking.calculateTotalPrice(booking);
        assertEquals(130, totalPrice); // Assuming hotel price is 30 and flight price is 100
    }
}