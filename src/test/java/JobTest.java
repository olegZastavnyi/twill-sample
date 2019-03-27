import org.apache.twill.api.ResourceSpecification;
import org.apache.twill.api.TwillController;
import org.apache.twill.api.TwillRunner;
import org.apache.twill.api.logging.PrinterLogHandler;
//import org.apache.twill.yarn.BaseYarnTest;
//import org.junit.Assert;
//import org.junit.Test;

import java.io.PrintWriter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class JobTest /*extends BaseYarnTest */{

//    @Test
//    public void test() throws InterruptedException, TimeoutException, ExecutionException {
       /* TwillRunner runner = getTwillRunner();

        CountDownLatch logLatch = new CountDownLatch(1);
        TwillController controller = runner.prepare(new Job(),
                ResourceSpecification.Builder.with()
                        .setVirtualCores(2)
                        .setMemory(1, ResourceSpecification.SizeUnit.MEGA)
                        .setInstances(10)
                        .build())
                .addLogHandler(new PrinterLogHandler(new PrintWriter(System.out, true)))
                .start();

        Assert.assertTrue(logLatch.await(120, TimeUnit.SECONDS));
        controller.terminate().get(120, TimeUnit.SECONDS);*/
 //   }

}
