public class ConcreteBookingMediator implements BookingMediator {
    private TravelAgency travelAgency;

    public ConcreteBookingMediator(TravelAgency travelAgency) {
        this.travelAgency = travelAgency;
    }

    public void setTravelAgency(TravelAgency travelAgency) {
        this.travelAgency = travelAgency;
    }

    @Override
    public void bookFlightAndHotel(Booking booking) {
        // Estrai i dettagli dalla prenotazione
        Customer customer = booking.getCustomer();
        Flight flight = booking.getFlight();
        Hotel hotel = booking.getHotel();

        // Esegui la prenotazione del volo
        System.out.println("Prenotazione del volo con id " + flight.getId() + " per " + customer.getName());

        // Esegui la prenotazione dell'hotel
        System.out.println("Prenotazione dell'hotel " + hotel.getName() + " per " + customer.getName());

        // Invia conferma al cliente
        sendConfirmation(customer, "prenotazione confermata per volo e hotel.");

        // Aggiorna lo stato della prenotazione
        booking.setBookingStatus("Confermata");
    }

    @Override
    public void cancelBooking(Booking booking) {
        // Stampa messaggio di cancellazione.
        System.out.println("Prenotazione annullata per " + booking.getCustomer().getName());

        // Invia conferma al cliente
        sendConfirmation(booking.getCustomer(), "prenotazione annullata.");

        // Aggiorna lo stato della prenotazione
        booking.setBookingStatus("Annullata");
    }

    private void sendConfirmation(Customer customer, String message) {
        // Simula l'invio di una conferma al cliente
        System.out.println("Invio conferma a " + customer.getEmail() + ": " + message);
    }
}