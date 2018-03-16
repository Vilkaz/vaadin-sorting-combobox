package org.vaadin;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.data.provider.Query;
import com.vaadin.server.SerializablePredicate;

import java.util.Collection;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Vilkas on 08/03/2018.
 */
public class SortingDataProvider<T> extends ListDataProvider<T> {
    private List<BiPredicate<T, String>> exactFits;
    private String filterText;

    public SortingDataProvider(final String filterText, final Collection<T> items, List<BiPredicate<T, String>> exactFits) {
        super(items);
        this.filterText = filterText;
        this.exactFits = exactFits;
    }

    @Override
    public Stream<T> fetch(Query<T, SerializablePredicate<T>> query) {
        final List<T> items = super.fetch(query).collect(Collectors.toList());

        final Collection<T> fits = items.stream()
                .filter(i -> isItemInExactFits(i))
                .collect(Collectors.toList());

        if (!fits.isEmpty()) {
            fits.forEach(fit -> items.remove(fit));
            fits.addAll(items);
            return fits.stream();
        }
        return items.stream();
    }

    private boolean isItemInExactFits(final T i) {
        return exactFits
                .stream()
                .anyMatch(fit -> fit.test(i, filterText));
    }


}