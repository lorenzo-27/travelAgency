public class VisaCreditCardStrategy implements PaymentStrategy {
    @Override
    public void pay(Booking booking) {
        // Effettua il pagamento con carta di credito Visa
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
