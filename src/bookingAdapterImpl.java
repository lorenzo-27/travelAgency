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
        return paymentInfo != null;
    }

    @Override
    public String getType() {
        // Restituisci il tipo di strategia di pagamento, se necessario
        if (booking.getStrategy() instanceof payPalStrategy) {
            return "PayPalStrategy";
        }
        else if (booking.getStrategy() instanceof visaCreditCardStrategy) {
            return "VisaCardStrategy";
        }
        else if (booking.getStrategy() instanceof americanExpressCreditCardStrategy) {
            return "AmericanExpressCardStrategy";
        }
        else if (booking.getStrategy() instanceof masterCardCreditCardStrategy) {
            return "MasterCardCardStrategy";
        }
        else {
            return "UnknownStrategy";
        }
    }
}
