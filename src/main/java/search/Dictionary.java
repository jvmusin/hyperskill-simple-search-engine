package search;

import java.util.*;

import static java.util.Collections.emptySet;

public class Dictionary {
    private final List<String> lines;
    private final Map<String, Set<Integer>> wordToLines;

    public Dictionary(List<String> lines) {
        this.lines = lines;
        wordToLines = new HashMap<>();
        for (int i = 0; i < lines.size(); i++) {
            for (String word : lines.get(i).toLowerCase().split(" ")) {
                wordToLines.computeIfAbsent(word, k -> new TreeSet<>()).add(i);
            }
        }
    }

    public List<String> getLines() {
        return lines;
    }

    public Set<Integer> getLinesByWord(String word) {
        return wordToLines.getOrDefault(word.toLowerCase(), emptySet());
    }

    public String getLine(int index) {
        return lines.get(index);
    }

    public int getLineCount() {
        return lines.size();
    }
}
