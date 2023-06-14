package com.example.network.models

import com.google.gson.annotations.SerializedName

data class Breed(
    val weight: Weight? = null,
    val id: String? = null,
    val name: String? = null,

    @SerializedName("cfa_url")
    val cfaURL: String? = null,

    @SerializedName("vetstreet_url")
    val vetstreetURL: String? = null,

    @SerializedName("vcahospitals_url")
    val vcahospitalsURL: String? = null,

    val temperament: String? = null,
    val origin: String? = null,

    @SerializedName("country_codes")
    val countryCodes: String? = null,

    @SerializedName("country_code")
    val countryCode: String? = null,

    val description: String? = null,

    @SerializedName("life_span")
    val lifeSpan: String? = null,

    val indoor: Long? = null,
    val lap: Long? = null,

    @SerializedName("alt_names")
    val altNames: String? = null,

    val adaptability: Long? = null,

    @SerializedName("affection_level")
    val affectionLevel: Long? = null,

    @SerializedName("child_friendly")
    val childFriendly: Long? = null,

    @SerializedName("dog_friendly")
    val dogFriendly: Long? = null,

    @SerializedName("energy_level")
    val energyLevel: Long? = null,

    val grooming: Long? = null,

    @SerializedName("health_issues")
    val healthIssues: Long? = null,

    val intelligence: Long? = null,

    @SerializedName("shedding_level")
    val sheddingLevel: Long? = null,

    @SerializedName("social_needs")
    val socialNeeds: Long? = null,

    @SerializedName("stranger_friendly")
    val strangerFriendly: Long? = null,

    val vocalisation: Long? = null,
    val experimental: Long? = null,
    val hairless: Long? = null,
    val natural: Long? = null,
    val rare: Long? = null,
    val rex: Long? = null,

    @SerializedName("suppressed_tail")
    val suppressedTail: Long? = null,

    @SerializedName("short_legs")
    val shortLegs: Long? = null,

    @SerializedName("wikipedia_url")
    val wikipediaURL: String? = null,

    val hypoallergenic: Long? = null,

    @SerializedName("reference_image_id")
    val referenceImageID: String? = null,

    @SerializedName("cat_friendly")
    val catFriendly: Long? = null,

    val bidability: Long? = null
)
