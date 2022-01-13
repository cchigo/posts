package com.example.rxjavaapp.data.model.mapper;


public interface BaseModelMapper<T, F> {

    public T from(F data);
    public F to(T data);
}


