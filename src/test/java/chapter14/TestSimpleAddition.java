package chapter14;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Hashtable;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class TestSimpleAddition {

    @Test
    @DisplayName("2프랑을 달려로 바꾸고 싶다~")
    public void testReduceMoneyDiffCurrency(){
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Money result = bank.reduce(Money.franc(2), "USD");
        assertThat(Money.dollar(1)).isEqualTo(result);
    }

    @Test
    @DisplayName("2프랑을 달려로 바꾸고 싶다~")
    public void testIdentityRate() {
        assertThat(1).isEqualTo(new Bank().rate("USD", "USD"));
    }

    private class Pair {
        private String from;
        private String to;

        public Pair(String from, String to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return Objects.equals(from, pair.from) &&
                    Objects.equals(to, pair.to);
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }
    }

    static class Sum implements Expression {
        Money augend;
        Money addend;

        public Sum(Money augend, Money addend) {
            this.augend = augend;
            this.addend = addend;
        }

        @Override
        public Money reduce(Bank bank, String to) {
            int amount = augend.amount + addend.amount;
            return new Money(amount, to);
        }
    }

    interface Expression {
        Money reduce(Bank bank, String to);
    }

    class Bank {
        private Hashtable rates = new Hashtable();

        void addRate(String from, String to, int rate) {
            rates.put(new Pair(from, to), new Integer(rate));
        }

        public Money reduce(Expression source, String to) {
            return source.reduce(this, to);
        }

        int rate(String from, String to) {
            if(from.equals(to)) return 1;
            Integer rate = (Integer) rates.get(new Pair(from, to));
            return rate.intValue();
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

        @Override
        public Money reduce(Bank bank, String to) {
            int rate = bank.rate(currency, to);
            return new Money(amount / rate, to);
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
