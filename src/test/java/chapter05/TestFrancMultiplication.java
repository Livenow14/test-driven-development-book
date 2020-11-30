package chapter05;

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

    class Franc {
        private int amount;

        Franc(int amount) {
            this.amount = amount;
        }

        public Franc times(int multiplier) {
            return new Franc(amount * multiplier);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Franc franc = (Franc) o;
            return amount == franc.amount;
        }

        @Override
        public int hashCode() {
            return Objects.hash(amount);
        }
    }
}
