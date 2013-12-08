package com.springapp.mvc.controllers;

import com.springapp.mvc.model.Item;
import com.springapp.mvc.repositories.ItemRepositrory;

import java.beans.PropertyEditorSupport;

/**
 * Created with IntelliJ IDEA.
 * User: APepeskul
 * Date: 08.12.13
 * Time: 18:10
 * To change this template use File | Settings | File Templates.
 */
public class ItemEditor extends PropertyEditorSupport {
    ItemRepositrory itemRepositrory;
    ItemEditor(ItemRepositrory itemRepositroryRepository){
        this.itemRepositrory=itemRepositrory;

    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Item item = itemRepositrory.findOne(Long.parseLong(text));
        this.setValue(item);
    }

    @Override
    public String getAsText() {
        Item item = (Item) this.getValue();
        return Long.toString(item.getId());
    }
}
