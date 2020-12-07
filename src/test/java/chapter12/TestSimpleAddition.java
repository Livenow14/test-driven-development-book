package chapter12;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class TestSimpleAddition {
    
    @Test
    @DisplayName("가짜 객체 만들기")
    public void testSimpleAddition() {
        Money five = Money.dollar(5);
        Expression sum = five.plus(five);
        Bank bank = new Bank();
        Money reduced = bank.reduce(sum, "USD");
        assertThat(Money.dollar(10)).isEqualTo(reduced);
    }

    interface Expression{
    }

    class Bank{
        public Money reduce(Expression source, String to) {
            return Money.dollar(10);
        }
    }

    static class Money implements Expression {
        protected int amount;
        protected String currency;

        public Money(int amount, String currency) {
            this.amount = amount;
            this.currency = currency;
        }

        public static Money dollar(int amount) {
            return new Money(amount, "USD");
        }

        public static Money franc(int amount) {
            return new Money(amount, "CHF");
        }

        public Expression plus(Money addend){
            return new Money(amount + addend.amount, currency);
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
}
