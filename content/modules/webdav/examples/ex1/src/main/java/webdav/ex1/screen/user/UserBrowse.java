package webdav.ex1.screen.user;

import io.jmix.ui.navigation.Route;
import io.jmix.ui.screen.*;
import webdav.ex1.entity.User;

@UiController("User.browse")
@UiDescriptor("user-browse.xml")
@LookupComponent("usersTable")
@Route("users")
public class UserBrowse extends StandardLookup<User> {
}