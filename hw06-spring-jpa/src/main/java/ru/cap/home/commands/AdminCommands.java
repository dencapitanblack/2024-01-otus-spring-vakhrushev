package ru.cap.home.commands;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.sql.SQLException;

@ShellComponent
public class AdminCommands {

    @ShellMethod(value = "run console", key = "console")
    public String runH2Console() throws SQLException {
        org.h2.tools.Console.main();
        return "Console started";
    }

}
