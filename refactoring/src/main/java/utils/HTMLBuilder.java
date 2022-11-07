package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HTMLBuilder<T> {
    private final List<String> content = new ArrayList<>();

    public HTMLBuilder<T> addHeader(String header) {
        content.add(String.format("<h1>%s</h1>", header));
        return this;
    }

    public HTMLBuilder<T> addNewRawLine(String rawLine) {
        content.add(rawLine);
        return this;
    }

    public HTMLBuilder<T> addNewLines(List<T> newLinesValues) {
        content.addAll(newLinesValues.stream().map(it -> it.toString() + "</br>").collect(Collectors.toList()));
        return this;
    }

    public String build() {
        StringBuilder result = new StringBuilder();
        result.append("<html><body>\n");
        for (String line : content) {
            result.append(line).append("\n");
        }
        result.append("</body></html>");
        return result.toString();
    }
}
