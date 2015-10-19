package com.olacabs.customer.p076d;

import com.olacabs.customer.utils.Utils;

/* compiled from: FoodListItem */
/* renamed from: com.olacabs.customer.d.bd */
public class bd implements dw {
    private FoodListItem attributes;
    private String description;
    private long id;
    private String image_url;
    private double listing_price;
    private FoodListItem merchant;
    private String name;
    private int quantity;
    private double sale_price;

    /* renamed from: com.olacabs.customer.d.bd.a */
    public class FoodListItem {
        private String cuisine;
        private String food_type;

        public String getFood_type() {
            return this.food_type;
        }

        public String getCuisine() {
            return this.cuisine;
        }
    }

    /* renamed from: com.olacabs.customer.d.bd.b */
    public class FoodListItem implements dw {
        private long id;
        private FoodListItem location;
        private String logo_url;
        private String name;

        /* renamed from: com.olacabs.customer.d.bd.b.a */
        public class FoodListItem implements dw {
            private String address_line_one;
            private String address_line_two;
            private String city;
            private long id;
            private String landmark;
            private String name;

            public long getId() {
                return this.id;
            }

            public String getName() {
                return this.name;
            }

            public String getAddress_line_one() {
                return this.address_line_one;
            }

            public String getAddress_line_two() {
                return this.address_line_two;
            }

            public String getLandmark() {
                return this.landmark;
            }

            public String getCity() {
                return this.city;
            }

            public boolean isValid() {
                return Utils.m14924g(this.address_line_one) && this.id != 0;
            }
        }

        public long getId() {
            return this.id;
        }

        public String getName() {
            return this.name;
        }

        public String getLogo_url() {
            return this.logo_url;
        }

        public FoodListItem getLocation() {
            return this.location;
        }

        public boolean isValid() {
            return Utils.m14924g(this.name) && this.id != 0 && this.location != null && this.location.isValid();
        }
    }

    public FoodListItem getAttributes() {
        return this.attributes;
    }

    public boolean isVeg() {
        return "veg".equalsIgnoreCase(this.attributes.getFood_type());
    }

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getImage_url() {
        return this.image_url;
    }

    public void setImage_url(String str) {
        this.image_url = str;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int i) {
        this.quantity = i;
    }

    public double getListing_price() {
        return this.listing_price;
    }

    public void setListing_price(int i) {
        this.listing_price = (double) i;
    }

    public double getSale_price() {
        return this.sale_price;
    }

    public void setSale_price(int i) {
        this.sale_price = (double) i;
    }

    public FoodListItem getMerchant() {
        return this.merchant;
    }

    public void setMerchant(FoodListItem foodListItem) {
        this.merchant = foodListItem;
    }

    public boolean isValid() {
        return Utils.m14924g(this.name) && Utils.m14924g(this.description) && this.id != 0 && this.merchant != null && this.merchant.isValid();
    }
}
