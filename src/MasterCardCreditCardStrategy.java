public class MasterCardCreditCardStrategy implements PaymentStrategy {
    @Override
    public void pay(Booking booking) {
        // Effettua il pagamento con carta di credito MasterCard
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
