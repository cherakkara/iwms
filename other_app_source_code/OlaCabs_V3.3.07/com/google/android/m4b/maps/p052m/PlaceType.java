package com.google.android.m4b.maps.p052m;

import android.os.Parcel;
import com.google.android.m4b.maps.p037h.C0591c;
import com.google.android.m4b.maps.p047g.Preconditions;

/* renamed from: com.google.android.m4b.maps.m.i */
public final class PlaceType implements C0591c {
    public static final PlaceTypeCreator CREATOR;
    final int f7511a;
    final String f7512b;

    static {
        PlaceType.m10692a("accounting");
        PlaceType.m10692a("airport");
        PlaceType.m10692a("amusement_park");
        PlaceType.m10692a("aquarium");
        PlaceType.m10692a("art_gallery");
        PlaceType.m10692a("atm");
        PlaceType.m10692a("bakery");
        PlaceType.m10692a("bank");
        PlaceType.m10692a("bar");
        PlaceType.m10692a("beauty_salon");
        PlaceType.m10692a("bicycle_store");
        PlaceType.m10692a("book_store");
        PlaceType.m10692a("bowling_alley");
        PlaceType.m10692a("bus_station");
        PlaceType.m10692a("cafe");
        PlaceType.m10692a("campground");
        PlaceType.m10692a("car_dealer");
        PlaceType.m10692a("car_rental");
        PlaceType.m10692a("car_repair");
        PlaceType.m10692a("car_wash");
        PlaceType.m10692a("casino");
        PlaceType.m10692a("cemetery");
        PlaceType.m10692a("church");
        PlaceType.m10692a("city_hall");
        PlaceType.m10692a("clothing_store");
        PlaceType.m10692a("convenience_store");
        PlaceType.m10692a("courthouse");
        PlaceType.m10692a("dentist");
        PlaceType.m10692a("department_store");
        PlaceType.m10692a("doctor");
        PlaceType.m10692a("electrician");
        PlaceType.m10692a("electronics_store");
        PlaceType.m10692a("embassy");
        PlaceType.m10692a("establishment");
        PlaceType.m10692a("finance");
        PlaceType.m10692a("fire_station");
        PlaceType.m10692a("florist");
        PlaceType.m10692a("food");
        PlaceType.m10692a("funeral_home");
        PlaceType.m10692a("furniture_store");
        PlaceType.m10692a("gas_station");
        PlaceType.m10692a("general_contractor");
        PlaceType.m10692a("grocery_or_supermarket");
        PlaceType.m10692a("gym");
        PlaceType.m10692a("hair_care");
        PlaceType.m10692a("hardware_store");
        PlaceType.m10692a("health");
        PlaceType.m10692a("hindu_temple");
        PlaceType.m10692a("home_goods_store");
        PlaceType.m10692a("hospital");
        PlaceType.m10692a("insurance_agency");
        PlaceType.m10692a("jewelry_store");
        PlaceType.m10692a("laundry");
        PlaceType.m10692a("lawyer");
        PlaceType.m10692a("library");
        PlaceType.m10692a("liquor_store");
        PlaceType.m10692a("local_government_office");
        PlaceType.m10692a("locksmith");
        PlaceType.m10692a("lodging");
        PlaceType.m10692a("meal_delivery");
        PlaceType.m10692a("meal_takeaway");
        PlaceType.m10692a("mosque");
        PlaceType.m10692a("movie_rental");
        PlaceType.m10692a("movie_theater");
        PlaceType.m10692a("moving_company");
        PlaceType.m10692a("museum");
        PlaceType.m10692a("night_club");
        PlaceType.m10692a("painter");
        PlaceType.m10692a("park");
        PlaceType.m10692a("parking");
        PlaceType.m10692a("pet_store");
        PlaceType.m10692a("pharmacy");
        PlaceType.m10692a("physiotherapist");
        PlaceType.m10692a("place_of_worship");
        PlaceType.m10692a("plumber");
        PlaceType.m10692a("police");
        PlaceType.m10692a("post_office");
        PlaceType.m10692a("real_estate_agency");
        PlaceType.m10692a("restaurant");
        PlaceType.m10692a("roofing_contractor");
        PlaceType.m10692a("rv_park");
        PlaceType.m10692a("school");
        PlaceType.m10692a("shoe_store");
        PlaceType.m10692a("shopping_mall");
        PlaceType.m10692a("spa");
        PlaceType.m10692a("stadium");
        PlaceType.m10692a("storage");
        PlaceType.m10692a("store");
        PlaceType.m10692a("subway_station");
        PlaceType.m10692a("synagogue");
        PlaceType.m10692a("taxi_stand");
        PlaceType.m10692a("train_station");
        PlaceType.m10692a("travel_agency");
        PlaceType.m10692a("university");
        PlaceType.m10692a("veterinary_care");
        PlaceType.m10692a("zoo");
        PlaceType.m10692a("administrative_area_level_1");
        PlaceType.m10692a("administrative_area_level_2");
        PlaceType.m10692a("administrative_area_level_3");
        PlaceType.m10692a("colloquial_area");
        PlaceType.m10692a("country");
        PlaceType.m10692a("floor");
        PlaceType.m10692a("geocode");
        PlaceType.m10692a("intersection");
        PlaceType.m10692a("locality");
        PlaceType.m10692a("natural_feature");
        PlaceType.m10692a("neighborhood");
        PlaceType.m10692a("political");
        PlaceType.m10692a("point_of_interest");
        PlaceType.m10692a("post_box");
        PlaceType.m10692a("postal_code");
        PlaceType.m10692a("postal_code_prefix");
        PlaceType.m10692a("postal_town");
        PlaceType.m10692a("premise");
        PlaceType.m10692a("room");
        PlaceType.m10692a("route");
        PlaceType.m10692a("street_address");
        PlaceType.m10692a("sublocality");
        PlaceType.m10692a("sublocality_level_1");
        PlaceType.m10692a("sublocality_level_2");
        PlaceType.m10692a("sublocality_level_3");
        PlaceType.m10692a("sublocality_level_4");
        PlaceType.m10692a("sublocality_level_5");
        PlaceType.m10692a("subpremise");
        PlaceType.m10692a("transit_station");
        PlaceType.m10692a("other");
        CREATOR = new PlaceTypeCreator();
    }

    private static PlaceType m10692a(String str) {
        return new PlaceType(0, str);
    }

    PlaceType(int i, String str) {
        Preconditions.m10461a(str);
        this.f7511a = i;
        this.f7512b = str;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof PlaceType) && this.f7512b.equals(((PlaceType) obj).f7512b);
    }

    public final int hashCode() {
        return this.f7512b.hashCode();
    }

    public final String toString() {
        return this.f7512b;
    }

    public final int describeContents() {
        PlaceTypeCreator placeTypeCreator = CREATOR;
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        PlaceTypeCreator placeTypeCreator = CREATOR;
        PlaceTypeCreator.m10693a(this, parcel);
    }
}
