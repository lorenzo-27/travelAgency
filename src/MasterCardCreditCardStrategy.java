import java.util.Objects;

public class MasterCardCreditCardStrategy extends CreditCardPaymentStrategy {
    private static final double MASTERCARD_COMMISSION_RATE = 0.03; // 3% commissione di MasterCard

    @Override
    protected double getCommissionRate() {
        return MASTERCARD_COMMISSION_RATE;
    }

    @Override
    protected void processPayment(double totalAmount, double commission, String customerEmail) {
        System.out.println("Effettuare il pagamento di €" + totalAmount + " con MasterCard per l'utente " + customerEmail);
        System.out.println("Commissioni MasterCard: €" + commission);
    }

    @Override
    protected String getCardNumberPattern() {
        return "5[1-5][0-9]{14}";
    }

    @Override
    protected int getCardNumberLength() {
        return 16;
    }
}