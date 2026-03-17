package model;

import entities.ModEntities;
import net.minecraft.resources.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class ModModel extends GeoModel<ModEntities> {

    @Override
    public Identifier getModelResource(GeoRenderState renderState) {
        return Identifier.fromNamespaceAndPath("end-update", "geo/ender_warp.geo.json");
    }

    @Override
    public Identifier getTextureResource(GeoRenderState renderState) {
        return Identifier.fromNamespaceAndPath("end-update", "textures/entity/ender_warp.png");
    }

    @Override
    public Identifier getAnimationResource(ModEntities entity) {
        return Identifier.fromNamespaceAndPath("end-update", "animations/ender_warp.animation.json");
    }
}