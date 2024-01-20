import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        // Creazione di voli, hotel e clienti
        Scanner scanner = new Scanner(System.in);

        System.out.print("Inserisci il numero di voli: ");
        int numFlights = scanner.nextInt();

        System.out.print("Inserisci il numero di hotel: ");
        int numHotels = scanner.nextInt();

        System.out.print("Inserisci il numero di clienti: ");
        int numCustomers = scanner.nextInt();

        System.out.print("Inserisci il numero di prenotazioni: ");
        int numBookings = scanner.nextInt();

        // Creazione di voli, hotel e clienti
        List<flight> flights = createFlights(numFlights);
        List<hotel> hotels = createHotels(numHotels);
        List<customer> customers = createCustomers(numCustomers);

        // Creazione di un oggetto ConcreteBookingMediator
        concreteBookingMediator mediator = new concreteBookingMediator(new travelAgency(null));

        // Creazione di prenotazioni casuali per i clienti
        Random random = new Random();
        for (int i = 0; i < numBookings; i++) {
            customer customer = customers.get(random.nextInt(customers.size()));
            flight flight = flights.get(random.nextInt(flights.size()));
            hotel hotel = hotels.get(random.nextInt(hotels.size()));
            paymentStrategy paymentStrategy = getRandomPaymentStrategy();

            // Crea una prenotazione
            booking booking = mediator.createBooking(customer, flight, hotel, paymentStrategy, "pending");

            // Esegui il pagamento e gestisci la conferma/annullamento
            paymentStrategy.pay(booking);
        }
        scanner.close();
    }

    // Creazione di voli e hotel
    private static List<flight> createFlights(int numFlights) {
        List<flight> flights = new ArrayList<>();
        for (int i = 0; i < numFlights; i++) {
            flights.add(new flight("F" + (i + 1), "Airline" + (i + 1), "Departure" + (i + 1),
                    "Arrival" + (i + 1), LocalDateTime.now(), LocalDateTime.now().plusHours(2),
                    120, 300, 20));
        }
        return flights;
    }

    private static List<hotel> createHotels(int numHotels) {
        List<hotel> hotels = new ArrayList<>();
        for (int i = 0; i < numHotels; i++) {
            hotels.add(new hotel("H" + (i + 1), "Hotel" + (i + 1), "Address" + (i + 1),
                    "City" + (i + 1), "Country" + (i + 1), i + 1, 5));
        }
        return hotels;
    }


    // Creazione di clienti
    private static List<customer> createCustomers(int numCustomers) {
        List<customer> customers = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numCustomers; i++) {
            String paymentInfo;
            int paymentType = random.nextInt(4);

            switch (paymentType) {
                case 0:
                    // PayPal
                    paymentInfo = "email@techadron.com";
                    break;
                case 1:
                    // Visa
                    paymentInfo = "4" + generateRandomDigits(16); // Visa card starts with 4
                    break;
                case 2:
                    // AmericanExpress
                    paymentInfo = generateAmericanExpressNumber(); // AmericanExpress card starts with 37 or 34
                    break;
                case 3:
                    // MasterCard
                    String masterCardPrefix = "5" + (random.nextInt(3) + 1); // Generates 1, 2, or 3
                    paymentInfo = masterCardPrefix + generateRandomDigits(15); // MasterCard card starts with 51, 52, or 53
                    break;
                default:
                    paymentInfo = "";
            }

            customers.add(new customer("Customer" + (i + 1), "email" + (i + 1) + "@techadron.com",
                    "123456789", paymentInfo));
        }

        return customers;
    }

    private static String generateAmericanExpressNumber() {
        Random random = new Random();
        // Choose between 34 or 37 as the starting digits for AmericanExpress
        String startingDigits = random.nextBoolean() ? "34" : "37";
        return startingDigits + generateRandomDigits(14); // AmericanExpress card has a total length of 15 digits
    }

    private static String generateRandomDigits(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length - 1; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    // Restituisce casualmente una delle implementazioni di PaymentStrategy
    private static paymentStrategy getRandomPaymentStrategy() {
        Random random = new Random();
        switch (random.nextInt(4)) {
            case 0:
                return new payPalStrategy();
            case 1:
                return new visaCreditCardStrategy();
            case 2:
                return new americanExpressCreditCardStrategy();
            default:
                return new masterCardCreditCardStrategy();
        }
    }

}