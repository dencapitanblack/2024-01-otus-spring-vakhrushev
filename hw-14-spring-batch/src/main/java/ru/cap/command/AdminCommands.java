package ru.cap.command;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.cap.config.AppProperties;

/**
 * @author dvakhrushev
 */

import java.sql.SQLException;

import static ru.cap.config.Readers.INPUT_FILE_NAME;


@ShellComponent
@RequiredArgsConstructor
public class AdminCommands {

    private final Job job;

    private final JobLauncher jobLauncher;

    private final AppProperties appProperties;

    @ShellMethod(value = "run console", key = "console")
    public String runH2Console() throws SQLException {
        org.h2.tools.Console.main();
        return "Console started";
    }

    @ShellMethod(value = "run csv load", key = "csv-load")
    public String runCSVLoad() throws Exception {
        jobLauncher.run(job, new JobParametersBuilder()
                .addString(INPUT_FILE_NAME, appProperties.getCsvPath())
                .toJobParameters());
        return "CSV loader ended";
    }

}
