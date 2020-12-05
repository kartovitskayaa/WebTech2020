package edu.epam.bsuir.controller.command;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class CommandFactory {

    private static CommandFactory instance;

    private static final ReentrantLock lock = new ReentrantLock();

    private static final AtomicBoolean create = new AtomicBoolean(false);

    private final HashMap<String, Command> commandMap;

    private CommandFactory() {
        commandMap = new HashMap<>();
    }

    public static CommandFactory getInstance() {
        if (!create.get()) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new CommandFactory();
                    create.set(true);
                }
            } finally {
                lock.unlock();
            }
        }

        return instance;
    }

    public Command receiveCommand(String commandType) {
        return commandMap.get(commandType);
    }
}
