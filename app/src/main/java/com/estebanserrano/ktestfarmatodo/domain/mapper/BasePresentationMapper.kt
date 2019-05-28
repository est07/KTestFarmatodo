package com.estebanserrano.ktestfarmatodo.domain.mapper

interface BasePresentationMapper<D, P> {

    fun transform(DomainType: D): P

    fun transformToResponse(PresentationType: P): D
}