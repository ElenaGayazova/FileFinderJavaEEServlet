package servlet;

import java.util.Arrays;
import java.util.Map;

public class Utils {

    /**
     * обрамляем вывод элементов списка тегами ul или ol
     * @param list
     * @param tag
     * @return
     */
    public static String wrapList(String list, String tag) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n<"+tag+">\n").append(list).append("</"+tag+">\n");
        return sb.toString();
    }

    /**
     * рекурсивно выводим список
     * @param current
     * @return
     */
    public static String writeTree(Map<String, Object> current, String tag) {
        StringBuilder sb = new StringBuilder();
        if(current==null) return "";

        // содержимое каталога выводим как элементы списке <li>name<li>
        String[] list = current.keySet().toArray(new String[current.size()]);
        Arrays.sort(list);

        for(String name: list) {
            sb.append("<li>");
            Object value = current.get(name);
            if(value instanceof Map) {
                String subtree = writeTree((Map<String, Object>)value, tag);
                sb.append(name).append(wrapList(subtree, tag));
            } else {
                sb.append(name);
            }
            sb.append("</li>\n");
        }
        return sb.toString();
    }
}
