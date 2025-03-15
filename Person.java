
import utils.*;

/**
 * @overview A person has a phone
 *
 * @attributes ID Interger int Name String String Phone MobilePhone
 *
 *
 * @object A typical person is (id,name,phone)
 *
 * @abstract_properties mutable(ID)=false /\ optional(ID) = false /\ min(ID) = 1
 * /\ mutable(name)=false /\ optional(name) = false /\ min(name) = 1 /\
 * max(name) = 30 /\ mutable(phone)=true /\ optional(phone) = true /\
 */
public class Person {

    @DomainConstraint(type = "int", mutable = false, optional = false, min = 1)
    private int id;
    @DomainConstraint(type = "string", mutable = false, optional = false, min = 1, max = 30)
    private String name;
    @DomainConstraint(type = "string", mutable = true, optional = true)
    private MobilePhone phone;

    /**
     * @effects if hp, id,n,p are valid initialize as <tt>(id,n,p)</tt>
     * else throw Exception
     */
    public Person(@AttrRef("id") int id, @AttrRef("name") String name, @AttrRef("phone") MobilePhone phone) {
        setId(id);
        setName(name);
        setPhone(phone);
    }

    /**
     * @effects return id
     */
    @DOpt(type = OptType.Mutator)
    @AttrRef("ID")
    public int getId() {
        return id;
    }

    /**
     * @effects return name
     */
    @DOpt(type = OptType.Observer)
    @AttrRef("name")
    public String getName() {
        return name;
    }

    /**
     * @effects return phone
     */
    @DOpt(type = OptType.Observer)
    @AttrRef("phone")
    public MobilePhone getPhone() {
        return phone;
    }

    /**
     * @effects if id is valid set this.id = id else throw Exception
     */
    @DOpt(type = OptType.Mutator)
    @AttrRef("ID")
    public void setId(int id) {
        if (id >= 1) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("Invalid ID. Please enter a number greater than 0.");
        }
    }

    /**
     * @effects return true if name is valid (name must consist of at least two
     * words that are separated by a white space)
     */
    @DOpt(type = OptType.Helper)
    @AttrRef("name")
    private boolean isValidName(String name) {
        return name != null && name.trim().split("\\s+").length >= 2;
    }

    /**
     * @effects if name is not valid throw Exception else this.name = name
     */
    @DOpt(type = OptType.Mutator)
    @AttrRef("name")
    public void setName(String name) {
        if (!isValidName(name)) {
            throw new IllegalArgumentException("Name must consist of at least two words separated by spaces.");
        }
        this.name = name;
    }

    /**
     * @effects set this.phone = phone
     */
    @DOpt(type = OptType.Mutator)
    @AttrRef("phone")
    public void setPhone(MobilePhone phone) {
        this.phone = phone;
    }

    /**
     * @effects return "Hello, my name is " + name
     */
    @DOpt(type = OptType.Default)
    @AttrRef("name")
    public String greeting() {
        return "Hello, my name is " + name;
    }

    /**
     * @effects return Person as String
     */
    @DOpt(type = OptType.Default)
    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", phone=" + phone + "]";
    }

}
