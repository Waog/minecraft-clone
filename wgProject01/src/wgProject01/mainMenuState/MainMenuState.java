package wgProject01.mainMenuState;

import wgProject01.GameApplication;
import wgProject01.ingameState.IngameState;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

public class MainMenuState extends AbstractAppState implements ScreenController {

	/**
	 * Flag: was the corresponding nifty GUI XML file already read?
	 */
	private static boolean readXmlOnce = false;

	/**
	 * datafields given by the {@link GameApplication} and the
	 * {@link AssetManager} itself
	 */
	private GameApplication app;
	private Nifty nifty;
	private AppStateManager stateManager;

	/**
	 * Hopefully automatically constructed once at the screen initialization
	 * when reading the XML.
	 */
	public MainMenuState() {
	}

	@Override
	public void bind(Nifty nifty, Screen screen) {
		this.nifty = nifty;
	}

	/**
	 * Called by JME3 whenever this state is attached to a state manager.
	 * 
	 * Initializes the Main Menu (including nifty stuff, mouse cursor).
	 * 
	 * Internal: Sets all data fields.
	 */
	@Override
	public void initialize(AppStateManager stateManager, Application app) {
		super.initialize(stateManager, app);

		// Sets some variables.
		this.app = (GameApplication) app; // cast to a more specific class
		this.stateManager = stateManager;

		// enable cursor
		this.app.getInputManager().setCursorVisible(true);

		nifty.gotoScreen("start");
	}

	// ============== Input handling methods ===========

	/**
	 * Switches to the screen with the given ID.
	 */
	public void startGame() {
		// initialize and switch to the next state/screen
		ScreenController ingameScreenController = nifty.getScreen("hud")
				.getScreenController();
		IngameState ingameState = (IngameState) ingameScreenController;
		this.nifty.gotoScreen("hud");
		this.stateManager.attach(ingameState);
		this.stateManager.detach(this);
	}

	/**
	 * Quits the game app.
	 */
	public void quitGame() {
		app.stop();
	}

	// ================== unused JME state management methods
	// ====================

	/**
	 * Called by JME3 whenever this state is detached from it's state manager.
	 */
	@Override
	public void cleanup() {
		super.cleanup();
		// unregister all my listeners, detach all my nodes, etc...
	}

	/**
	 * Called by JME3 whenever the state is paused/unpaused. A disabled game
	 * state receives no updates until enabled again.
	 */
	@Override
	public void setEnabled(boolean enabled) {
		// Pause and unpause
		super.setEnabled(enabled);
		if (enabled) {
			// init stuff that is in use while this state is RUNNING
			// nothing
		} else {
			// take away everything not needed while this state is PAUSED
			// nothing
		}
	}

	/**
	 * Note that update is only called while the state is both attached and
	 * enabled.
	 */
	@Override
	public void update(float tpf) {
		// nothing
	}

	// ================= nifty methods =================

	@Override
	public void onEndScreen() {
		// nothing
	}

	@Override
	public void onStartScreen() {
		// nothing
	}
}