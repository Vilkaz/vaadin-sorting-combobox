package org.vaadin.demo;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import org.vaadin.SortingComboBox;

import java.util.Arrays;
import java.util.Collection;

@Theme("demo")
@Title("MyComponent Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI
{

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {

        Collection<Item> items = createItems();

        SortingComboBox<Item> regularComboBox = new SortingComboBox("Regular ComboBox");
        regularComboBox.setItems(items);
        regularComboBox.setItemCaptionGenerator(i -> i.getId() + ":" + i.getDescription());


        ComboBox<Item> sortedComboBox = new ComboBox("Sorting Combobox");
        sortedComboBox.setItemCaptionGenerator(i -> i.getId() + ":" + i.getDescription());
        sortedComboBox.setItems(items);

        HorizontalLayout boxLayout = new HorizontalLayout(regularComboBox, sortedComboBox,  new Label("try entering values 11-14 in bouth boxes"));
        setContent(boxLayout);
    }

    private Collection<Item> createItems() {
        Item item1 = new Item(11L, "nivea(14131211)");
        Item item2 = new Item(12L, "isana(13141211)");
        Item item3 = new Item(13L,  "bebe(11121314)");
        Item item4 = new Item(14L,  "olaz(11131214)");
        return Arrays.asList(item1, item2, item3, item4);
    }
}
