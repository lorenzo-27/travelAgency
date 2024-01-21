import java.util.Objects;

public abstract class CreditCardPaymentStrategy implements PaymentStrategy {
    @Override
    public void pay(Booking booking) {
        if (isValid(booking.getCustomer().getPaymentInfo()) && Objects.equals(booking.getBookingStatus(), "confermata")) {
            String customerEmail = booking.getCustomer().getEmail();
            int totalPrice = booking.calculateTotalPrice(booking);

            double commission = totalPrice * getCommissionRate();
            double totalAmount = totalPrice + commission;

            processPayment(totalAmount, commission, customerEmail);
        } else {
            System.out.println("Informazioni di pagamento non valide!");
            booking.getMediator().cancelBooking(booking);
            booking.setBookingStatus("annullata");
        }
    }
    protected abstract double getCommissionRate();

    protected abstract void processPayment(double totalAmount, double commission, String customerEmail);

    @Override
    public String getType() {
        return getClass().getSimpleName().replace("CreditCardStrategy", "");
    }

    @Override
    public boolean isValid(String creditCardNumber) {
        return creditCardNumber.matches(getCardNumberPattern()) && creditCardNumber.length() == getCardNumberLength();
    }

    // Metodo astratto per ottenere il modello del numero della carta di credito
    protected abstract String getCardNumberPattern();

    // Metodo astratto per ottenere la lunghezza del numero della carta di credito
    protected abstract int getCardNumberLength();
}
