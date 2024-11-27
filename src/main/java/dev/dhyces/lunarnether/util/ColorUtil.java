package dev.dhyces.lunarnether.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.Range;

public class ColorUtil {
    public static final Codec<Integer> HEX_COLOR = Codec.STRING.flatXmap(
            s -> {
                if (!s.startsWith("#")) {
                    return DataResult.error(() -> "Color must start with '#'.");
                }
                try {
                    return DataResult.success(Integer.decode(s));
                } catch (NumberFormatException e) {
                    return DataResult.error(() -> "Could not parse as hex color code.");
                }
            },
            integer -> DataResult.success("#" + Integer.toHexString(integer & 0xFFFFFF))
    );

    public enum ColorSpace implements StringRepresentable {
        RGB("rgb") {
            @Override
            public int interpolate(int rgbColorA, int rgbColorB, float delta) {
                int r = (int) Mth.lerp(delta, FastColor.ARGB32.red(rgbColorA), FastColor.ARGB32.red(rgbColorB));
                int g = (int) Mth.lerp(delta, FastColor.ARGB32.green(rgbColorA), FastColor.ARGB32.green(rgbColorB));
                int b = (int) Mth.lerp(delta, FastColor.ARGB32.blue(rgbColorA), FastColor.ARGB32.blue(rgbColorB));
                return FastColor.ARGB32.color(0xFF, r, g, b);
            }
        };

        public static final Codec<ColorSpace> CODEC = StringRepresentable.fromEnum(ColorSpace::values);

        private final String safeName;

        ColorSpace(String safeName) {
            this.safeName = safeName;
        }

        @Override
        public String getSerializedName() {
            return safeName;
        }

        public abstract int interpolate(int rgbColorA, int rgbColorB, float delta);
    }

    // Based on https://tannerhelland.com/2012/09/18/convert-temperature-rgb-algorithm-code.html by Tanner Helland
    /**
     *
     * @param kelvin Only guaranteed correct values for inputs from 1000 to 40000
     * @return packed ARGB32 int
     */
    public static int getTemperatureColor(@Range(from = 1000, to = 40000) int kelvin) {

        float temp = kelvin / 100f;

        float red = 255;
        float green;
        float blue = 255;

        if (temp > 66) {
            red = temp - 60;
            red = (float) (329.698727446 * Math.pow(red, -0.1332047592));
            red = Mth.clamp(red, 0, 255);
        }

        if (temp <= 66) {
            green = temp;
            green = (float) (99.4708025861 * Math.log(green) - 161.1195681661);
            green = Mth.clamp(green, 0, 255);
        } else {
            green = temp - 60;
            green = (float) (288.1221695283 * Math.pow(green, -0.0755148492));
            green = Mth.clamp(green, 0, 255);
        }

        if (temp < 66) {
            if (temp <= 19) {
                blue = 0;
            } else {
                blue = temp - 10;
                blue = (float) (138.5177312231 * Math.log(blue) - 305.0447927307);
                blue = Mth.clamp(blue, 0, 255);
            }
        }

        return FastColor.ARGB32.color(0xFF, (int) red, (int) green, (int) blue);
    }
}
