package com.alaishat.mohammad.javalibrary.backend;

/**
 * Created by Mohammad Al-Aishat on Jun/10/2024.
 * Draw Your Paper Project.
 */

public class DataPaper {
    public DataPaper(char value, int height, int width) {
        this.value = value;
        this.height = height;
        this.width = width;
    }

    public char value;
    public int height;
    public int width;

    @Override
    public String toString() {
        return value + "[" + height +
                "," + width +
                ']';
    }
}

