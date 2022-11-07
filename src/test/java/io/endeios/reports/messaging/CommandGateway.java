package io.endeios.reports.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

class CommandGateway {
    private final static Logger log = LoggerFactory.getLogger(CommandGateway.class);
    @SuppressWarnings("rawtypes")
    private final List<Listener> listenerList;

    public CommandGateway() {
        listenerList = new ArrayList<>();
    }

    public Object request(Command command) {

        return dispatch(command);

    }

    private Object dispatch(Command command) {
        if (listenerList.size() == 0) {
            log.debug("There are no registered listeners in" + this + " : the requested commands was " + command);
            return null;
        }
        for (@SuppressWarnings("rawtypes")Listener listener :
                listenerList) {
            Class<? extends Listener> clazz = listener.getClass();
            try {
                Method method = clazz.getDeclaredMethod("on", command.getClass());
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes[0].equals(command.getClass()))
                    return method.invoke(listener, command);

            } catch (NoSuchMethodException | InvocationTargetException e) {
                log.debug("Could not dispatch "+ command + " to "+ listener);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

        }
        log.debug("There is no listener that can handle "+ command);
        return null;
    }

    public void addListener(@SuppressWarnings("rawtypes") Listener listener) {
        listenerList.add(listener);
    }
}
