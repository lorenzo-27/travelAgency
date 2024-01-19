public class payPalStrategy implements paymentStrategy {

    @Override
    public void pay(booking booking) {
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
