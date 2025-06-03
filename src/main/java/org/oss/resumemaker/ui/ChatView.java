package org.oss.resumemaker.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.icon.VaadinIcon;

public class ChatView extends VerticalLayout {
    
    private final Div messagesContainer;
    private final TextField messageInput;
    
    public ChatView() {
        setSizeFull();
        setPadding(true);
        setSpacing(true);
        
        // Chat header
        H3 header = new H3("Chat");
        header.getStyle().set("margin-top", "0");
        
        // Messages container
        messagesContainer = new Div();
        messagesContainer.setClassName("messages-container");
        messagesContainer.getStyle().set("overflow-y", "auto");
        messagesContainer.getStyle().set("flex-grow", "1");
        messagesContainer.getStyle().set("display", "flex");
        messagesContainer.getStyle().set("flex-direction", "column");
        messagesContainer.getStyle().set("gap", "8px");
        messagesContainer.getStyle().set("padding", "10px");
        messagesContainer.getStyle().set("background-color", "#ffffff");
        messagesContainer.getStyle().set("border-radius", "5px");
        messagesContainer.getStyle().set("border", "1px solid #e0e0e0");
        
        // Add a welcome message
        addMessage("Bot", "Hello! I'm here to answer questions about the resume. What would you like to know?");
        
        // Message input area
        messageInput = new TextField();
        messageInput.setPlaceholder("Type your message...");
        messageInput.setClearButtonVisible(true);
        messageInput.setWidthFull();
        
        Button sendButton = new Button(VaadinIcon.PAPERPLANE.create());
        sendButton.getElement().setAttribute("aria-label", "Send message");
        sendButton.addClickListener(e -> sendMessage());
        
        // Enter key sends the message
        messageInput.addKeyPressListener(event -> {
            if (event.getKey().equals("Enter")) {
                sendMessage();
            }
        });
        
        // Input layout with text field and send button
        HorizontalLayout inputLayout = new HorizontalLayout(messageInput, sendButton);
        inputLayout.setWidthFull();
        inputLayout.setFlexGrow(1, messageInput);
        
        // Add all components to the layout
        add(header, messagesContainer, inputLayout);
        
        // Set component sizing
        setFlexGrow(1, messagesContainer);
        setHeight("100%");
    }
    
    private void sendMessage() {
        String message = messageInput.getValue().trim();
        if (!message.isEmpty()) {
            addMessage("You", message);
            messageInput.clear();
            messageInput.focus();
            
            // Simulate a response after a short delay
            getUI().ifPresent(ui -> {
                ui.access(() -> {
                    try {
                        Thread.sleep(1000);
                        addMessage("Bot", generateResponse(message));
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            });
        }
    }
    
    private void addMessage(String sender, String content) {
        Div messageDiv = new Div();
        messageDiv.getStyle().set("padding", "8px 12px");
        messageDiv.getStyle().set("border-radius", "8px");
        messageDiv.getStyle().set("margin-bottom", "8px");
        messageDiv.getStyle().set("max-width", "80%");
        
        if (sender.equals("You")) {
            messageDiv.getStyle().set("align-self", "flex-end");
            messageDiv.getStyle().set("background-color", "#e7f4ff");
        } else {
            messageDiv.getStyle().set("align-self", "flex-start");
            messageDiv.getStyle().set("background-color", "#f0f0f0");
        }
        
        Paragraph senderParagraph = new Paragraph(sender);
        senderParagraph.getStyle().set("font-weight", "bold");
        senderParagraph.getStyle().set("margin", "0 0 4px 0");
        
        Paragraph contentParagraph = new Paragraph(content);
        contentParagraph.getStyle().set("margin", "0");
        
        messageDiv.add(senderParagraph, contentParagraph);
        messagesContainer.add(messageDiv);
        
        // Scroll to the bottom
        getUI().ifPresent(ui -> ui.access(() -> {
            String js = "document.querySelector('.messages-container').scrollTop = " +
                    "document.querySelector('.messages-container').scrollHeight";
            ui.getPage().executeJs(js);
        }));
    }
    
    private String generateResponse(String message) {
        // Simple response generation - in a real app, this would be more sophisticated
        message = message.toLowerCase();
        
        if (message.contains("experience") || message.contains("work")) {
            return "The resume shows extensive experience in software development and leadership roles.";
        } else if (message.contains("education") || message.contains("degree")) {
            return "The education section lists degrees and certifications in computer science and related fields.";
        } else if (message.contains("skill") || message.contains("technology")) {
            return "The resume highlights skills in Java, Spring Boot, cloud technologies, and various other programming languages and frameworks.";
        } else if (message.contains("contact") || message.contains("email") || message.contains("phone")) {
            return "Contact information can be found at the top of the resume.";
        } else if (message.contains("hello") || message.contains("hi") || message.contains("hey")) {
            return "Hello! How can I help you with information about the resume?";
        } else {
            return "I'm not sure about that specific detail. Could you ask something about the experience, education, or skills sections?";
        }
    }
}