package chapter09;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.assertj.core.api.Assertions.*;

public class TestFrancMultiplication {

    @Test
    @DisplayName("통화 개념을 어떻게 테스트 해야할까?")
    public void testCurrency() {
        assertThat("USD").isEqualTo(Money.dollar(1).currency);
        assertThat("CHF").isEqualTo(Money.franc(1).currency);
    }

    abstract static class Money {
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

        abstract Money times(int multiplier);

        public String currency() {
            return currency;
        }

        @Override
        public boolean equals(Object o) {
            Money money = (Money) o;
            return amount == money.amount
                    && getClass().getSuperclass().equals(money.getClass().getSuperclass());
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

        public Money times(int multiplier) {
            return Money.dollar(amount * multiplier);
        }
    }

    static class Franc extends Money {

        Franc(int amount, String currency) {
            super(amount, currency);
        }

        public Money times(int multiplier) {
            return Money.franc(amount * multiplier);
        }
    }
}
