package chapter08;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Objects;

public class TestFrancMultiplication {

    @Test
    @DisplayName("Franc 객체와 Dollar 객체 비교할때 에러가 발생한다 ")
    public void testEquality() {
        Assertions.assertThat(new Dollar(10)).isEqualTo(new Dollar(10));
        Assertions.assertThat(new Dollar(15)).isEqualTo(new Dollar(15));
        Assertions.assertThat(new Franc(10)).isEqualTo(new Franc(10));
        Assertions.assertThat(new Franc(15)).isEqualTo(new Franc(15));
        Assertions.assertThat(new Franc(5)).isEqualTo(new Dollar(5));
    }

    @Test
    @DisplayName("팩토리 메소드를 만든다.  ")
    public void testFactory() {
        Money five = Money.dollar(5);
        Assertions.assertThat(new Franc(10)).isEqualTo(five.times(2));
        Assertions.assertThat(new Franc(15)).isEqualTo(five.times(3));
    }

    @Test
    @DisplayName("팩토리 메소드로 나머지 테스트 확인.  ")
    public void testFactoryMultiplication() {
        Money five = Money.dollar(5);
        Assertions.assertThat(Money.dollar(10)).isEqualTo(five.times(2));
        Assertions.assertThat(Money.dollar(15)).isEqualTo(five.times(3));
    }
    abstract static class Money {

        protected int amount;

        public static Money dollar(int amount) {
            return new Dollar(amount);
        }

        public static Money franc(int amount) {
            return new Franc(amount);
        }

        abstract Money times(int multiplier);

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

        Dollar(int amount) {
            this.amount = amount;
        }

        public Money times(int multiplier) {
            return new Dollar(amount * multiplier);
        }
    }

    static class Franc extends Money {

        Franc(int amount) {
            this.amount = amount;
        }

        public Money times(int multiplier) {
            return new Franc(amount * multiplier);
        }
    }
}
