package reports.ex2.screen.entity.book;

import io.jmix.reportsui.action.list.EditorPrintFormAction;
import io.jmix.ui.Actions;
import io.jmix.ui.component.Button;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;
import reports.ex2.entity.Book;

import javax.inject.Inject;

@UiController("jmxrpr_Book.edit")
@UiDescriptor("book-edit.xml")
@EditedEntityContainer("bookDc")
public class BookEdit extends StandardEditor<Book> {
    // tag::editor-print-button[]
    @Inject
    private Button reportButton;

    @Autowired
    private Actions actions;

    @Subscribe
    public void onInit(InitEvent event) {
        EditorPrintFormAction action = actions.create(EditorPrintFormAction.class);
        action.setEditor(this);
        action.setReportOutputName(null);
        reportButton.setAction(action);
    }
    // end::editor-print-button[]
}