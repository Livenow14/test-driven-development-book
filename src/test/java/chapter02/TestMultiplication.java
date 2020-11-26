package chapter02;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestMultiplication {

    @Test
    @DisplayName("이렇게 되길 원한다. 하지만 틀림")
    public void testMultiplication() {
        Dollar five = new Dollar(5);
        five.times(2);
        Assertions.assertThat(10).isEqualTo(five.amount);
        five.times(3);
        Assertions.assertThat(15).isEqualTo(five.amount);
    }

    @Test
    @DisplayName("Doal.times()를 수정한다")
    public void testMultiplication2() {
        Dollar five = new Dollar(5);
        Dollar product = five.times(2);
        Assertions.assertThat(10).isEqualTo(product.amount);
        product = five.times(3);
        Assertions.assertThat(15).isEqualTo(product.amount);
    }


    class Dollar {
        int amount;

        Dollar(int amount) {
            this.amount = amount;
        }

        public Dollar times(int multiplier) {
/*            this.amount *= multiplier;
            return null;*/

            return new Dollar(amount * multiplier);
        }
    }
}
