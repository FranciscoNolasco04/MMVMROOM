package com.example.mvvm.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
/*
* Creado por Francisco Nolasco
* Año 2023 | 2024
* */
class ResponseRegistro() {

    @SerializedName("result")
    @Expose
    var result: String=""

    @SerializedName("insert_id")
    @Expose
    var insert_id: String =""

}