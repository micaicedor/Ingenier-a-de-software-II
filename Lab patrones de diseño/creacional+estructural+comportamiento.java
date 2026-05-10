// ================================================================
// PATRÓN STRATEGY (Comportamiento) - Algoritmos de descuento
// ================================================================

interface DiscountStrategy {
    double apply(double price);
}

class NoDiscount implements DiscountStrategy {
    @Override
    public double apply(double price) {
        return price;
    }
}

class SeasonDiscount implements DiscountStrategy {
    @Override
    public double apply(double price) {
        System.out.println("Descuento de temporada: 20%");
        return price * 0.80;
    }
}

class MemberDiscount implements DiscountStrategy {
    @Override
    public double apply(double price) {
        System.out.println("Descuento de membresía: 35%");
        return price * 0.65;
    }
}

// ================================================================
// PATRÓN ADAPTER (Estructural) - Sistema de pagos externo antiguo
// ================================================================

// Sistema externo antiguo que no podemos modificar
class OldPaymentSystem {
    public void makePayment(String type, int amountInCents) {
        System.out.println("[SISTEMA ANTIGUO] Procesando " + type +
            " por " + amountInCents + " centavos");
    }
}

// Interfaz moderna que usa nuestro código
interface ModernPayment {
    void pay(double amount);
}

// Adapter: traduce la interfaz antigua a la moderna
class PaymentAdapter implements ModernPayment {
    private OldPaymentSystem oldSystem;
    private String paymentType;

    public PaymentAdapter(OldPaymentSystem oldSystem, String paymentType) {
        this.oldSystem = oldSystem;
        this.paymentType = paymentType;
    }

    @Override
    public void pay(double amount) {
        int cents = (int)(amount * 100);
        oldSystem.makePayment(paymentType, cents);
    }
}

// ================================================================
// PATRÓN FACTORY METHOD (Creacional) - Fábrica de pagos
// ================================================================

abstract class Payment {
    protected DiscountStrategy discount;

    public Payment(DiscountStrategy discount) {
        this.discount = discount;
    }

    public void processPayment(double price) {
        double finalPrice = discount.apply(price);
        System.out.printf("Precio final: $%.2f%n", finalPrice);
        execute(finalPrice);
    }

    protected abstract void execute(double amount);
}

class CardPayment extends Payment {
    public CardPayment(DiscountStrategy discount) {
        super(discount);
    }

    @Override
    protected void execute(double amount) {
        System.out.printf("Pagando $%.2f con tarjeta de crédito%n", amount);
    }
}

class PayPalPayment extends Payment {
    public PayPalPayment(DiscountStrategy discount) {
        super(discount);
    }

    @Override
    protected void execute(double amount) {
        System.out.printf("Pagando $%.2f con PayPal%n", amount);
    }
}

// El sistema antiguo integrado via Adapter como un tipo de pago más
class LegacyPayment extends Payment {
    private ModernPayment adapter;

    public LegacyPayment(DiscountStrategy discount) {
        super(discount);
        this.adapter = new PaymentAdapter(new OldPaymentSystem(), "LEGACY");
    }

    @Override
    protected void execute(double amount) {
        System.out.println("Usando sistema de pagos antiguo via Adapter:");
        adapter.pay(amount);
    }
}

// La fábrica: decide qué tipo de pago crear
class PaymentFactory {
    public static Payment create(String type, DiscountStrategy discount) {
        switch (type) {
            case "card":    return new CardPayment(discount);
            case "paypal":  return new PayPalPayment(discount);
            case "legacy":  return new LegacyPayment(discount);
            default: throw new IllegalArgumentException("Tipo de pago desconocido: " + type);
        }
    }
}

// ================================================================
// MAIN
// ================================================================

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Pago normal con tarjeta ===");
        Payment p1 = PaymentFactory.create("card", new NoDiscount());
        p1.processPayment(100.00);

        System.out.println("\n=== Pago con PayPal y descuento de temporada ===");
        Payment p2 = PaymentFactory.create("paypal", new SeasonDiscount());
        p2.processPayment(100.00);

        System.out.println("\n=== Pago con sistema antiguo y descuento de membresía ===");
        Payment p3 = PaymentFactory.create("legacy", new MemberDiscount());
        p3.processPayment(100.00);
    }
}