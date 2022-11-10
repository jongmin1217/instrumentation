package com.bellminp.instrumentation.mapper

interface PresentationMapper<D, P> {

    fun mapToDomain(from: P): D

    fun mapToPresentation(from: D): P

}