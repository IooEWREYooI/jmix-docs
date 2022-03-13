package ui.ex1.screen.data.sort;

import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.model.Sorter;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ui.ex1.entity.Order;


@UiController("uiex1_OrderExample.browse")
@UiDescriptor("order-browse-example.xml")
@LookupComponent("ordersTable")
// tag::screen[]
public class OrderBrowseExample extends StandardLookup<Order> {

    @Autowired
    private CollectionLoader<Order> ordersDl;

    @Autowired
    private CollectionContainer<Order> ordersDc;

    @Autowired
    private BeanFactory beanFactory;

    @Subscribe
    private void onInit(InitEvent event) {
        Sorter sorter = new CustomCollectionContainerSorter(ordersDc, ordersDl, beanFactory);
        ordersDc.setSorter(sorter);
    }
}
// end::screen[]