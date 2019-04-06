package fr.uga.devops.panda.strategy.display;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DisplayLastLines implements Display_Itf {

    public static final DisplayLastLines DISPLAYER = new DisplayLastLines(); //Singleton

    private DisplayLastLines() {
    }

    @Override
    public List<HashMap<String, Object>> selectLines(List<HashMap<String, Object>> datas, int nb_lines) {
        List<HashMap<String, Object>> res = new ArrayList<>();
        int index;
        for (index = datas.size() - nb_lines; index < datas.size(); index++) {
            res.add(datas.get(index));
        }
        return res;
    }
}
