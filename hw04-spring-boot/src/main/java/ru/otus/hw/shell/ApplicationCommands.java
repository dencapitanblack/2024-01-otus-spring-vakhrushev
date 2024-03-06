package ru.otus.hw.shell;


import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.hw.security.LoginContext;
import ru.otus.hw.service.TestRunnerService;

@ShellComponent(value = "Application Events Commands")
@RequiredArgsConstructor
public class ApplicationCommands {

    private final TestRunnerService testRunnerService;

    private final LoginContext loginContext;

    @ShellMethod(value = "Start testing", key = {"-s", "--start"})
    @ShellMethodAvailability(value = "availableStartCommand")
    public void start() {
        testRunnerService.run();
    }

    @ShellMethod(value = "Hello command", key = {"--hello"})
    public String hello() {
        loginContext.login();
        return "You have successfully logged in";
    }

    private Availability availableStartCommand() {
        return loginContext.isUserLoggedIn() ?
                Availability.available() :
                Availability.unavailable("for start you must call hello command. (--hello)");
    }


}
