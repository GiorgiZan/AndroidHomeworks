package com.example.androidhomeworks.field

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidhomeworks.box.FieldBox
import kotlinx.serialization.json.Json

class FieldsViewModel : ViewModel() {
    private val json = """
        [
            [
                {
                    "field_id": 1,
                    "hint": "UserName",
                    "field_type": "input",
                    "keyboard": "text",
                    "required": true,
                    "is_active": true,
                    "icon": "https://jemala.png"
                },
                {
                    "field_id": 2,
                    "hint": "Email",
                    "field_type": "input",
                    "keyboard": "text",
                    "required": true,
                    "is_active": true,
                    "icon": "https://jemala.png"
                }
            ],
            [
                {
                    "field_id": 3,
                    "hint": "Phone",
                    "field_type": "input",
                    "keyboard": "number",
                    "required": false,
                    "is_active": true,
                    "icon": "https://jemala.png"
                }
            ],
            [
                {
                    "field_id": 3,
                    "hint": "Phone",
                    "field_type": "input",
                    "keyboard": "number",
                    "required": false,
                    "is_active": true,
                    "icon": "https://jemala.png"
                },
                {
                    "field_id": 4,
                    "hint": "Birthday",
                    "field_type": "chooser",
                    "required": false,
                    "is_active": true,
                    "icon": "https://jemala.png"
                },
                {
                    "field_id": 4,
                    "hint": "Gender",
                    "field_type": "chooser",
                    "required": false,
                    "is_active": true,
                    "icon": "https://jemala.png"
                }
            ]
        ]
    """

    private val _fieldContainers: MutableLiveData<List<FieldBox>> = MutableLiveData(
        Json.decodeFromString<List<List<Field>>>(json).map { FieldBox(it) }
    )
    val fieldContainers: LiveData<List<FieldBox>> get() = _fieldContainers

}