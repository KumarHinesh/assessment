package com.assessment.model;

import com.assessment.enums.ItemCategory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemTest {

    @Test
    public void testItemFields() {
        // Arrange
        String name = "Laptop";
        ItemCategory category = ItemCategory.ELECTRONICS;
        double price = 1200.0;

        // Act
        Item item = new Item(name, category, price);

        // Assert
        assertEquals(name, item.getName());
        assertEquals(category, item.getCategory());
        assertEquals(price, item.getPrice(), 0.001);

    }

    @Test
    public void testSetterAndGetter() {
        // Arrange
        String name = "Laptop";
        ItemCategory category = ItemCategory.ELECTRONICS;
        double price = 1200.0;

        // Act
        Item item = new Item();

        item.setName(name);
        item.setCategory(category);
        item.setPrice(price);

        // Assert
        assertEquals(name, item.getName());
        assertEquals(category, item.getCategory());
        assertEquals(price, item.getPrice(), 0.001);

    }

}
