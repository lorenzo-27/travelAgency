import java.util.Objects;

public class MasterCardCreditCardStrategy implements PaymentStrategy {

    private static final double MASTERCARD_COMMISSION_RATE = 0.02; // 2% commissione di MasterCard

    @Override
    public void pay(Booking booking) {
        // Simula il processo di pagamento con MasterCard
        if (isValid(booking.getCustomer().getPaymentInfo()) && Objects.equals(booking.getBookingStatus(), "confermata")) {
            String customerEmail = booking.getCustomer().getEmail();
            int totalPrice = booking.calculateTotalPrice(booking);

            // Applica la commissione MasterCard
            double commission = totalPrice * MASTERCARD_COMMISSION_RATE;
            double totalAmount = totalPrice + commission;

            // Esegue il pagamento
            System.out.println("Effettuato il pagamento di €" + totalAmount + " con MasterCard per l'utente " + customerEmail);
            System.out.println("Commissioni MasterCard: €" + commission);
        }
        else {
            System.out.println("Informazioni di pagamento non valide!");
            booking.getMediator().cancelBooking(booking);
        }
    }

    @Override
    public boolean isValid(String paymentInfo) {
        return (paymentInfo.startsWith("51") || paymentInfo.startsWith("52") || paymentInfo.startsWith("53") || paymentInfo.startsWith("54") || paymentInfo.startsWith("55")) && paymentInfo.length() == 16;
    }

    @Override
    public String getType() {
        return "MasterCard";
    }
}
