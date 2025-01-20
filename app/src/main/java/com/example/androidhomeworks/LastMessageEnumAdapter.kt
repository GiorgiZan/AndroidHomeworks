package com.example.androidhomeworks

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson

class LastMessageEnumAdapter  {

    @FromJson
    fun fromJson(reader: JsonReader): LastMessageEnum {
        val type = reader.nextString()
        return LastMessageEnum.fromString(type)
    }

    @ToJson
    fun toJson(writer: JsonWriter, value: LastMessageEnum?) {
        writer.value(value?.type)
    }
}
