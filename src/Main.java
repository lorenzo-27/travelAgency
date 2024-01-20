import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
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
        List<Flight> Flights = createFlights(numFlights);
        List<Hotel> Hotels = createHotels(numHotels);
        List<Customer> Customers = createCustomers(numCustomers);

        // Creazione di un oggetto ConcreteBookingMediator
        ConcreteBookingMediator mediator = new ConcreteBookingMediator(new TravelAgency(null));

        // Creazione di prenotazioni casuali per i clienti
        Random random = new Random();
        for (int i = 0; i < numBookings; i++) {
            Customer customer = Customers.get(random.nextInt(Customers.size()));
            Flight flight = Flights.get(random.nextInt(Flights.size()));
            Hotel hotel = Hotels.get(random.nextInt(Hotels.size()));
            PaymentStrategy paymentStrategy = getRandomPaymentStrategy();

            // Crea una prenotazione
            Booking booking = mediator.createBooking(customer, flight, hotel, paymentStrategy, "pending");

            // Esegui il pagamento e gestisci la conferma/annullamento
            paymentStrategy.pay(booking);
        }
        scanner.close();

        // TODO: inserire funzioalità della classi travelAgency e bookingAdapterImpl
    }

    // Creazione di voli e hotel
    private static List<Flight> createFlights(int numFlights) {
        List<Flight> Flights = new ArrayList<>();
        for (int i = 0; i < numFlights; i++) {
            Flights.add(new Flight("F" + (i + 1), "Airline" + (i + 1), "Departure" + (i + 1),
                    "Arrival" + (i + 1), LocalDateTime.now(), LocalDateTime.now().plusHours(2),
                    120, 300, 20));
        }
        return Flights;
    }

    private static List<Hotel> createHotels(int numHotels) {
        List<Hotel> Hotels = new ArrayList<>();
        for (int i = 0; i < numHotels; i++) {
            Hotels.add(new Hotel("H" + (i + 1), "Hotel" + (i + 1), "Address" + (i + 1),
                    "City" + (i + 1), "Country" + (i + 1), i + 1, 5));
        }
        return Hotels;
    }


    // Creazione di clienti
    private static List<Customer> createCustomers(int numCustomers) {
        List<Customer> Customers = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numCustomers; i++) {
            String paymentInfo;
            int paymentType = random.nextInt(4);

            paymentInfo = switch (paymentType) {
                case 0 ->
                    // PayPal
                        "email@techadron.com";
                case 1 ->
                    // Visa
                        "4" + generateRandomDigits(16); // Visa card starts with 4
                case 2 ->
                    // AmericanExpress
                        generateAmericanExpressNumber(); // AmericanExpress card starts with 37 or 34
                case 3 -> {
                    // MasterCard
                    String masterCardPrefix = "5" + (random.nextInt(3) + 1); // Generates 1, 2, or 3
                    yield masterCardPrefix + generateRandomDigits(15); // MasterCard card starts with 51, 52, or 53
                }
                default -> "";
            };

            Customers.add(new Customer("Customer" + (i + 1), "email" + (i + 1) + "@techadron.com",
                    "123456789", paymentInfo));
        }

        return Customers;
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
    private static PaymentStrategy getRandomPaymentStrategy() {
        Random random = new Random();
        return switch (random.nextInt(4)) {
            case 0 -> new PayPalStrategy();
            case 1 -> new VisaCreditCardStrategy();
            case 2 -> new AmericanExpressCreditCardStrategy();
            default -> new MasterCardCreditCardStrategy();
        };
    }

}