package me.mical.cleaneraddon.utils;

import org.serverct.parrot.parrotx.utils.i18n.I18n;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapUtil {

    public static List<String> formatMap(final Map<?, ?> map, final String typeName) {
        final List<String> results = new ArrayList<>();
        results.add(I18n.color(map.isEmpty() ? "&fMap &d{0} &f中没有数据." : "&fMap &d{0} &f的数据", typeName));
        map.forEach((key, value) -> results.add(
                I18n.color(
                        "&9- &f值 &c{0} &f(&9{1}&f) &a<- &f键 &e{2} &f(&9{3}&f)",
                        value.toString(),
                        value.getClass().getSimpleName() + ".class",
                        key.toString(),
                        key.getClass().getSimpleName() + ".class"
                )
        ));
        return results;
    }
}
