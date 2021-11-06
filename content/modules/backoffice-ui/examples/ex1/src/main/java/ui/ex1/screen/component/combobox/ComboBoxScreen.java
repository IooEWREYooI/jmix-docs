package ui.ex1.screen.component.combobox;

import com.vaadin.server.FontAwesome;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.*;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;
import ui.ex1.entity.Hobby;


import java.util.*;

@UiController("comboBox-screen")
@UiDescriptor("combobox-screen.xml")
public class ComboBoxScreen extends Screen {
    @Autowired
    protected ComboBox<Hobby> hobbyValidField;

    // tag::image[]
    private Image imageResource;

    // end::image[]
    @Autowired
    private UiComponents uiComponents;

    // tag::icon-combo-box[]
    @Autowired
    private ComboBox iconComboBox;

    // end::icon-combo-box[]
    // tag::options-list[]
    @Autowired
    private ComboBox<String> maritalStatusField;

    // end::options-list[]
    // tag::options-map[]
    @Autowired
    private ComboBox<Integer> ratingField;

    // end::options-map[]
    // tag::options-map-2[]
    // tag::options-list-2[]
    @Subscribe
    public void onInit(InitEvent event) {
        // end::options-map-2[]
        List<String> list = new ArrayList<>();
        list.add("Married");
        list.add("Widowed");
        list.add("Separated");
        list.add("Divorced");
        list.add("Single");
        maritalStatusField.setOptionsList(list);
        // end::options-list-2[]
        // tag::new-option-handler[]
        maritalStatusField.setEnterPressHandler(enterPressEvent -> {
            String text = enterPressEvent.getText();
            list.add(text);
            maritalStatusField.setOptionsList(list);
        });
        // end::new-option-handler[]
        // tag::options-map-3[]
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("Poor", 2);
        map.put("Average", 3);
        map.put("Good", 4);
        map.put("Excellent", 5);
        ratingField.setOptionsMap(map);
        // end::options-map-3[]
        // tag::icon-map[]
        Map<String, FontAwesome> iconMap = new HashMap<>();
        iconMap.put("Archive file", FontAwesome.FILE_ARCHIVE_O);
        iconMap.put("PDF file", FontAwesome.FILE_PDF_O);
        iconMap.put("TXT file", FontAwesome.FILE_TEXT_O);
        iconComboBox.setOptionsMap(iconMap);
        // end::icon-map[]
        // tag::image-create[]
        imageResource = uiComponents.create(Image.NAME);
        // end::image-create[]
        // tag::init-end[]
    }

    // end::init-end[]
    // tag::icon-provider[]
    @Install(to = "iconComboBox", subject = "optionIconProvider")
    private String iconComboBoxOptionIconProvider(FontAwesome icon) {
        return "font-icon:" + icon;
    }

    // end::icon-provider[]
    // tag::style-provider[]
    @Install(to = "ratingField", subject = "optionStyleProvider")
    private String ratingFieldOptionStyleProvider(Integer rating) {
        if (rating != null) {
            switch (rating) {
                case 2:
                    return "poor";
                case 3:
                    return "average";
                case 4:
                    return "good";
                case 5:
                    return "excellent";
            }
        }
        return null;
    }

    // end::style-provider[]
    // tag::options-caption-filter[]
    @Install(to = "hobbyField", subject = "optionsCaptionFilter")
    private boolean hobbyFieldOptionsCaptionFilter(ComboBox.OptionsCaptionFilteringContext
                                                           optionsCaptionFilteringContext) {
        return optionsCaptionFilteringContext.getItemCaption()
                .contains(optionsCaptionFilteringContext.getSearchString());
    }

    // end::options-caption-filter[]
    // tag::option-image-provider[]
    @Install(to = "comboBoxWithImages", subject = "optionImageProvider")
    private Resource comboBoxWithImagesOptionImageProvider(Hobby hobby) {
        return imageResource.setSource(ThemeResource.class).setPath("icons/check-mark.png");
    }
    // end::option-image-provider[]

    @Subscribe("validBtn")
    protected void onValidBtnClick(Button.ClickEvent event) {
        hobbyValidField.validate();
    }

    // tag::validator[]
    @Install(to = "iconComboBox", subject = "validator")
    protected void iconComboBoxValidator(FontAwesome icon) {
        if (icon != null)
            if (icon == FontAwesome.FILE_PDF_O)
                throw new ValidationException("The file type you selected " +
                        "is not currently supported");
    }
    // end::validator[]

    @Subscribe("validateBtn")
    protected void onValidateBtnClick(Button.ClickEvent event) {
        iconComboBox.validate();
    }
}