package com.euwbah.bouncing_ball;

import java.util.ArrayList;

/**
 * For display operations
 */
public class Graphics {
    public static ArrayList<String> replace(ArrayList<String> current, Vector2 index, String replacement) {
        try {
            ArrayList<String> returnable = new ArrayList<>();

            for (int y = 0; y < current.size(); y++) {
                if (y == index.roundY())
                    returnable.add(replace(current.get(y), index.roundX(), replacement));
                else
                    returnable.add(current.get(y));
            }

            return returnable;
        } catch (IndexOutOfBoundsException e) {
            //Move along...
        }

        return (ArrayList<String>) current.clone();
    }

    public static String replace(String current, int xposition, String replacement) {
        return current.substring(0, xposition) + replacement + current.substring(xposition + replacement.length());
    }
}
