package chapter10;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class TestFrancMultiplication {

    @Test
    @DisplayName("통화 개념을 어떻게 테스트 해야할까?")
    public void testCurrency() {
        assertThat("USD").isEqualTo(Money.dollar(1).currency);
        assertThat("CHF").isEqualTo(Money.franc(1).currency);
    }

    @Test
    @DisplayName("equals의 동작을 본다. ")
    public void testDifferentClassEquality() {
        assertThat(new Money(10, "CHF")).isEqualTo(new Franc(10, "CHF"));
    }

    static class Money {
        protected int amount;
        protected String currency;

        public Money(int amount, String currency) {
            this.amount = amount;
            this.currency = currency;
        }

        public static Money dollar(int amount) {
            return new Dollar(amount, "USD");
        }

        public static Money franc(int amount) {
            return new Franc(amount, "CHF");
        }

        public Money times(int amount, int multiplier) {
            return new Money(amount * multiplier, currency);
        }

        public String currency() {
            return currency;
        }

        public String toString() {
            return amount + " " + currency;
        }

        @Override
        public boolean equals(Object o) {
            Money money = (Money) o;
            return amount == money.amount
                    && currency().equals(money.currency());
        }

        @Override
        public int hashCode() {
            return Objects.hash(amount);
        }
    }

    static class Dollar extends Money {
        Dollar(int amount, String currency) {
            super(amount, currency);
        }
    }

    static class Franc extends Money {
        Franc(int amount, String currency) {
            super(amount, currency);
        }
    }
}
