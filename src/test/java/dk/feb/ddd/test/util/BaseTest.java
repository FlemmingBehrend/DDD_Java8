package dk.feb.ddd.test.util;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;

@RunWith(HierarchicalContextRunner.class)
public abstract class BaseTest extends Assert {

    @Before
    public void initializeMockito() {
        MockitoAnnotations.initMocks(this);
    }

    @Rule
    public TestName method = new TestName();

}