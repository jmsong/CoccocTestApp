package com.coccoc.coccoctestapp.core;

import com.google.gson.Gson;
import com.google.inject.util.Types;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by tungtm on 1/24/17.
 */

public class MovieConverterFactory extends Converter.Factory {
    /**
     * Create an instance using a default {@link Gson} instance for conversion. Encoding to JSON and
     * decoding from JSON (when no charset is specified by a header) will use UTF-8.
     */
    public static MovieConverterFactory create() {
        return create(new Gson());
    }

    /**
     * Create an instance using {@code gson} for conversion. Encoding to JSON and
     * decoding from JSON (when no charset is specified by a header) will use UTF-8.
     */
    public static MovieConverterFactory create(Gson gson) {
        return new MovieConverterFactory(gson);
    }

    private final Gson gson;

    private MovieConverterFactory(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {
        Type movieType = Types.newParameterizedType(MovieResponse.class, type);
        Converter<ResponseBody, MovieResponse> delegate =
                retrofit.nextResponseBodyConverter(this, movieType, annotations);
        return new MovieResponseBodyConverter(delegate);
    }

//    @Override
//    public Converter<?, RequestBody> requestBodyConverter(Type type,
//                                                          Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
//        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
//        return new MovieResponseBodyConverter<>(gson, adapter);
//    }

    static class MovieResponseBodyConverter<T> implements Converter<ResponseBody, T> {
        final Converter<ResponseBody, MovieResponse<T>> delegate;

        MovieResponseBodyConverter(Converter<ResponseBody, MovieResponse<T>> delegate) {
            this.delegate = delegate;
        }

        @Override public T convert(ResponseBody responseBody) throws IOException {
            MovieResponse<T> response = delegate.convert(responseBody);
            return response.data;
        }
    }
}
