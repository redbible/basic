package mobile.nftf.util

import com.google.gson.*
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*

object GsonFactory {

    private val builder = GsonBuilder()
        .registerTypeAdapter(Date::class.java, DateSerializer())

    fun create() = builder.create()

    class DateSerializer : JsonSerializer<Date>, JsonDeserializer<Date> {
        private var format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.000+09:00'")

        override fun serialize(src: Date, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
            return JsonPrimitive(format.format(src).toString())
        }

        override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Date {
            val value = json.asString

            return try {
                format.parse(value)
            } catch (e: Exception) {
                Date()
            }
        }
    }
}