package util;

import java.util.Iterator;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.BiConsumer;
import java.util.regex.Pattern;

public class ResourceParser implements Iterable<String> {

    private ResourceBundle myResources;

    public ResourceParser(String path) {
        myResources = ResourceBundle.getBundle(path);
    }

    public String getString(String key) {
        return myResources.getString(key);
    }

    public void parseAll(BiConsumer<String, String> operation) {
        forEach(key -> operation.accept(key, getString(key)));
    }


    public Optional<String> getKey(String name) {
        for(String key: this) {
            if(Pattern.matches(myResources.getString(key), name));
            return Optional.of(key);
        }
        return Optional.empty();
    }

    @Override
    public Iterator<String> iterator() {
        return myResources.keySet().iterator();
    }
    
}
