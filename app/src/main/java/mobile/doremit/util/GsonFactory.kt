package mobile.doremit.util

import com.google.gson.*
import java.lang.reflect.Type
import java.util.*

object GsonFactory {

    private val builder = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapter(Date::class.java, DateSerializer())

    fun getBuilder() = builder

    fun create() = builder.create()

    class DateSerializer : JsonSerializer<Date>, JsonDeserializer<Date> {

        private val milliMinLength = 13

        override fun serialize(src: Date, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
            return JsonPrimitive(src.time / 1000)
        }

        override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Date {
            val value = json.asDouble
            val longValue = value.toLong()
            if (longValue.toString().length >= milliMinLength) {
                return Date(longValue)
            }

            return Date((value * 1000).toLong())
        }
    }
}