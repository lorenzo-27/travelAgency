import java.util.Objects;

public class AmericanExpressCreditCardStrategy implements PaymentStrategy {

    private static final double AMERICAN_EXPRESS_COMMISSION_RATE = 0.04; // 4% commissione di American Express

    @Override
    public void pay(Booking booking) {
        // Simula il processo di pagamento con American Express
        if (isValid(booking.getCustomer().getPaymentInfo()) && Objects.equals(booking.getBookingStatus(), "confermata")) {
            String customerEmail = booking.getCustomer().getEmail();
            int totalPrice = booking.calculateTotalPrice(booking);

            // Applica la commissione American Express
            double commission = totalPrice * AMERICAN_EXPRESS_COMMISSION_RATE;
            double totalAmount = totalPrice + commission;

            // Esegue il pagamento
            System.out.println("Effettuare il pagamento di €" + totalAmount + " con American Express per l'utente " + customerEmail);
            System.out.println("Commissioni American Express: €" + commission);
        }
        else {
            System.out.println("Informazioni di pagamento non valide!");
            booking.getMediator().cancelBooking(booking);
        }
    }

    @Override
    public boolean isValid(String creditCardNumber) {
        return creditCardNumber.startsWith("34") || creditCardNumber.startsWith("37") && creditCardNumber.length() == 15;
    }

    @Override
    public String getType() {
        return "American Express";
    }
}