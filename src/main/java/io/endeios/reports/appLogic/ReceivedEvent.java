package io.endeios.reports.appLogic;

import java.util.Objects;

public class ReceivedEvent {
    private final String sender;
    private final String name;
    private final double value;

    public ReceivedEvent(String sender, String name, double v) {
        this.sender = sender;
        this.name = name;
        this.value = v;
    }

    public static ReceivedEvent fromString(String message) {
        String[] splits = message.split(":");

        return new ReceivedEvent(splits[0],splits[1],Double.parseDouble(splits[2]));
    }

    public String getSender() {
        return sender;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "ReceivedEvent{" +
                "sender='" + sender + '\'' +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReceivedEvent event)) return false;
        return Double.compare(event.getValue(), getValue()) == 0 && Objects.equals(getSender(), event.getSender()) && Objects.equals(getName(), event.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSender(), getName(), getValue());
    }
}
