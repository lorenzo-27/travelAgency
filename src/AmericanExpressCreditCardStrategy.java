import java.util.Objects;

public class AmericanExpressCreditCardStrategy extends CreditCardPaymentStrategy {
    private static final double AMERICAN_EXPRESS_COMMISSION_RATE = 0.04; // 4% commissione di American Express

    @Override
    protected double getCommissionRate() {
        return AMERICAN_EXPRESS_COMMISSION_RATE;
    }

    @Override
    protected void processPayment(double totalAmount, double commission, String customerEmail) {
        System.out.println("Effettuare il pagamento di €" + totalAmount + " con American Express per l'utente " + customerEmail);
        System.out.println("Commissioni American Express: €" + commission);
    }

    @Override
    protected String getCardNumberPattern() {
        return "3[47][0-9]{13}";
    }

    @Override
    protected int getCardNumberLength() {
        return 15;
    }
}