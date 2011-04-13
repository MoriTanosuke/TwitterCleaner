package de.kopis.twittercleaner;

import static com.google.appengine.api.taskqueue.TaskOptions.Builder.withUrl;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions.Method;
import com.google.appengine.api.taskqueue.dev.LocalTaskQueue;
import com.google.appengine.api.taskqueue.dev.QueueStateInfo;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalTaskQueueTestConfig;

public class TaskQueueTest {
    private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalTaskQueueTestConfig());

    @Before
    public void setUp() {
        helper.setUp();
    }

    @After
    public void tearDown() {
        helper.tearDown();
    }

    @Test
    public void testDeleteStatus() throws Exception {
        QueueFactory.getDefaultQueue().add(
                withUrl("/deletestatusworker").param("id", "1").param("oauthtoken", "1-token").method(Method.GET));
        Thread.sleep(1000);
        LocalTaskQueue ltq = LocalTaskQueueTestConfig.getLocalTaskQueue();
        QueueStateInfo qsi = ltq.getQueueStateInfo().get(QueueFactory.getDefaultQueue().getQueueName());
        assertEquals(1, qsi.getTaskInfo().size());

    }

    @Test
    public void testTaskGetsScheduled1() throws InterruptedException {
        QueueFactory.getDefaultQueue().add(
                withUrl("/deletestatusworker").param("id", "1").param("oauthtoken", "token").method(Method.GET));
        Thread.sleep(1000);
        // give the task time to execute if tasks are actually enabled (which
        // they aren't, but that's part of the test)
        Thread.sleep(1000);
        LocalTaskQueue ltq = LocalTaskQueueTestConfig.getLocalTaskQueue();
        QueueStateInfo qsi = ltq.getQueueStateInfo().get(QueueFactory.getDefaultQueue().getQueueName());
        assertEquals(1, qsi.getTaskInfo().size());
        assertEquals("task1", qsi.getTaskInfo().get(0).getTaskName());
    }
}
