package kz.edu.astanait.classes;

public class Product {
    private int id;
    private String name;
    private double price;
    private String photo;
    private String category;
    private int user_id;

    public Product(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setPrice(builder.price);
        setPhoto(builder.photo);
        setCategory(builder.category);
        setUser_id(builder.user_id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public static class Builder{
        private int id;
        private String name;
        private double price;
        private String photo;
        private String category;
        private int user_id;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setCategory(String c)
        {
            this.category = c;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder setPhoto(String photo) {
            this.photo = photo;
            return this;
        }

        public Product build()
        {
            return new Product(this);
        }

        public Builder setUser_id(int user_id) {
            this.user_id = user_id;
            return this;
        }
    }
}
