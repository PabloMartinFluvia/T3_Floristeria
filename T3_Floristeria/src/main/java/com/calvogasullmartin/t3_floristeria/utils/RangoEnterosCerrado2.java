package com.calvogasullmartin.t3_floristeria.utils;

public class RangoEnterosCerrado2 {

    private int min;

    private int max;

    public RangoEnterosCerrado2(int min, int max) {
        assert min <= max;
        this.min = min;
        this.max = max;
    }

    public boolean includes(int value) {
        return min <= value && value <= max;
    }

    int getMin() {
        return min;
    }

    int getMax() {
        return max;
    }

}
