package entities;

import end_update.EndUpdate;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class EntityRegistry {

    public static final EntityType<ModEntities> ENDER_WARP =
            Registry.register(
                    BuiltInRegistries.ENTITY_TYPE,
                    Identifier.fromNamespaceAndPath(EndUpdate.MOD_ID, "ender_warp"),
                    EntityType.Builder.of(ModEntities::new, MobCategory.MONSTER)
                            .sized(0.6f, 1.95f)
                            .build(
                                    ResourceKey.create(
                                            Registries.ENTITY_TYPE,
                                            Identifier.fromNamespaceAndPath(EndUpdate.MOD_ID, "ender_warp")
                                    )
                            )
            );

    public static void register() {
        // Forces class loading so static field registers
    }
}