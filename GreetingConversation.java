public class GreetingConversation{
    public static void main(String[] args){
        MobilePhone phone1 = new MobilePhone("Pham Nhat Quang","M-IPH-140", Color.Bl, 2023,true);
        Person p1 = new Person(1, "Pham Nhat Quang", phone1);
        System.out.println(p1.greeting());
        System.out.println(phone1);
    }
}