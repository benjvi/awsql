package com.benjvi.awsql.queryfilters;

import com.google.common.base.Strings;
import org.apache.commons.beanutils.PropertyUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Created by benjamin on 15/10/2017.
 */
public class ContainsFilter<R> {

    public List<Predicate<R>> buildPredicates(R resource) {
        List<Predicate<R>> predicates = new ArrayList<>();
        try {
            Map<String, Object> fields = PropertyUtils.describe(resource);

            fields.entrySet().stream()
                    .filter(e -> e.getValue() instanceof String && !Strings.isNullOrEmpty((String)e.getValue()))
                    .forEach(e -> predicates.add(getStringPropertyPredicate(e.getKey(), e.getValue())));
            fields.entrySet().stream()
                    .filter(e -> e.getValue() instanceof List && !((List)e.getValue()).isEmpty())
                    .forEach(e -> predicates.add(getListPropertyPredicate(e.getKey(), e.getValue())));
        } finally {
            return predicates;
        }
    }

    public Predicate<R> getStringPropertyPredicate(String key, Object value) {
        return resource -> {
            try {
                return ((String) PropertyUtils.getProperty(resource, key)).contains((String) value);
            } catch (Throwable t) {
                // just dont filter when errors occur
                return true;
            }
        };
    }

    public Predicate<R> getListPropertyPredicate(String key, Object value) {
        return resource -> {
            try {
                return ((List) PropertyUtils.getProperty(resource, key)).containsAll((List) value);
            } catch (Throwable t) {
                // just dont filter when errors occur
                return true;
            }
        };
    }
}
