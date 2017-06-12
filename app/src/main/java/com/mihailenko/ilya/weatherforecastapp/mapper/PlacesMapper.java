package com.mihailenko.ilya.weatherforecastapp.mapper;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.TypeRef;
import com.mihailenko.ilya.weatherforecastapp.data.models.places.Place;


import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PlacesMapper implements JsonDeserializer<Place> {
    private final Configuration configuration;
    private final TypeRef<List<String>> mListStringType;

    @Inject
    public PlacesMapper(Configuration configuration) {
        this.configuration = configuration;
        mListStringType = new TypeRef<List<String>>() {
        };
    }

    @Override
    public Place deserialize(JsonElement jsonElement,
                             Type typeOfT,
                             JsonDeserializationContext context) throws JsonParseException {
        String json = jsonElement.toString();
        final Place place = new Place();

        place.setSuggestions(JsonPath
                .using(configuration)
                .parse(json)
                .read("$.predictions[*].terms[0].value", mListStringType));

        return place;
    }

}