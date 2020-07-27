package search;

import java.util.List;

public interface SearchStrategy {
    List<String> find(Dictionary dictionary, String[] words);
}
