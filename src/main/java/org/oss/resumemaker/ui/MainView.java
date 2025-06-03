package org.oss.resumemaker.ui;

import com.vaadin.componentfactory.pdfviewer.PdfViewer;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.HtmlObject;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.component.html.IFrame;
import com.vaadin.flow.server.StreamRegistration;
import com.vaadin.flow.server.VaadinSession;

@Route("")
@PageTitle("Resume Viewer")
public class MainView extends HorizontalLayout {

    public MainView() {
        setSizeFull();
        setPadding(false);
        setSpacing(false);

        // Create the PDF viewer component (3/4 of the width)
        Div pdfViewerContainer = createPdfViewer();
        pdfViewerContainer.setWidth("75%");
        pdfViewerContainer.setHeight("100%");

        // Create the chat component (1/4 of the width)
        Div chatContainer = createChatInterface();
        chatContainer.setWidth("25%");
        chatContainer.setHeight("100%");

        // Add components to the layout
        add(pdfViewerContainer, chatContainer);
    }

    private Div createPdfViewer() {
        Div container = new Div();
        container.getStyle().set("overflow", "hidden");

        // Create a StreamResource for the PDF file
        StreamResource resource = new StreamResource("PradeepResume.pdf",
                () -> getClass().getResourceAsStream("/PradeepResume.pdf"));

/*        // Register the resource with the session
        StreamRegistration registration = VaadinSession.getCurrent().getResourceRegistry().registerResource(resource);

        // Get the resource URL
        String resourceUrl = registration.getResourceUri().toString();

        // Create an IFrame to display the PDF
        IFrame iframe = new IFrame(resourceUrl);
        iframe.setSizeFull();
        iframe.getElement().setAttribute("frameborder", "0");*/
//        PdfBrowserViewer pdfBrowserViewer = new PdfBrowserViewer(resource);

        PdfViewer pdfViewer = new PdfViewer();
        pdfViewer.setSrc(resource);
        pdfViewer.setSizeFull();
        container.add(pdfViewer);
        return container;
    }

    private Div createChatInterface() {
        Div container = new Div();
        container.getStyle().set("background-color", "#f5f5f5");
        container.getStyle().set("padding", "10px");
        container.getStyle().set("box-sizing", "border-box");

        // Add the ChatView component to the container
        ChatView chatView = new ChatView();
        container.add(chatView);

        return container;
    }
}
