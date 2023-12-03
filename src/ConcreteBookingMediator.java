public class ConcreteBookingMediator implements BookingMediator {
    private TravelAgency travelAgency;

    public ConcreteBookingMediator(TravelAgency travelAgency) {
        this.travelAgency = travelAgency;
    }

    @Override
    public void bookFlightAndHotel(Booking booking) {
        // Logica per coordinare la prenotazione del volo e dell'hotel
        // Utilizzando Flight, Hotel, Customer, e altri dettagli da Booking.
        // Ad esempio, puoi inviare conferme o aggiornamenti alle parti interessate.

        // Esempio: Stampa un messaggio di conferma.
        System.out.println("Prenotazione confermata per " + booking.getCustomer().getName());
    }

    @Override
    public void cancelBooking(Booking booking) {
        // Logica per annullare la prenotazione
        // Può coinvolgere l'invio di notifiche o l'aggiornamento di altri sistemi.

        // Esempio: Stampa un messaggio di cancellazione.
        System.out.println("Prenotazione annullata per " + booking.getCustomer().getName());
    }
}