package com.springapp.mvc.controllers;

import com.springapp.mvc.model.Dimension;
import com.springapp.mvc.repositories.DimensionRepository;

import java.beans.PropertyEditorSupport;

/**
 * Created with IntelliJ IDEA.
 * User: APepeskul
 * Date: 08.12.13
 * Time: 18:10
 * To change this template use File | Settings | File Templates.
 */
public class DimensionEditor extends PropertyEditorSupport {
    DimensionRepository dimensionRepository;
    DimensionEditor(DimensionRepository dimensionRepository){
        this.dimensionRepository=dimensionRepository;

    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Dimension dimension = dimensionRepository.findOne(Long.parseLong(text));
        this.setValue(dimension);
    }

    @Override
    public String getAsText() {
        Dimension dimension = (Dimension) this.getValue();
        return Long.toString(dimension.getDimensionId());
    }
}
