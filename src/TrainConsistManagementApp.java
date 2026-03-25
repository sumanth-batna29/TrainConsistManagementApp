public class TrainConsistManagementApp {
    static class Train {
        String trainId;
        String name;
        int totalBogies;

        public Train(String trainId, String name, int totalBogies) {
            this.trainId = trainId;
            this.name = name;
            this.totalBogies = totalBogies;
        }

        public void displayConsistSummary() {
            System.out.println("Train ID: " + trainId);
            System.out.println("Train Name: " + name);
            System.out.println("Total Bogies: " + totalBogies);
        }
    }

    public static void main(String[] args) {
        Train train = new Train("TR001", "Express", 10);
        train.displayConsistSummary();
    }
}