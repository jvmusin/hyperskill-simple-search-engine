package search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AllWordsSearchStrategy implements SearchStrategy {
    @Override
    public List<String> find(Dictionary dictionary, String[] words) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (String word : words) dictionary.getLinesByWord(word).forEach(i -> counter.merge(i, 1, Integer::sum));
        return counter.entrySet().stream().filter(e -> e.getValue() == words.length)
                .map(Map.Entry::getKey)
                .map(dictionary::getLine)
                .collect(Collectors.toList());
    }
}
