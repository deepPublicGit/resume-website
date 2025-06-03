package org.oss.resumemaker.ui;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;

/**
 * The main configuration class for the Vaadin application.
 */
@PWA(
    name = "Resume Viewer",
    shortName = "Resume",
    description = "An application to view a resume and chat with an assistant"
)
@Theme(value = "default")
@Push
public class AppShell implements AppShellConfigurator {
    // This class doesn't need any implementation, it just serves as a configuration point
}
