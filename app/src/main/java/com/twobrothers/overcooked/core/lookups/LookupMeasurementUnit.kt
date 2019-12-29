package com.twobrothers.overcooked.core.lookups

@SuppressWarnings("unused")
enum class LookupMeasurementUnit(
    val id: String,
    val description: String
) {
    ITEM("zPmZwAS5sT6gxeMrrrAY", "item"),
    GRAMS("PsS3Uj41GOo2VoKG0Azp", "g"),
    MILLILITRES("cGwAQdvKnbi9N6XJO5Y1", "ml"),
    TSP("4Uqpb6kmne7vc8hscrcQ", "tsp"),
    TBSP("YXHJO1XkBmtaVyEzc0Mu", "Tbsp"),
    CUPS("51mMmatnM3zBuMKUw8Lv", "cup"),
    STALK("7wkwHF8o1WbP96h8lQJU", "stalk of"),
    BUNCH("suT9e7FCd8CkmR3zfOcu", "bunch of"),
    SPRIG("lLMkjfY20Qg3suG2kZCe", "sprig"),
    SLICE("eqPzkgi0KbJxgespgjDL", "slice"),
    RASHERS("1pq9A9Z24JucwiI5yCH0", "rasher of"),
    HEAD("DpiRatRF1kakLPVLKVcQ", "head of"),
    SHEETS("R7OdhwU4oFtk9nrnfk7F", "sheet of");

    companion object {
        fun getById(id: String) = values().firstOrNull { it.id == id }
    }
}