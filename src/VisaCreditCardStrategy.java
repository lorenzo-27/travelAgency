import java.util.Objects;

public class VisaCreditCardStrategy extends CreditCardPaymentStrategy {
    private static final double VISA_COMMISSION_RATE = 0.03; // 3% commissione di Visa

    @Override
    protected double getCommissionRate() {
        return VISA_COMMISSION_RATE;
    }

    @Override
    protected void processPayment(double totalAmount, double commission, String customerEmail) {
        System.out.println("Effettuare il pagamento di €" + totalAmount + " con Visa per l'utente " + customerEmail);
        System.out.println("Commissioni Visa: €" + commission);
    }

    @Override
    protected String getCardNumberPattern() {
        return "4[0-9]{12}(?:[0-9]{3})?";
    }

    @Override
    protected int getCardNumberLength() {
        return 16;
    }
}