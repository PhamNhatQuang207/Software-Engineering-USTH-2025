import utils. *;
/**
 * @overview An armored vehicle in a game
 * 
 * @attributes
 * HitPoint     Interger int
 * damage       Interger int
 * armor        Interger int
 * price        Interger int
 * 
 * @object
 * A typical Tank is <hp,d,a,p> where HitPoint(hp),damage(d),armor(a),price(p)
 * 
 * @abstract_properties
 * mutable(HP)=true /\ optional(HP) = false /\ min(HP) = 1 /\
 * mutable(damage)=true /\ optional(damage) = false /\ min(damage) = 1 /\
 * mutable(armor)=true /\ optional(armor) = false /\ min(armor) = 0 /\
 * mutable(price)=true /\ optional(price) = false /\ min(price) = 1 /\
 */
public class Tank{
    @DomainConstraint(type = "int", mutable = true, optional = false, min = 1)
    private int HP;
    @DomainConstraint(type = "int", mutable = true, optional = false, min = 1)
    private int damage;
    @DomainConstraint(type = "int", mutable = true, optional = false, min = 0)
    private int armor;
    @DomainConstraint(type = "int", mutable = true, optional = false, min = 1)
    private int price;
    /**
     * @effects
     *  if hp, d,a,p are valid
     *     initialize as <tt>(hp,d,a,p)</tt>
     *  else
     *      throw NotPossibleException
     */
    @DOpt(type = OptType.Helper) @AttrRef("HP")
    private static boolean validateHP(int HP) {
        return HP >= 1;
    }
    @DOpt(type = OptType.Helper) @AttrRef("damage")
    private static boolean validateDamage(int damage) {
        return damage >= 1;
    }
    @DOpt(type = OptType.Helper) @AttrRef("armor")
    private static boolean validateArmor(int armor) {
        return armor >= 0;
    }
    @DOpt(type = OptType.Helper) @AttrRef("price")
    private static boolean validatePrice(int price) {
        return price >= 1;
    }
    public boolean repOK() {
        return validateHP(HP) && validateDamage(damage) && validateArmor(armor) && validatePrice(price);
    }
    public Tank(@AttrRef("HP")int HP,@AttrRef("damage") int damage,@AttrRef("armor") int armor,@AttrRef("price") int price) {
        if (!validateHP(HP)) {
            throw new NotPossibleException("Invalid " + HP);
        }
        if (!validateDamage(damage)) {
            throw new NotPossibleException("Invalid " + damage);
        }
        if (!validateArmor(armor)) {
            throw new NotPossibleException("Invalid " + armor);
        }
        if (!validatePrice(price)) {
            throw new NotPossibleException("Invalid " + price);
        }
        this.HP = HP;
        this.damage = damage;
        this.armor = armor;
        this.price = price;
    }
    /**
     * @effects
     * if HP is valid
     * set this.HP=HP
     * else false
     */
    @DOpt(type = OptType.Mutator) @AttrRef("HP")
    public boolean setHP(int HP) {
        if(validateHP(HP)) {
            this.HP = HP;
            return true;
        }
        return false;
    }
    /**
     * @effects 
     * return HP
     */
    @DOpt(type = OptType.Observer) @AttrRef("HP")
    public int getHP() {
        return HP;
    }
    /**
     * @effects
     * if damage is valid
     * set this.damage=damage
     */
    @DOpt(type = OptType.Mutator) @AttrRef("damage")
    public boolean Damage(int damage) {
        if(validateDamage(damage)) {
            this.damage = damage;
            return true;
        }
        return false;
    }
    /**
     * @effects
     * return damage
     */
    @DOpt(type = OptType.Observer) @AttrRef("damage")
    public int getDamage() {
        return damage;
    }
    /**
     * @effects
     * set this.armor=armor
     */
    @DOpt(type = OptType.Mutator) @AttrRef("armor")
    public void setArmor(int armor) {
        this.armor = armor;
    }
    /**
     * @effects
     * return armor
     */
    @DOpt(type = OptType.Observer) @AttrRef("armor")
    public int getArmor() {
        return armor;
    }
    /**
     * @effects
     * set this.price=price
     */
    @DOpt(type = OptType.Mutator) @AttrRef("price")
    public void setPrice(int price) {
        this.price = price;
    }
    /**
     * @effects
     * return price
     */
    @DOpt(type = OptType.Observer) @AttrRef("price")
    public int getPrice() {
        return price;
    }
    @Override 
    public String toString() {
        return String.format("Tank(%d,%d,%d,%d)", HP, damage, armor, price);
    }
}