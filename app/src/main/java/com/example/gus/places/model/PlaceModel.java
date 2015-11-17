package com.example.gus.places.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Laotshi on 11/16/15.
 */
public class PlaceModel {

    private Links links;
    private List<Item> items = new ArrayList<Item>();

    /**
     * @return The links
     */
    public Links getLinks() {
        return links;
    }

    /**
     * @param links The links
     */
    public void setLinks(Links links) {
        this.links = links;
    }

    /**
     * @return The items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * @param items The items
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }


}
