package course;

import servlet.Utils;

import javax.ejb.Stateless;
import java.io.File;
import java.util.*;

@Stateless
public class DirectoryServiceBean implements DirectoryService {

    private int scanDepthLimit = 3;

    /**
     * рекурсивно читаем содержимое каталога
     * дерево сохраняем в виде Map в которой key - имя файла ил каталога
     * а value - либо строка (если это файл), либо Map с содержимым подкаталога
     * @param current - директория содержимое которой нужно прочитать
     * @return Map с деревом
     */
    private Map<String, Object> scan(File current, int depth) {
        if(depth>=scanDepthLimit) return null;

        Map res = new HashMap();
        File[] list = current.listFiles();
        if(list==null) return null;
        for(File f: list) {
            String fname = f.getName();
            //System.out.println(fname);
            // пропускаем "фиктивные" каталоги "." и ".."
            if(fname.equals(".") || fname.equals("..")) continue;
            if(f.isDirectory()) {
                res.put(fname,scan(f, depth+1)); // сохраняем содержимое подкаталога
            } else {
                res.put(fname,"."); // сохраняем имя файла
            }
        }
        return res;
    }

    /**
     * сканирование домашнего каталога
     * @return возвращает структуру домашнего каталога в виде Map<String, Object>
     */
    public Map<String, Object> getHomeDir(){
        File fHomeDir = new File(System.getProperty("user.home"));
        Map tree = scan(fHomeDir, 0);

        return tree;
    }


    public static void main(String[] args){
        DirectoryServiceBean s = new DirectoryServiceBean();
        System.out.println(Utils.writeTree(s.getHomeDir(),"ol"));
    }
}
