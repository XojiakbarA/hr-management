package uz.pdp.hrmanagement.entity.enums;

import lombok.Getter;

@Getter
public enum Grade {
    A(10_000_000), B(7_000_000), C(5_000_000), D(3_000_000);

    Grade(Integer value) {
        this.value = value;
    }

    private final Integer value;
}
