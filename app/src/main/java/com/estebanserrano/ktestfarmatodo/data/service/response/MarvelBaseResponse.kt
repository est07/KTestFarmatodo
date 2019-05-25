package com.estebanserrano.ktestfarmatodo.data.service.response

class MarvelBaseResponse<T>(

    var code: Int,
    var status: String,
    var data: T?
)