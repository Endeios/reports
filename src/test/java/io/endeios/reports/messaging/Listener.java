package io.endeios.reports.messaging;

interface Listener<T extends Command> {
    public Object on(T command);

}
