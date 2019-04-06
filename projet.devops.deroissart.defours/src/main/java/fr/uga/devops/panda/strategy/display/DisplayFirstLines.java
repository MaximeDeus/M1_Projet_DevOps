package fr.uga.devops.panda.strategy.display;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DisplayFirstLines implements Display_Itf {

    public static final DisplayFirstLines DISPLAYER = new DisplayFirstLines(); //Singleton

    private DisplayFirstLines() {
    }

    @Override
    public List<HashMap<String, Object>> selectLines(List<HashMap<String, Object>> datas, int nb_lines) {
        List<HashMap<String, Object>> res = new ArrayList<>();
        int index;
        for (index = 0; index < nb_lines; index++) {
            res.add(datas.get(index));
        }
        return res;
    }
}
