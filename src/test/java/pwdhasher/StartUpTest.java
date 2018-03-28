package pwdhasher;

import org.junit.Test;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

public class StartUpTest extends AbstractFxTest {

	@Test
	public void startUp() {
		verifyThat("#rootPane", isVisible());
	}
}
