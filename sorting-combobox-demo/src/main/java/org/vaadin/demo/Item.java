package org.vaadin.demo;

/**
 * Created by Vilkas on 11/03/2018.
 */
public class Item {
    private Long id;
    private String description;

    public Item() {
    }

    public Item(final Long id, final String description) {
        this();
        this.id = id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

}