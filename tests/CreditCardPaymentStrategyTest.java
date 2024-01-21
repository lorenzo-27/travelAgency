import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class CreditCardPaymentStrategyTest {

    private Booking booking;
    @Before
    public void setUp() throws Exception {
        Customer customer = new Customer("John Doe", "john@example.com", "123456789", "valid_payment_info");
        Flight flight = new Flight("F001", "Airline", "Departure", "Arrival", null, null, 2, 100, 100);
        Hotel hotel = new Hotel("H001", "Hotel", "Address", "City", "Country", 5, 100);

        booking = new Booking(customer, flight, hotel, null, new ConcreteBookingMediator(), "pending");
    }

    @After
    public void tearDown() throws Exception {
        booking = null;
    }

    @Test
    public void pay_ValidPaymentInfoAndConfirmedBooking_ProcessesPayment() {
        // Mock the mediator to avoid actual cancellations
        ConcreteBookingMediator mockMediator = Mockito.mock(ConcreteBookingMediator.class);
        booking.setMediator(mockMediator);

        CreditCardPaymentStrategy strategy = new VisaCreditCardStrategy();
        booking.setStrategy(strategy);
        booking.setBookingStatus("confermata");

        strategy.pay(booking);
    }

    @Test
    public void pay_InvalidPaymentInfo_CancelsBooking() {
        CreditCardPaymentStrategy strategy = new MasterCardCreditCardStrategy();
        booking.setStrategy(strategy);
        booking.getCustomer().setPaymentInfo("invalid_payment_info");

        strategy.pay(booking);

        assertEquals("annullata", booking.getBookingStatus());
    }

    @Test
    public void pay_UnconfirmedBooking_CancelsBooking() {
        CreditCardPaymentStrategy strategy = new AmericanExpressCreditCardStrategy();
        booking.setBookingStatus("non confermata");

        strategy.pay(booking);

        assertEquals("annullata", booking.getBookingStatus());
    }

    // Test per VisaCreditCardStrategy
    @Test
    public void testVisaPayment() {
        VisaCreditCardStrategy visaStrategy = new VisaCreditCardStrategy();

        assertTrue(visaStrategy.isValid("4111111111111111"));
        assertFalse(visaStrategy.isValid("1234567890123456"));

        assertEquals("Visa", visaStrategy.getType());
    }

    // Test per MasterCardCreditCardStrategy
    @Test
    public void testMasterCardPayment() {
        MasterCardCreditCardStrategy masterCardStrategy = new MasterCardCreditCardStrategy();

        assertTrue(masterCardStrategy.isValid("5111111111111111"));
        assertFalse(masterCardStrategy.isValid("1234567890123456"));

        assertEquals("MasterCard", masterCardStrategy.getType());
    }

    // Test per AmericanExpressCreditCardStrategy
    @Test
    public void testAmericanExpressPayment() {
        AmericanExpressCreditCardStrategy amexStrategy = new AmericanExpressCreditCardStrategy();

        assertTrue(amexStrategy.isValid("378282246310005"));
        assertFalse(amexStrategy.isValid("123456789012345"));

        assertEquals("AmericanExpress", amexStrategy.getType());
    }

    // Altri test per CreditCardPaymentStrategy
    @Test
    public void testInvalidPayment() {
        CreditCardPaymentStrategy invalidStrategy = new CreditCardPaymentStrategy() {
            @Override
            protected double getCommissionRate() {
                return 0;
            }

            @Override
            protected void processPayment(double totalAmount, double commission, String customerEmail) {
                // Non fa nulla in quanto è un test
            }

            @Override
            protected String getCardNumberPattern() {
                return "\\d+"; // Qualsiasi numero è valido
            }

            @Override
            protected int getCardNumberLength() {
                return 16;
            }
        };

        assertFalse(invalidStrategy.isValid("invalidCardNumber"));
        assertEquals("", invalidStrategy.getType());
    }

    @Test
    public void testChangePaymentStrategy() {
        // Creazione dei componenti necessari per le prenotazioni
        Customer customer = new Customer("John Doe", "john.doe@example.com", "123456789", "valid_payment_info");
        Flight flight = new Flight("F001", "AirlineX", "AirportA", "AirportB", LocalDateTime.now(), LocalDateTime.now().plusHours(2), 120, 200, 100);
        Hotel hotel = new Hotel("H001", "Best Hotel", "123 Main St", "CityX", "CountryY", 4, 100);

        // Creazione delle strategie di pagamento
        PaymentStrategy initialStrategy = new MasterCardCreditCardStrategy();
        PaymentStrategy newStrategy = new VisaCreditCardStrategy();

        // Creazione delle prenotazioni
        Booking booking1 = new Booking(customer, flight, hotel, initialStrategy, new ConcreteBookingMediator(), "pending");
        Booking booking2 = new Booking(customer, flight, hotel, initialStrategy, new ConcreteBookingMediator(), "pending");

        // Verifica della strategia di pagamento iniziale
        assertEquals("MasterCard", booking1.getStrategy().getType());
        assertEquals("MasterCard", booking2.getStrategy().getType());

        // Cambio della strategia di pagamento per la prima prenotazione
        booking1.setStrategy(newStrategy);

        // Verifica che la strategia di pagamento sia stata cambiata correttamente
        assertEquals("Visa", booking1.getStrategy().getType());
        assertEquals("MasterCard", booking2.getStrategy().getType()); // La seconda prenotazione mantiene la strategia iniziale
    }

}