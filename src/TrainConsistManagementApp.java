public class TrainConsistManagementApp {
    static class Bogie {
        String bogieId;
        String bogieType; // "Passenger" or "Cargo"
        int capacity;

        public Bogie(String bogieId, String bogieType, int capacity) {
            this.bogieId = bogieId;
            this.bogieType = bogieType;
            this.capacity = capacity;
        }
    }

    static class Train {
        String trainId;
        String name;
        java.util.List<Bogie> bogies = new java.util.ArrayList<>();

        public Train(String trainId, String name) {
            this.trainId = trainId;
            this.name = name;
        }

        public void addPassengerBogie(Bogie bogie) {
            if ("Passenger".equals(bogie.bogieType)) {
                bogies.add(bogie);
                System.out.println("Added Passenger Bogie: " + bogie.bogieId);
            }
        }
    }

    public static void main(String[] args) {
        Train train = new Train("TR001", "Express");
        train.addPassengerBogie(new Bogie("B001", "Passenger", 80));
    }
}