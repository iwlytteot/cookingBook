package com.iwlytteot.cookingBook.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
@AllArgsConstructor
public class TimeComplexity {
    public TimeComplexity() {}
    /**
     * Represents base (approximate) time
     * e.g.: 1h
     */
    private String baseEstimate;

    /**
     * Helps to make range
     * e.g.: baseEstimate = 1h, additionalEstimate = 20m, therefore "1h to 1h20m"
     */
    private String additionalEstimate;

}
