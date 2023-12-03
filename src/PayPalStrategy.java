public class PayPalStrategy implements PaymentStrategy {

    @Override
    public void pay(Booking booking) {
        // Effettua il pagamento con PayPal
    }

    public boolean isValid(String paymentInfo) {
        return paymentInfo.contains("@");
    }

    @Override
    public String getType() {
        return "PayPal";
    }

}
