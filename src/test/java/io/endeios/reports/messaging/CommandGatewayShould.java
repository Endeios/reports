package io.endeios.reports.messaging;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class CommandGatewayShould {
    @Test
    void acceptCommands() {
        Command command = new SimpleCommand();
        CommandGateway commandGateway = new CommandGateway();
        commandGateway.request(command);
    }

    @Test
    void dispatchCommandsToListeners() {
        Listener<SimpleCommand> listener = mock(SimpleCommandListener.class);
        SimpleCommand command = new SimpleCommand();
        CommandGateway commandGateway = new CommandGateway();
        commandGateway.addListener(listener);
        commandGateway.request(command);
        verify(listener).on(command);
    }

    @Test
    void returnAResultFromACommand() {
        Listener<SimpleCommand> listener = mock(SimpleCommandListener.class);
        SimpleCommand command = new SimpleCommand();
        when(listener.on(any(SimpleCommand.class))).thenReturn(Boolean.TRUE);
        CommandGateway commandGateway = new CommandGateway();
        commandGateway.addListener(listener);
        Object result = commandGateway.request(command);
        verify(listener).on(command);
        assertThat(result).isEqualTo(true);
    }

    @Test
    void returnACorrectResultForTheCorrectCommand() {
        Listener<CommandA> listenerA = mock(CommandListenerA.class);
        CommandA commandA = new CommandA();
        when(listenerA.on(any(CommandA.class))).thenReturn("Answer A");
        Listener<CommandB> listenerB = mock(CommandListenerB.class);
        CommandB commandB = new CommandB();
        when(listenerB.on(any(CommandB.class))).thenReturn("Answer B");

        CommandGateway commandGateway = new CommandGateway();
        commandGateway.addListener(listenerA);
        commandGateway.addListener(listenerB);
        String resultA = (String) commandGateway.request(commandA);
        String resultB = (String) commandGateway.request(commandB);
        assertThat(resultA).isEqualTo("Answer A");
        assertThat(resultB).isEqualTo("Answer B");

    }

    class CommandA implements Command{}
    class CommandB implements Command{}
    class CommandListenerA implements Listener<CommandA>{
        @Override
        public Object on(CommandA command) {
            return null;
        }
    }

    class CommandListenerB implements Listener<CommandB> {
        @Override
        public Object on(CommandB command) {
            return null;
        }
    }
    class SimpleCommandListener implements Listener<SimpleCommand>{

        @Override
        public Object on(SimpleCommand command) {
            throw new RuntimeException();
        }
    }
    class SimpleCommand implements Command {}

}
