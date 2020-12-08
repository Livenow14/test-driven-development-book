package chapter13;

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

    @Test
    @DisplayName("플러스는 Sum을 반환해야 한다. ")
    public void testPlusReturnsSum() {
        Money five = Money.dollar(5);
        Expression result = five.plus(five);
        Sum sum = (Sum) result;
        assertThat(five).isEqualTo(sum.augend);
        assertThat(five).isEqualTo(sum.addend);
    }

    @Test
    @DisplayName("reduce 테스트 ")
    public void testReduceMoney() {
        Bank bank = new Bank();
        Money result = bank.reduce(Money.dollar(1), "USD");
        assertThat(Money.dollar(1)).isEqualTo(result);
    }

    static class Sum implements Expression {
        Money augend;
        Money addend;

        public Sum(Money augend, Money addend) {
            this.augend = augend;
            this.addend = addend;
        }

        public Money reduce(String to) {
            int amount = augend.amount + addend.amount
            return new Money(amount, to);
        }
    }

    interface Expression {
        Money reduce(String to);
    }

    class Bank {
        public Money reduce(Expression source, String to) {
            return source.reduce(to);
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

        public Expression plus(Money addend) {
            return new Sum(this, addend);
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

        public Money reduce(String to){
            return this;
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
