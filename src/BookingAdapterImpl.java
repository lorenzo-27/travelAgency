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
        return paymentInfo != null;
    }

    @Override
    public String getType() {
        // Restituisci il tipo di strategia di pagamento, se necessario
        if (booking.getStrategy() instanceof PayPalStrategy) {
            return "PayPalStrategy";
        }
        else if (booking.getStrategy() instanceof VisaCreditCardStrategy) {
            return "VisaCardStrategy";
        }
        else if (booking.getStrategy() instanceof AmericanExpressCreditCardStrategy) {
            return "AmericanExpressCardStrategy";
        }
        else if (booking.getStrategy() instanceof MasterCardCreditCardStrategy) {
            return "MasterCardCardStrategy";
        }
        else {
            return "UnknownStrategy";
        }
    }
}
