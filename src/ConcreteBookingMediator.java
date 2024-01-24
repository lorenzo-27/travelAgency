import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConcreteBookingMediator implements BookingMediator {
    private final List<Booking> bookings;

    public ConcreteBookingMediator() {
        this.bookings = new ArrayList<>();
    }

    @Override
    public Booking createBooking(Customer customer, Flight flight, Hotel hotel, PaymentStrategy strategy, String bookingStatus) {
        Booking booking = new Booking(customer, flight, hotel, strategy, this, bookingStatus);

        if (flight.getNAvailableSeats() != 0 && hotel.getNAvailableRooms() != 0) {
            // Aggiorna i posti disponibili
            flight.decreaseNAvailableSeats();
            hotel.decreaseNAvailableRooms();

            // Aggiorna lo stato della prenotazione
            bookings.add(booking);
            booking.setBookingStatus("confermata");

            // Esegui la prenotazione del volo e dell'hotel
            System.out.println("Prenotazione del volo con id " + flight.getId() + " per " + customer.getName());
            System.out.println("Prenotazione dell'hotel " + hotel.getName() + " per " + customer.getName());

            // Invia conferma al cliente
            sendConfirmation(customer, "prenotazione confermata per volo e hotel. Pagamento in corso...");
        }
        else {
            // Aggiorna lo stato della prenotazione
            booking.setBookingStatus("annullata");

            // Invia conferma al cliente
            sendConfirmation(customer, "prenotazione non confermata (tutto esaurito).");
        }

        return booking;
    }

    @Override
    public void cancelBooking(Booking booking) {
        if (bookings.contains(booking) && Objects.equals(booking.getBookingStatus(), "annullata")) {
            System.out.println("La prenotazione è già stata annullata.");
        }
        else if (!bookings.contains(booking)) {
            System.out.println("La prenotazione non è stata trovata.");
        }
        else {
            // Estrae il volo e l'hotel dalla prenotazione
            Flight flight = booking.getFlight();
            Hotel hotel = booking.getHotel();

            // Aggiorna i posti disponibili
            flight.increaseNAvailableSeats();
            hotel.increaseNAvailableRooms();

            // Aggiorna lo stato della prenotazione
            bookings.remove(booking);
            booking.setBookingStatus("annullata");

            // Stampa messaggio di cancellazione.
            System.out.println("Prenotazione annullata per " + booking.getCustomer().getName() + ".");

            // Invia conferma al cliente
            sendConfirmation(booking.getCustomer(), "prenotazione annullata.");
        }
    }

    // Simula l'invio di una conferma al cliente
    private void sendConfirmation(Customer customer, String message) {
        System.out.println("Invio conferma a " + customer.getEmail() + ": " + message);
    }
}