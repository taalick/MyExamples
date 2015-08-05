package serializableExamples;

/**
 * Created by алла on 05.08.15.
 */
    public class Asset {
        public Asset(String name) {
            this.name = name;
        }

        private String name;
        private double price;

        public String getName() {
            return name;
        }

        public void setName(String name){ this.name = name;}

        public double getPrice() {
            return price;
        }
    }


