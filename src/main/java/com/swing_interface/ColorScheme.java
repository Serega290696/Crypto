package com.swing_interface;

import java.awt.*;
import java.lang.reflect.Field;

/**
 * Created by Serega on 01.08.2015.
 */
public abstract class ColorScheme {

    static byte scheme = 1;
    static Color[] color1 = {
            new Color(106, 166, 230),
            new Color(50, 163, 233),
            new Color(41, 147, 208),
    };
    static Color[] color2 = {
            new Color(50, 163, 233),
    };
    static Color[] color3 = {
            new Color(41, 147, 208),
    };
    static Color[] color4 = {
            new Color(41, 147, 208),
    };
    static Color[] color5 = {
            new Color(41, 147, 208),
    };

    public static Color getColor1() {
        if(scheme >= color1.length)
            return color1[color1.length-1];
        return color1[scheme];
    }
//    public static Color getColor2() {
//        if(scheme >= color1.length)
//            return color1[color1.length-1];
//        return color1[scheme];
//    }
//    public static Color getColor3() {
//        if(scheme >= color3.length)
//            return color3[color3.length-1];
//        return color3[scheme];
//    }
    public static Color getColor(byte colorNum) {
        Color[] colors;
        Class c = ColorScheme.class;
        try {
            Field field = c.getDeclaredField("color" + colorNum);
            colors = (Color[]) field.get(new ColorScheme() {});
            if(scheme >= colors.length)
                return colors[colors.length-1];
            return colors[scheme];
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
