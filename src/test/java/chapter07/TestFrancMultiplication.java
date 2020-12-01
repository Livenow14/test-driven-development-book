package chapter07;

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

    class Money {

        protected int amount;

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

    class Dollar extends Money {

        Dollar(int amount) {
            this.amount = amount;
        }

        public Dollar times(int multiplier) {
            return new Dollar(amount * multiplier);
        }
    }

    class Franc extends Money {

        Franc(int amount) {
            this.amount = amount;
        }

        public Franc times(int multiplier) {
            return new Franc(amount * multiplier);
        }
    }
}
