package com.twobrothers.overcooked.core.lookups

@SuppressWarnings("unused")
enum class LookupMeasurementUnit(
    val id: String,
    val singular: String,
    val plural: String
) {
    ITEM("zPmZwAS5sT6gxeMrrrAY", " ", " "),
    RASHERS("1pq9A9Z24JucwiI5yCH0", " rasher of ", " rashes of "),
    TSP("4Uqpb6kmne7vc8hscrcQ", " tsp ", " tsp "),
    CUPS("51mMmatnM3zBuMKUw8Lv", " cup ", " cups "),
    STALK("7wkwHF8o1WbP96h8lQJU", " stalk of ", " stalks of "),
    HEAD("DpiRatRF1kakLPVLKVcQ", " head of ", " heads of "),
    GRAMS("PsS3Uj41GOo2VoKG0Azp", "g ", "g"),
    SHEETS("R7OdhwU4oFtk9nrnfk7F", " sheet of ", " sheets of "),
    TBSP("YXHJO1XkBmtaVyEzc0Mu", " tbsp ", " tbsp "),
    MILLILITRES("cGwAQdvKnbi9N6XJO5Y1", "ml ", "ml "),
    SLICE("eqPzkgi0KbJxgespgjDL", " slice ", " slices of "),
    SPRIG("lLMkjfY20Qg3suG2kZCe", " sprig ", " sprigs of "),
    BUNCH("suT9e7FCd8CkmR3zfOcu", " bunch of ", " bunches of ");

    companion object {
        fun getById(id: String) = values().firstOrNull { it.id == id }
    }
}