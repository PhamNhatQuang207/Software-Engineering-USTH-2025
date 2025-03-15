public class TestCow{
    public static void main(String[] args){
        try {
            Cow cow = new Cow("Cow", 40, "Breed");
            System.out.println("Name: " + cow.getName());
            System.out.println("Age: " + cow.getAge());
            System.out.println("Breed: " + cow.getBreed());
        } catch (Exception e) {
            System.err.println("Failed to create Cow object: " + e.getMessage());
        }
    }
}