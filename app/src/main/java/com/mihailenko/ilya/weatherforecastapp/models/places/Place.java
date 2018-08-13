package com.mihailenko.ilya.weatherforecastapp.models.places;

import java.util.List;

/**
 * Created by Ilya on 12.06.2017.
 */

public class Place {
    private List<String> suggestions;

    public List<String> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<String> suggestions) {
        this.suggestions = suggestions;
    }
}
