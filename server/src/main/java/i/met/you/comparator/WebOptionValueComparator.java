package i.met.you.comparator;

import java.util.Comparator;

import i.met.you.bean.WebOption;

public class WebOptionValueComparator implements Comparator<WebOption> {
    @Override
    public int compare(WebOption o1, WebOption o2) {
        return o1.getValue().compareTo(o2.getValue());
    }
}

