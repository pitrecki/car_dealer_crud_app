package org.pitrecki.car_dealer_crud_app.helpers;

import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParamEntries {
    private final List<ParamEntry> entries = new ArrayList<>();

    private ParamEntries() {
    }

    public ParamEntries add(ParamEntry entry) {
        entries.add(entry);
        return this;
    }

    public static ParamEntries aParamEntries() {
        return new ParamEntries();
    }


    public MultiValueMap<String, String> collect() {
        Map<String, List<String>> collect = entries.stream()
                .collect(Collectors.toMap(o -> o.key, o -> o.params));
        return new MultiValueMapAdapter<>(collect);
    }

    public static class ParamEntry {
        private final String key;
        private final List<String > params;

        private ParamEntry(String key, List<String> params) {
            this.key = key;
            this.params = params;
        }

        public static ParamEntry entry(String key, String ... params) {
            return new ParamEntry(key, List.of(params));
        }
    }

}
