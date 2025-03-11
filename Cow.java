import utils. *;
/**
 * @Overview: A cow in a farm
 * @Attributes:
 * name String
 * age Integer int
 * breed String
 * 
 * @object: A typical Cow is c = <n,a,b>, where name(c) = n, age(c) = a, breed(c) = b
 * 
 * @abstract_properties
 * mutable(name) = true /\ optional(name) = false /\ length(name) = 30 /\
 * mutable(age) = true /\ optional(age) = false /\ min(age) = 1 /\ max(age) = 20 /\
 * mutable(breed) = true /\ optional(breed) = false /\ length(breed) = 30 /\
 */
public class Cow{
    @DomainConstraint (type = "String",mutable=true,optional=false, length=30)
    private String name;
    @DomainConstraint (type = "int",mutable=true,optional=false, min = 1, max = 20)
    private int age;
    @DomainConstraint (type = "String", mutable=true,optional=false, length=30)
    private String breed;
    @DOpt (type = OptType.Observer) @AttrRef("name")
    public String getName(){
        return name;
    }
    @DOpt (type = OptType.Observer) @AttrRef("age")    
    public int getAge(){
        return age;
    }
    @DOpt (type = OptType.Observer) @AttrRef("breed")
    public String getBreed(){
        return breed;
    }
    @DOpt (type = OptType.Mutator) @AttrRef("name")
    public void setName(String name){
        this.name = name;
    }
    @DOpt (type = OptType.Mutator) @AttrRef("age")    
    public void setAge(int age){
        this.age = age;
    }
    @DOpt (type = OptType.Mutator) @AttrRef("breed")
    public void setBreed(String breed){
        this.breed = breed;
    }
//Question7
/**
 * @effects
 *   if name,age,breed is valid
 *       initialize as Cow(name,age,breed)
 *   else 
 *      throw exception
 */
    public static boolean validateName(String name){
        return name != null && name.length() > 0 && name.length() <= 30;
    }
    public static boolean validateAge(int age){
        return age >= 1 && age <= 20;
    }
    public static boolean validateBreed(String breed){
        return breed != null && breed.length() > 0 && breed.length() <= 30;
    }
    public Cow(@AttrRef("name") String name,@AttrRef("age") int age,@AttrRef("breed") String breed) throws Exception{
            if (!validateName(name)) {
                throw new Exception("Invalid name: " + name);
            }
            if (!validateAge(age)) {
                throw new Exception("Invalid age: " + age);
            }
            if (!validateBreed(breed)) {
                throw new Exception("Invalid breed: " + breed);
            }
        this.name = name;
        this.age = age;
        this.breed = breed;
    }
}

