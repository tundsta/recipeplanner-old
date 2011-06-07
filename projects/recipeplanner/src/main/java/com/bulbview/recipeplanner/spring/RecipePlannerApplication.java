package com.bulbview.recipeplanner.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.bulbview.recipeplanner.ui.RecipePresenter;
import com.bulbview.recipeplanner.ui.manager.CategoryEditor;
import com.bulbview.recipeplanner.ui.manager.CategoryTabs;
import com.bulbview.recipeplanner.ui.manager.MainWindowUiManager;
import com.bulbview.recipeplanner.ui.manager.RecipeEditor;
import com.bulbview.recipeplanner.ui.manager.RecipeMasterList;
import com.bulbview.recipeplanner.ui.manager.WeeklySchedule;
import com.bulbview.recipeplanner.ui.presenter.CategoryTabsPresenter;
import com.bulbview.recipeplanner.ui.presenter.ShoppingListPresenter;
import com.vaadin.Application;
import com.vaadin.ui.Window;

@Configurable(preConstruction = true)
public class RecipePlannerApplication extends Application {

    private static final long      serialVersionUID = 1L;

    @Autowired
    private CategoryEditor         categoryEditor;
    @Autowired
    private CategoryTabs           categoryTabs;
    @Autowired
    private CategoryTabsPresenter  categoryTabsPresenter;
    private Logger                 logger;
    @Autowired
    private MainWindowUiManager    mainWindow;
    @Autowired
    private RecipeEditor           recipeEditor;
    @Autowired
    private RecipeMasterList       recipeMasterList;
    @Autowired
    private RecipePresenter recipePlannerPresenter;
    @Autowired
    private Window                 rootWindow;
    @Autowired
    private ShoppingListPresenter  shoppingListPresenter;
    @Autowired
    private String                 theme;
    @Autowired
    private WeeklySchedule         weeklyScheduler;

    @Override
    public void init() {
        this.logger = LoggerFactory.getLogger(getClass());
        logger.info("Initialising application...");
        setTheme(theme);
        setMainWindow(rootWindow);
        recipeMasterList.setTopLevelPanel(mainWindow.getRecipePanel());
        weeklyScheduler.setScheduler(mainWindow.getSchedulerAccordion());
        categoryTabs.setComponent(mainWindow.getCategoryAccordion());
        initPresenterViews();
        // TODO Presenters should be initialised by spring container only -
        // init() method called in UI component manager.
        recipePlannerPresenter.init();
        categoryTabsPresenter.init();
        shoppingListPresenter.init();
        weeklyScheduler.init();
    }

    private void initPresenterViews() {
        logger.info("Configuring presenters...");
        recipePlannerPresenter.setRecipeEditor(recipeEditor);
        recipePlannerPresenter.setRecipeMasterList(recipeMasterList);
        categoryTabsPresenter.setCategoryEditorWindow(categoryEditor);
        categoryTabsPresenter.setCategoryTabs(categoryTabs);
    }
}