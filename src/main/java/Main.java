import org.apache.hadoop.yarn.conf.YarnConfiguration;
import org.apache.twill.api.Command;
import org.apache.twill.api.ResourceSpecification;
import org.apache.twill.api.TwillController;
import org.apache.twill.api.TwillRunnerService;
import org.apache.twill.api.logging.PrinterLogHandler;
import org.apache.twill.yarn.YarnTwillRunnerService;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String args[]) throws ExecutionException, InterruptedException {

        if (args.length < 1) {
            System.err.println("Arguments format: <host:port of zookeeper server>");
            System.exit(1);
        }

        String zkUrl = args[0];
        TwillRunnerService runnerService = new YarnTwillRunnerService(
                new YarnConfiguration(), zkUrl);
        runnerService.start();

        final TwillController controller = runnerService.prepare(new Job(),
                ResourceSpecification.Builder.with()
                        .setVirtualCores(2)
                        .setMemory(1, ResourceSpecification.SizeUnit.GIGA)
                        .setInstances(5)
                        .build())
                .addLogHandler(new PrinterLogHandler(new PrintWriter(System.out)))
                .start();

        runnerService.start();

        Thread.sleep(30000);
        controller.sendCommand(new MyCommand("Sent command. Hello World!"));

        controller.awaitTerminated();

    }

    static class MyCommand implements Command {
        String command;

        MyCommand(String command) {
            this.command = command;
        }

        @Override
        public String getCommand() {
            return command;
        }

        @Override
        public Map<String, String> getOptions() {
            return new HashMap<>();
        }
    }
}
