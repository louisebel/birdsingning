package com.birdsinging.level.quiz;

import java.util.Objects;

/*
DDD
Value Object
 */
public class Choice {
    private final String value;

    public Choice(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Choice choice = (Choice) o;
        return Objects.equals(value, choice.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
