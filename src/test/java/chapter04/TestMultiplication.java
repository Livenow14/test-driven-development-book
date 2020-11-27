package chapter04;

import chapter03.TestEquality;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Objects;

public class TestMultiplication {

    @Test
    @DisplayName("Doal.times()를 수정한다")
    public void testMultiplication() {
        Dollar five = new Dollar(5);
        Dollar product = five.times(2);
        Assertions.assertThat(10).isEqualTo(product.amount);
        product = five.times(3);
        Assertions.assertThat(15).isEqualTo(product.amount);
    }

    @Test
    @DisplayName("Dollar와 Dollar로 재정의")
    public void testMultiplication2() {
        Dollar five = new Dollar(5);
        Dollar product = five.times(2);
        Assertions.assertThat(new Dollar(10)).isEqualTo(product);
        product = five.times(3);
        Assertions.assertThat(new Dollar(15)).isEqualTo(product);
    }

    @Test
    @DisplayName("필요없는 변수 제거 가능 ")
    public void testMultiplication3() {
        Dollar five = new Dollar(5);
        Assertions.assertThat(new Dollar(10)).isEqualTo(five.times(2));
        Assertions.assertThat(new Dollar(15)).isEqualTo(five.times(3));
    }


    class Dollar {
        private int amount;

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
