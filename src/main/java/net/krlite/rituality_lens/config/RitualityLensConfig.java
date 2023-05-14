package net.krlite.rituality_lens.config;

import net.fabricmc.loader.api.FabricLoader;
import net.krlite.pierced.annotation.Silent;
import net.krlite.pierced.annotation.Table;
import net.krlite.pierced.config.Pierced;
import net.krlite.rituality_lens.RitualityLens;

import java.io.File;

public class RitualityLensConfig extends Pierced {
	@Silent
	private static final File file = FabricLoader.getInstance().getConfigDir().resolve("rituality_lens.toml").toFile();

	public RitualityLensConfig() {
		super(RitualityLensConfig.class, file);
	}

	{
		load();
		save();
	}

	@Silent
	public final float gap = 0.001F;
	@Table("StartRidingAnimation")
	public float lerpSpeed = 0.359F;
}
