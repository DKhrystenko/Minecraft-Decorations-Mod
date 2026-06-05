package danylo.mod.block;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// @CutoutBlock.java
// Used to correctly apply transparency for yuushya blocks(without it the transparent parts are displayed as black textures).
// Needed because "render_type": "cutout" in model.json doesn't work.
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CutoutBlock {}
