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
        sendConfirmation(customer, "Prenotazione confermata per volo e hotel.");

        // Aggiorna lo stato della prenotazione
        booking.setBookingStatus("Confermata");
    }

    @Override
    public void cancelBooking(Booking booking) {
        // Logica per annullare la prenotazione
        // Può coinvolgere l'invio di notifiche o l'aggiornamento di altri sistemi.

        // Esempio: Stampa un messaggio di cancellazione.
        System.out.println("Prenotazione annullata per " + booking.getCustomer().getName());
    }

    private void sendConfirmation(Customer customer, String message) {
        // Simula l'invio di una conferma al cliente
        System.out.println("Invio conferma a " + customer.getEmail() + ": " + message);
    }
}