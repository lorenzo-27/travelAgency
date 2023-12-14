public class BookingAdapterImpl implements BookingAdapter {
    private Booking booking;

    public BookingAdapterImpl(Booking booking) {
        this.booking = booking;
    }

    @Override
    public void pay(Booking booking) {
        // Utilizza l'oggetto Booking per effettuare il pagamento
        booking.pay();
    }

    @Override
    public boolean isValid(String paymentInfo) {
        // Implementa la logica di validità del pagamento, se necessario
        return true;
    }

    @Override
    public String getType() {
        // Restituisci il tipo di strategia di pagamento, se necessario
        return "BookingAdapter";
    }
}
