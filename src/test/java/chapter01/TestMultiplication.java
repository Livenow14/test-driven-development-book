package chapter01;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestMultiplication {

    @Test
    public void testMultiplication() {
        Dollar five = new Dollar(5);
        five.times(2);
        Assertions.assertThat(10).isEqualTo(five.amount);
    }

    class Dollar{
        int amount;

        Dollar(int amount) {
            this.amount = amount;
        }

        public void times(int multiplier) {
            this.amount *= multiplier;
        }

    }

}
