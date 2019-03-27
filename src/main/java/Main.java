import com.google.common.util.concurrent.Futures;
import org.apache.hadoop.yarn.conf.YarnConfiguration;
import org.apache.twill.api.ResourceSpecification;
import org.apache.twill.api.TwillController;
import org.apache.twill.api.TwillRunnerService;
import org.apache.twill.api.logging.PrinterLogHandler;
import org.apache.twill.yarn.YarnTwillRunnerService;

import java.io.PrintWriter;
import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String args[]) {

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

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                Futures.getUnchecked(controller.terminate());
            } finally {
                runnerService.stop();
            }
        }));

        try {
            controller.awaitTerminated();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
