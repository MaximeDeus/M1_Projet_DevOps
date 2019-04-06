package fr.uga.devops.panda.strategy.display;

import java.util.HashMap;
import java.util.List;

/**
 * Strategy pattern used to display wanted Lines (all lines, 5 first,2 last...)
 */
public interface Display_Itf {

    List<HashMap<String, Object>> selectLines(List<HashMap<String, Object>> datas, int nb_lines);
}
