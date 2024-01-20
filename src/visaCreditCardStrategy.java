public class visaCreditCardStrategy implements paymentStrategy {

    private static final double VISA_COMMISSION_RATE = 0.08; // 8% commissione di Visa

    @Override
    public void pay(booking booking) {
        // Simula il processo di pagamento con Visa
        String customerEmail = booking.getCustomer().getEmail();
        int totalPrice = calculateTotalPrice(booking);

        // Applica la commissione PayPal
        double commission = totalPrice * VISA_COMMISSION_RATE;
        double totalAmount = totalPrice + commission;

        // Esegue il pagamento
        System.out.println("Effettuato il pagamento di €" + totalAmount + " con Visa per l'utente " + customerEmail);
        System.out.println("Commissioni Visa: €" + commission);
    }

    private int calculateTotalPrice(booking booking) {
        // Simula il calcolo del prezzo totale della prenotazione
        int flightPrice = booking.getFlight().getPrice();
        int hotelPrice = booking.getHotel().getPrice();
        return flightPrice + hotelPrice;
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
