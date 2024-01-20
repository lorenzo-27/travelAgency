import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class concreteBookingMediator implements bookingMediator {
    private List<booking> bookings;
    private travelAgency travelAgency;

    public concreteBookingMediator(travelAgency travelAgency) {
        this.travelAgency = travelAgency;
        this.bookings = new ArrayList<>();
    }

    @Override
    public booking createBooking(customer customer, flight flight, hotel hotel, paymentStrategy strategy, String bookingStatus) {
        booking booking = new booking(customer, flight, hotel, strategy, this, bookingStatus);

        if (flight.getNAvailableSeats() != 0 && hotel.getNAvailableRooms() != 0) {
            // Aggiorna i posti disponibili
            flight.decreaseNAvailableSeats();
            hotel.decreaseNAvailableRooms();

            // Aggiorna lo stato della prenotazione
            bookings.add(booking);
            booking.setBookingStatus("Confermata");

            // Esegui la prenotazione del volo e dell'hotel
            System.out.println("Prenotazione del volo con id " + flight.getId() + " per " + customer.getName());
            System.out.println("Prenotazione dell'hotel " + hotel.getName() + " per " + customer.getName());

            // Invia conferma al cliente
            sendConfirmation(customer, "prenotazione confermata per volo e hotel. Pagamento in corso...");
        }
        else {
            // Invia conferma al cliente
            if (flight.getNAvailableSeats() == 0) {
                sendConfirmation(customer, "prenotazione non confermata per volo (posti esauriti).");
            }
            else {
                sendConfirmation(customer, "prenotazione non confermata per hotel (camere esaurite).");
            }

            // Aggiorna lo stato della prenotazione
            booking.setBookingStatus("Annullata");
        }

        return booking;
    }

    @Override
    public void cancelBooking(booking booking) {
        if (bookings.contains(booking) && Objects.equals(booking.getBookingStatus(), "Annullata")) {
            System.out.println("La prenotazione è già stata annullata.");
        }
        else if (!bookings.contains(booking)) {
            System.out.println("La prenotazione non è stata trovata.");
        }
        else {
            // Estrae il volo e l'hotel dalla prenotazione
            flight flight = booking.getFlight();
            hotel hotel = booking.getHotel();

            // Aggiorna i posti disponibili
            flight.increaseNAvailableSeats();
            hotel.increaseNAvailableRooms();

            // Aggiorna lo stato della prenotazione
            bookings.remove(booking);
            booking.setBookingStatus("Annullata");

            // Stampa messaggio di cancellazione.
            System.out.println("Prenotazione annullata per " + booking.getCustomer().getName() + ".");

            // Invia conferma al cliente
            sendConfirmation(booking.getCustomer(), "prenotazione annullata.");
        }
    }

    private void sendConfirmation(customer customer, String message) {
        // Simula l'invio di una conferma al cliente
        System.out.println("Invio conferma a " + customer.getEmail() + ": " + message);
    }
}