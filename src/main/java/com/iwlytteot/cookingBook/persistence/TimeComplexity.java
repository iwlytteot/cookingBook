package com.iwlytteot.cookingBook.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Embeddable;
import java.time.LocalTime;

@Embeddable
@Data
@AllArgsConstructor
public class TimeComplexity {
    public TimeComplexity() {}
    /**
     * Represents base (approximate) time
     * e.g.: 1h
     */
    private LocalTime baseEstimate;

    /**
     * Helps to make range
     * e.g.: baseEstimate = 1h, additionalEstimate = 20m, therefore "1h to 1h20m"
     */
    private LocalTime additionalEstimate;

}
