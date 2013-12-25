package com.springapp.mvc.controllers;

import com.springapp.mvc.model.Item;
import com.springapp.mvc.repositories.ItemRepository;

import java.beans.PropertyEditorSupport;

public class ItemEditor extends PropertyEditorSupport {

    private ItemRepository itemRepository;

    ItemEditor(ItemRepository itemRepositoryRepository){
        this.itemRepository = itemRepository;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Item item = itemRepository.findOne(Long.parseLong(text));
        this.setValue(item);
    }

    @Override
    public String getAsText() {
        Item item = (Item) this.getValue();
        return Long.toString(item.getId());
    }
}
