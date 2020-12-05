package edu.epam.bsuir.controller.command;

public enum CommandType {

    VISIT_HOME_PAGE("/");

    private final String command;

    CommandType(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
