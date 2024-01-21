import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Mockito;

import static org.junit.Assert.*;

public class PayPalStrategyTest {

    private PayPalStrategy payPalStrategy;
    private Booking confirmedBooking;
    private Booking canceledBooking;

    @Before
    public void setUp() throws Exception {
        payPalStrategy = new PayPalStrategy();

        Customer customer = new Customer("John Doe", "john@example.com", "123456789", "john@example.com");
        Flight flight = new Flight("F123", "Airline", "Departure", "Arrival", null, null, 2, 100, 100);
        Hotel hotel = new Hotel("H456", "Hotel", "Address", "City", "Country", 3, 100);
        PaymentStrategy paymentStrategy = payPalStrategy;

        // Booking with valid payment info and confirmed status
        confirmedBooking = new Booking(customer, flight, hotel, paymentStrategy, new ConcreteBookingMediator(), "confermata");

        // Booking with valid payment info and canceled status
        canceledBooking = new Booking(customer, flight, hotel, paymentStrategy, new ConcreteBookingMediator(), "annullata");
    }

    @After
    public void tearDown() throws Exception {
        payPalStrategy = null;
        confirmedBooking = null;
        canceledBooking = null;
    }

    @Test
    public void pay_ValidPaymentInfoAndConfirmedBooking_SuccessfulPayment() {
        // Mock the mediator to avoid actual cancellations
        ConcreteBookingMediator mockMediator = Mockito.mock(ConcreteBookingMediator.class);
        confirmedBooking.setMediator(mockMediator);

        payPalStrategy.pay(confirmedBooking);

        // Verify that the payment was successful
        assertEquals("confermata", confirmedBooking.getBookingStatus());
    }

    @Test
    public void pay_ValidPaymentInfoAndCanceledBooking_CancellationAttempted() {
        // Mock the mediator to verify that the cancellation is attempted
        ConcreteBookingMediator mockMediator = Mockito.mock(ConcreteBookingMediator.class);
        canceledBooking.setMediator(mockMediator);

        payPalStrategy.pay(canceledBooking);

        // Verify that the payment info is valid, but the booking is canceled due to the status
        assertEquals("annullata", canceledBooking.getBookingStatus());
        Mockito.verify(mockMediator, Mockito.times(1)).cancelBooking(canceledBooking);
    }

    @Test
    public void pay_InvalidPaymentInfo_BookingCanceled() {
        Booking invalidBooking = new Booking(new Customer("Jane Doe", "jane@example.com", "987654321", "invalidPaymentInfo"),
                new Flight("F789", "Airline", "Departure", "Arrival", null, null, 2, 100, 100),
                new Hotel("H101", "Hotel", "Address", "City", "Country", 3, 100),
                payPalStrategy, new ConcreteBookingMediator(), "confermata");

        // Mock the mediator to verify that the cancellation is attempted
        ConcreteBookingMediator mockMediator = Mockito.mock(ConcreteBookingMediator.class);
        invalidBooking.setMediator(mockMediator);

        payPalStrategy.pay(invalidBooking);

        // Verify that the payment info is invalid, and the booking is canceled
        assertEquals("annullata", invalidBooking.getBookingStatus());
        Mockito.verify(mockMediator, Mockito.times(1)).cancelBooking(invalidBooking);
    }

    @Test
    public void isValid_ValidPaymentInfo_ReturnsTrue() {
        assertTrue(payPalStrategy.isValid("john@example.com"));
    }

    @Test
    public void isValid_InvalidPaymentInfo_ReturnsFalse() {
        assertFalse(payPalStrategy.isValid("invalidPaymentInfo"));
    }

    @Test
    public void getType_ReturnsPayPal() {
        assertEquals("PayPal", payPalStrategy.getType());
    }
}