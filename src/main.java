// TODO: check all final methods

import java.time.LocalDateTime;

public class main {
    public static void main(String[] args) {
        // Crea un cliente
        customer customer = new customer("Mario Rossi", "mario.rossi@example.com", "1234567890");

        // Crea un volo
        flight flight = new flight("ABC123", "Ryanair", "FCO", "CPH", LocalDateTime.of(2023, 11, 20, 10, 00), LocalDateTime.of(2023, 11, 20, 16, 00), 3, 100, 20);

        // Crea un hotel
        hotel hotel = new hotel("ABC123", "Hotel California", "Via Roma 100, Firenze", "Firenze", "Italia", 5, 2);

        // Crea una strategia di pagamento
        paymentStrategy strategy = new payPalStrategy();

        // Crea un'agenzia di viaggi con un mediatore
        concreteBookingMediator mediator = new concreteBookingMediator(new travelAgency(null));
        travelAgency travelAgency = new travelAgency(mediator);

        // Associa il mediatore all'agenzia di viaggi
        mediator.setTravelAgency(travelAgency);
        //mediator.bookFlightAndHotel(new booking(customer, flight, hotel, strategy, mediator, "pending"));

        // Crea una prenotazione
        booking booking = travelAgency.createBooking(customer, flight, hotel, strategy, "pending");

        // Effettua il pagamento
        strategy.pay(booking);
        // System.out.println("Pagamento effettuato con " + booking.getStrategy());
    }
}
