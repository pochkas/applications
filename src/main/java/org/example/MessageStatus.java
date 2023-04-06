package org.example;

public enum MessageStatus {
    DRAFT("draft"),
    SENT("sent"),
    ACCEPTED("accepted"),
    REJECTED("rejected");
    private String text;

    MessageStatus(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static MessageStatus fromString(String text) {
        for (MessageStatus status : MessageStatus.values()) {
            if (status.text.equalsIgnoreCase(text)) {
                return status;
            }
        }
        return null;
    }
}
