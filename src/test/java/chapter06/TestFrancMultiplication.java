package chapter06;

import chapter04.TestMultiplication;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Objects;

public class TestFrancMultiplication {

    @Test
    @DisplayName("Franc 객체의 테스트 시작 ")
    public void testFrancMultiplication() {
        Franc five = new Franc(5);
        Assertions.assertThat(new Franc(10)).isEqualTo(five.times(2));
        Assertions.assertThat(new Franc(15)).isEqualTo(five.times(3));
    }

    @Test
    @DisplayName("있으면 좋을 것 같은 테스트 작성")
    public void testEquality() {
        Assertions.assertThat(new Dollar(10)).isEqualTo(new Dollar(10));
        Assertions.assertThat(new Dollar(15)).isEqualTo(new Dollar(15));
        Assertions.assertThat(new Franc(10)).isEqualTo(new Franc(10));
        Assertions.assertThat(new Franc(15)).isEqualTo(new Franc(15));
    }

    class Money {

        protected int amount;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Money money = (Money) o;
            return amount == money.amount;
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
