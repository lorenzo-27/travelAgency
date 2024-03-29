import java.util.Objects;

public class PayPalStrategy implements PaymentStrategy {

    private static final double PAYPAL_COMMISSION_RATE = 0.02; // 2% commissione di PayPal

    @Override
    public void pay(Booking booking) {
        // Simula il processo di pagamento con PayPal
        if (isValid(booking.getCustomer().getPaymentInfo()) && Objects.equals(booking.getBookingStatus(), "confermata")) {
            String customerEmail = booking.getCustomer().getEmail();
            int totalPrice = booking.calculateTotalPrice(booking);

            // Applica la commissione PayPal
            double commission = totalPrice * PAYPAL_COMMISSION_RATE; // TODO: eliminare tutti i valori oltre la seconda cifra decimale
            double totalAmount = totalPrice + commission;

            // Esegue il pagamento
            System.out.println("Effettuato il pagamento di €" + totalAmount + " con PayPal per l'utente " + customerEmail + ".");
            System.out.println("Commissioni PayPal: €" + commission);
        }
        else {
            System.out.println("Informazioni di pagamento non valide!");
            booking.getMediator().cancelBooking(booking);
            booking.setBookingStatus("annullata");
        }
    }

    public boolean isValid(String paymentInfo) {
        return paymentInfo.contains("@");
    }

    @Override
    public String getType() {
        return "PayPal";
    }

}