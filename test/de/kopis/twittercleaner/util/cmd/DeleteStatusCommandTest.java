package de.kopis.twittercleaner.util.cmd;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import twitter4j.Status;
import twitter4j.Twitter;
import de.kopis.twittercleaner.cmd.DeleteStatusCommand;

public class DeleteStatusCommandTest {

    @Test
    public void testDeleteStatus() throws Exception {
        long statusId = 1;
        Status status = new DummyStatusImpl(statusId);
        Twitter t = new DummyTwitterImpl(status);
        Status deleted = new DeleteStatusCommand(statusId).execute(t);
        assertEquals(status, deleted);
    }
}
