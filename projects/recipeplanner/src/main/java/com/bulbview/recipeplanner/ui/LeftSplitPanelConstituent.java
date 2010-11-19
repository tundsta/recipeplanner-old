package com.bulbview.recipeplanner.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class LeftSplitPanelConstituent extends VerticalLayout {

    private final Logger logger;

    @Inject public LeftSplitPanelConstituent(final MasterRecipeList masterRecipeList, final Label applicationNameLabel) {
        logger = LoggerFactory.getLogger(getClass());
        logger.info("...{} created", getClass().getName());
        addComponent(applicationNameLabel);
        addComponent(masterRecipeList);
        setExpandRatio(masterRecipeList, 1);
        setSizeFull();
        // recipeList.setSizeFull();
    }

}