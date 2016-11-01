package cofh.thermalexpansion.client.model;

import codechicken.lib.render.block.IExtendedModel;
import codechicken.lib.texture.TextureUtils;
import cofh.core.render.IconRegistry;
import cofh.thermalexpansion.client.bakery.BlockBakery;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.property.IExtendedBlockState;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by covers1624 on 26/10/2016.
 */
public class TEBakedModel implements IBakedModel, IExtendedModel {

    private final String particle;

    public TEBakedModel(String particle){
        this.particle = particle;
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing side, long rand) {
        return new ArrayList<BakedQuad>();
    }

    @Override
    public boolean isAmbientOcclusion() {
        return true;
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

    @Override
    public boolean isBuiltInRenderer() {
        return false;
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return TextureUtils.getTexture(particle);
    }

    @Override
    public ItemCameraTransforms getItemCameraTransforms() {
        return ItemCameraTransforms.DEFAULT;
    }

    @Override
    public ItemOverrideList getOverrides() {
        return new ItemOverrideList(ImmutableList.<ItemOverride>of()){
            @Override
            public IBakedModel handleItemState(IBakedModel originalModel, ItemStack stack, World world, EntityLivingBase entity) {
                IBakedModel model = BlockBakery.getCachedItemModel(stack);
                if (model == null){
                    return originalModel;
                }
                return model;
            }
        };
    }

    @Override
    public IBakedModel handleExtendedModel(IBakedModel prevModel, IBlockState state) {
        return BlockBakery.getCachedModel((IExtendedBlockState) state);
    }
}
