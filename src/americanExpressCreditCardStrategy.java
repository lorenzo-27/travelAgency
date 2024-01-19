public class americanExpressCreditCardStrategy implements paymentStrategy {
    @Override
    public void pay(booking booking) {
        // Effettua il pagamento con carta di credito American Express
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