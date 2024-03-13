package com.example.mvvm.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
/*
* Creado por Francisco Nolasco
* Año 2023 | 2024
* */
class ResponseComic() {
    @SerializedName("result")
    @Expose
    var result: String=""

    @SerializedName("token")
    @Expose
    var token: String =""

    @SerializedName("id")
    @Expose
    var id: String =""

    @SerializedName("details")
    @Expose
    var details: String =""



}