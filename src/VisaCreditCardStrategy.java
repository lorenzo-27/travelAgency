import java.util.Objects;

public class VisaCreditCardStrategy implements PaymentStrategy {

    private static final double VISA_COMMISSION_RATE = 0.08; // 8% commissione di Visa

    @Override
    public void pay(Booking booking) {
        // Simula il processo di pagamento con Visa
        if (!isValid(booking.getCustomer().getPaymentInfo()) && Objects.equals(booking.getBookingStatus(), "confermata")) {
            String customerEmail = booking.getCustomer().getEmail();
            int totalPrice = booking.calculateTotalPrice(booking);

            // Applica la commissione PayPal
            double commission = totalPrice * VISA_COMMISSION_RATE;
            double totalAmount = totalPrice + commission;

            // Esegue il pagamento
            System.out.println("Effettuato il pagamento di €" + totalAmount + " con Visa per l'utente " + customerEmail);
            System.out.println("Commissioni Visa: €" + commission);
        }
        else {
            System.out.println("Informazioni di pagamento non valide!");
            booking.getMediator().cancelBooking(booking);
        }
    }

    @Override
    public boolean isValid(String creditCardNumber) {
        return creditCardNumber.startsWith("4") && creditCardNumber.length() == 16;
    }

    @Override
    public String getType() {
        return "Visa";
    }
}
