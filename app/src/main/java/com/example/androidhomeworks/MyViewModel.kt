package com.example.androidhomeworks

import androidx.lifecycle.ViewModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class MyViewModel : ViewModel() {

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val jsonAdapter: JsonAdapter<List<ChatDto>> =
        moshi.adapter(Types.newParameterizedType(List::class.java, ChatDto::class.java))

    private var chatList: List<ChatDto>? = null

    private val json = """
        [
            {
                "id":1,
                "image":"https://www.alia.ge/wp-content/uploads/2022/09/grisha.jpg",
                "owner":"გრიშა ონიანი",
                "last_message":"თავის ტერიტორიას ბომბავდა",
                "last_active":"4:20 PM",
                "unread_messages":3,
                "is_typing":false,
                "laste_message_type":"text"
            },
            {
                "id":2,
                "image":null,
                "owner":"ჯემალ კაკაურიძე",
                "last_message":"შემოგევლე",
                "last_active":"3:00 AM",
                "unread_messages":0,
                "is_typing":true,
                "laste_message_type":"voice"
            },
            {
                "id":3,
                "image":"https://i.ytimg.com/vi/KYY0TBqTfQg/hqdefault.jpg",
                "owner":"გურამ ჯინორია",
                "last_message":"ცოცხალი ვარ მა რა ვარ შე.. როდის იყო კვტარი ტელეფონზე ლაპარაკობდა",
                "last_active":"1:00 ",
                "unread_messages":0,
                "is_typing":false,
                "laste_message_type":"file"
            },
            {
                "id":4,
                "image":"",
                "owner":"test",
                "last_message":"ადამიანი რო მოსაკლავად გაგიმეტებს თანაც ქალი ის დასანდობი არ არი",
                "last_active":"1:00 PM",
                "unread_messages":0,
                "is_typing":false,
                "laste_message_type":"text"
            }
        ]
    """

    fun loadChatList() {
        chatList = jsonAdapter.fromJson(json)
    }

    fun getChatList(): List<ChatDto>? {
        return chatList
    }

    fun filterChatsByName(name: String): List<ChatDto> {
        return chatList?.filter { it.owner.contains(name, ignoreCase = true) } ?: emptyList()
    }
}
