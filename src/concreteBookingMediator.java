public class concreteBookingMediator implements bookingMediator {
    private travelAgency travelAgency;

    public concreteBookingMediator(travelAgency travelAgency) {
        this.travelAgency = travelAgency;
    }

    public void setTravelAgency(travelAgency travelAgency) {
        this.travelAgency = travelAgency;
    }

    @Override
    public void bookFlightAndHotel(booking booking) {
        // Estrai i dettagli dalla prenotazione
        customer customer = booking.getCustomer();
        flight flight = booking.getFlight();
        hotel hotel = booking.getHotel();

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
    public void cancelBooking(booking booking) {
        // Stampa messaggio di cancellazione.
        System.out.println("Prenotazione annullata per " + booking.getCustomer().getName());

        // Invia conferma al cliente
        sendConfirmation(booking.getCustomer(), "prenotazione annullata.");

        // Aggiorna lo stato della prenotazione
        booking.setBookingStatus("Annullata");
    }

    private void sendConfirmation(customer customer, String message) {
        // Simula l'invio di una conferma al cliente
        System.out.println("Invio conferma a " + customer.getEmail() + ": " + message);
    }
}