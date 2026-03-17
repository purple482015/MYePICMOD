package renderer;

import entities.EntityRegistry;
import entities.ModEntities;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.world.entity.EntityType;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class ModRenderer<R extends EntityRenderState & GeoRenderState> extends GeoEntityRenderer<ModEntities, R> {
    public ModRenderer(EntityRendererProvider.Context context, EntityType<ModEntities> entityType) {
        super(context, entityType);
    }
}