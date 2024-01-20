public class bookingAdapterImpl implements bookingAdapter {
    private booking booking;

    public bookingAdapterImpl(booking booking) {
        this.booking = booking;
    }

    @Override
    public void pay(booking booking) {
        // Utilizza l'oggetto Booking per effettuare il pagamento
        booking.pay();
    }

    @Override
    public boolean isValid(String paymentInfo) {
        // Verifica se le informazioni di pagamento sono valide
        return paymentInfo != null;
    }

    @Override
    public String getType() {
        // Restituisci il tipo di strategia di pagamento, se necessario
        return booking.getStrategy().getType();
    }
}
