package wgProject01.ingameState.view;

import java.util.Observable;
import java.util.Observer;

import utils.typeModels.IntModel;
import wgProject01.ModelAccessor;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

/**
 * See {@link wgProject01.ingameState.view package description} for general
 * information on how to use controller classes.
 */
public class HudController implements Observer, ScreenController {

	private IntModel itemCount;
	private Nifty nifty;

	public HudController(Nifty nifty) {
		super();
		this.nifty = nifty;
		this.itemCount = ModelAccessor.getInstance().itemCount;
		this.itemCount.addObserver(this);

		createHud();

		update(this.itemCount, null);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o == itemCount) {
			String newText = "itemCount: " + itemCount.get();

			// find old text
			Element niftyElement = nifty.getScreen("hud").findElementByName(
					"updatedText");
			// swap old with new text
			niftyElement.getRenderer(TextRenderer.class).setText(newText);
		}
	}

	private void createHud() {
		/** Read your XML and initialize your custom ScreenController */
		nifty.fromXml("Interface/hud.xml", "start", this);
	}

	/**
	 * Switches to the screen with the given ID.
	 */
	public void startGame(String nextScreen) {
		System.out.println("startGame() called");
		nifty.gotoScreen(nextScreen); // switch to another screen
		// start the game and do some more stuff...
	}

	/**
	 * Quits the game app.
	 */
	public void quitGame() {
		// app.stop();
		System.out.println("quitQame() called");
		System.exit(0);
	}

	@Override
	public void bind(Nifty arg0, Screen arg1) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onEndScreen() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStartScreen() {
		// TODO Auto-generated method stub
	}

}
