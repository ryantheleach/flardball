package au.id.rleach.flardball.worldgen;

import com.google.common.collect.Maps;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.extent.ImmutableBiomeArea;
import org.spongepowered.api.world.extent.MutableBlockVolume;
import org.spongepowered.api.world.gen.GenerationPopulator;

import java.util.Map;

public class TAAMCBaseGenerationPopulator implements GenerationPopulator {

    Map<Range, GenerationPopulator> populatorMap = Maps.newLinkedHashMap();

    public TAAMCBaseGenerationPopulator(GenerationPopulator original, Range range) {
        append(range, original);
    }

    @Override public void populate(World world, MutableBlockVolume buffer, ImmutableBiomeArea biomes) {
        for (Map.Entry<Range, GenerationPopulator> entry : populatorMap.entrySet()) {
            Range range = entry.getKey();
            GenerationPopulator populator = entry.getValue();

            if (range.test(buffer)) {
                populator.populate(world, buffer, biomes);
            }
        }
    }

    void append(Range r, GenerationPopulator pop){
        populatorMap.put(r, pop);
    }
}
