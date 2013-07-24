package wgProject01.ingameState.gameLogic.view;

import jm3Utils.Jme3Utils;
import wgProject01.Settings;
import wgProject01.ingameState.gameLogic.components.CollisionBoxComponent;
import wgProject01.ingameState.gameLogic.components.PositionComponent;

import com.artemis.Entity;
import com.jme3.asset.AssetManager;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.control.AbstractControl;

/**
 * Wraps an (Artemis) Entity object and is attached to a visual model (a JME
 * spatial). Renders the spatial, to represent the wrapped entity best.
 * 
 * @author oli
 * 
 */
public class EntityView extends AbstractControl {

	/**
	 * The wrapped entity, which is visualized by this view.
	 */
	private Entity entity;

	/** the AssetManager of the simpleApplication */
	AssetManager assetManager;

	/**
	 * the Node containing mostly the entities, but now also the component box
	 * of it
	 */
	Node entityNode;

	private Geometry collisionBoxGeometry;

	/**
	 * Constructs a new View for the given entity.
	 */
	public EntityView(Entity entity) {
		this.entity = entity;
	}

	/** initializes the entity viewers datafields */
	public void init(AssetManager assetManager, Node entityNode) {
		this.assetManager = assetManager;
		this.entityNode = entityNode;

		
		// TODO 2 set the right debug mode
		if (Settings.debugMode >= 2) {
			PositionComponent positionComponent = entity
					.getComponent(PositionComponent.class);

			CollisionBoxComponent collisionBoxComponent = entity
					.getComponent(CollisionBoxComponent.class);
			if (collisionBoxComponent != null && positionComponent != null) {
				collisionBoxGeometry = Jme3Utils.getCuboid(collisionBoxComponent.radii, assetManager);
				collisionBoxGeometry.getMaterial().getAdditionalRenderState().setWireframe(true);
				collisionBoxGeometry.setLocalTranslation(positionComponent.pos);
				entityNode.attachChild(collisionBoxGeometry);
			}
		}
	}

	/**
	 * <p>
	 * JME3 calls this method automatically every frame.
	 * <p>
	 * 
	 * <p>
	 * Updates the spatial to which this view is attached to best match the
	 * wrapped entity.
	 * <p>
	 */
	@Override
	protected void controlUpdate(float tpf) {
		// set the position according to the entities position if it has one.
		PositionComponent positionComponent = entity
				.getComponent(PositionComponent.class);
		if (positionComponent != null) {
			spatial.setLocalTranslation(positionComponent.pos);
		}

		// TODO 2 set the right debug mode
		if (Settings.debugMode >= 2) {
			// show the collision box
			CollisionBoxComponent collisionBoxComponent = entity
					.getComponent(CollisionBoxComponent.class);
			if (collisionBoxComponent != null && positionComponent != null && collisionBoxGeometry != null) {
				collisionBoxGeometry.setLocalTranslation(positionComponent.pos);
				entityNode.attachChild(collisionBoxGeometry);
			}
		}
	}

	/**
	 * JME3 calls this method automatically every frame. Does nothing.
	 */
	@Override
	protected void controlRender(RenderManager rm, ViewPort vp) {
		// nothing
	}

}
