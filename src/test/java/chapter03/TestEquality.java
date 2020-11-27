package chapter03;

import chapter02.TestMultiplication;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;

public class TestEquality {

    @Test
    void testEquality() {
        Assertions.assertThat(new Dollar(5)).isEqualTo(new Dollar(5));
        Assertions.assertThat(new Dollar(5)).isEqualTo(new Dollar(6));
    }

    class Dollar {
        int amount;

        Dollar(int amount) {
            this.amount = amount;
        }

        public Dollar times(int multiplier) {
            return new Dollar(amount * multiplier);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Dollar dollar = (Dollar) o;
            return amount == dollar.amount;
        }

        @Override
        public int hashCode() {
            return Objects.hash(amount);
        }
    }
}
