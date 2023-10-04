package com.example.backend.common.response;

import java.util.Collection;
import java.util.Collections;

public class PaginationRS<E> {
    long total = 0L;
    Collection<E> items;

    public PaginationRS() {
    }

    public PaginationRS<E> setItems(Collection<E> items) {
        this.items = (Collection)(items == null ? Collections.emptyList() : items);
        return this;
    }

    public Collection<E> getItems() {
        return this.items;
    }

    public PaginationRS<E> setTotal(long total) {
        this.total = total;
        return this;
    }

    public long getTotal() {
        return this.total == 0L ? (long)this.items.size() : this.total;
    }
}
