package com.coccoc.coccoctestapp.core;

/**
 * Created by tungtm on 1/24/17.
 */

public class MovieResponse<T> {
    long id;
    T data;
    Object error;

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieResponse<?> that = (MovieResponse<?>) o;

        if (id != that.id) return false;
        if (data != null ? !data.equals(that.data) : that.data != null) return false;
        return !(error != null ? !error.equals(that.error) : that.error != null);
    }

    @Override public int hashCode() {
        int result1 = (int) (id ^ (id >>> 32));
        result1 = 31 * result1 + (data != null ? data.hashCode() : 0);
        result1 = 31 * result1 + (error != null ? error.hashCode() : 0);
        return result1;
    }
}
