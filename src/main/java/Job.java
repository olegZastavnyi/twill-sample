import org.apache.twill.api.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Job extends AbstractTwillRunnable {

    private static final Logger LOG = LoggerFactory.getLogger(Job.class);

    @Override
    public void run() {
        LOG.info("START-"
                + Thread.currentThread().getId() + "  "
                + getContext().getInstanceCount() + ": "
                + getContext().getInstanceId());
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        LOG.info("DESTROY-" + Thread.currentThread().getId());
    }
}

