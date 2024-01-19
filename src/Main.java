import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        // Crea un cliente
        Customer customer = new Customer("Mario Rossi", "mario.rossi@example.com", "1234567890");

        // Crea un volo
        Flight flight = new Flight("ABC123", "Ryanair", "FCO", "CPH", LocalDateTime.of(2023, 11, 20, 10, 00), LocalDateTime.of(2023, 11, 20, 16, 00), 3, 100);

        // Crea un hotel
        Hotel hotel = new Hotel("ABC123", "Hotel California", "Via Roma 100, Firenze", "Firenze", "Italia", 5);

        // Crea una strategia di pagamento
        PaymentStrategy strategy = new PayPalStrategy();

        // Crea un'agenzia di viaggi con un mediatore
        ConcreteBookingMediator mediator = new ConcreteBookingMediator(new TravelAgency(null));
        TravelAgency travelAgency = new TravelAgency(mediator);

        // Associa il mediatore all'agenzia di viaggi
        mediator.setTravelAgency(travelAgency);
        mediator.bookFlightAndHotel(new Booking(customer, flight, hotel, strategy, mediator, "pending"));

        // Crea una prenotazione
        Booking booking = travelAgency.createBooking(customer, flight, hotel, strategy, "pending");

        // Effettua il pagamento
        strategy.pay(booking);
        // System.out.println("Pagamento effettuato con " + booking.getStrategy());
    }
}
