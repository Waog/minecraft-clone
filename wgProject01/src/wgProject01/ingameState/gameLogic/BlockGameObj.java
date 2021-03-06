package wgProject01.ingameState.gameLogic;

import wgProject01.ingameState.gameLogic.components.BlockPropertiesComponent;
import wgProject01.ingameState.gameLogic.view.BlockView;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;

/**
 * Objects of this class represent the logic of fixed blocks in the terrain.
 * 
 * They are managed by the {@link BlockManager}. They need to be informed,
 * whenever they are moved or their neighborhood changed, using this classes
 * methods.
 * 
 * @author oli
 * 
 */
public class BlockGameObj {

	/**
	 * The View of this block.
	 */
	private BlockView blockView;

	/**
	 * The position of this block.
	 */
	public BlockPropertiesComponent blockPositionComponent;

	/**
	 * Creates a new Block game object which will attach a spatial to the given
	 * node as soon as placed via the
	 * {@link #doHandlePlacementAt(int, int, int)} method.
	 * 
	 * @param type
	 *            for possible block types see static constants in
	 *            {@link BlockPropertiesComponent} .
	 */
	BlockGameObj(Node node, AssetManager assetManager, String type) {
		this.blockPositionComponent = new BlockPropertiesComponent(type);
		blockView = new BlockView(this, node, assetManager);
	}

	/**
	 * Informs the block about a changed neighbor block in the current
	 * direction. This block acts accordingly by turning on and of it's surfaces
	 * meshes.
	 */
	void doHandleNeighborChangeAt(int i, int j, int k, BlockGameObj newNeighbor) {
		blockView.informBlockChange();
	}

	/**
	 * Informs the block that it was placed at the given position. The block
	 * updates its model and view accordingly.
	 */
	void doHandlePlacementAt(int x, int y, int z) {
		this.blockPositionComponent.x = x;
		this.blockPositionComponent.y = y;
		this.blockPositionComponent.z = z;
		this.blockPositionComponent.placed = true;
		blockView.informBlockChange();
	}

	/**
	 * Informs the block that it was removed. The block updates its model and
	 * view accordingly.
	 */
	void doHandleRemovementFrom() {
		this.blockPositionComponent.placed = false;
		blockView.informBlockChange();
	}
}
