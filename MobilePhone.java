
import utils. *;

enum Color {
    R, O, Y, B, P, Bl;
}

/**
 * @Overview A mobile phone that can be used by a person
 *
 * @Attribute ManName String String Model String String color Color Color year
 * Integer int month Integer int
 *
 * @Object A type of MobilePhone is (ManName,Model,color,year,month)
 *
 * @Abstract_properties mutable (ManName) = true /\ optional(ManName) = false /\
 * mutable (Model) = false /\ optional(Model) = true /\ mutable (Color) = true
 * /\ optional(Color) = true /\ mutable (year) = false /\ optional(year) = true
 * /\ mutable (Guarenteed) = false /\ optional(Guarenteed) = false /\
 *
 *
 */
public class MobilePhone {

    @DomainConstraint(type = "String", mutable = true, optional = false)
    private String ManName;
    @DomainConstraint(type = "String", mutable = false, optional = true)
    private String Model;
    @DomainConstraint(type = "Color", mutable = true, optional = true)
    private Color color;
    @DomainConstraint(type = "int", mutable = false, optional = true)
    private int year;
    @DomainConstraint(type = "boolean", mutable = false, optional = false)
    private boolean guaranteed;

    /**
     * @effects if ManName,Model,color,year,guaranteed are valid initialize as
     * <tt>(ManName,Model,color,year,guaranteed)</tt>
     * else throw NotPossibleException
     */
    public MobilePhone(String ManName, String Model, Color color, int year, boolean guaranteed) {
        setManName(ManName);
        setModel(Model);
        setColor(color);
        setYear(year);
        this.guaranteed = guaranteed;
    }

    /**
     * @effects return ManName
     */
    @DOpt(type = OptType.Observer)
    @AttrRef("ManName")
    public String getManName() {
        return ManName;
    }

    /**
     * @effects return Model
     */
    @DOpt(type = OptType.Observer)
    @AttrRef("Model")
    public String getModel() {
        return Model;
    }

    /**
     * @effects return Color
     */
    @DOpt(type = OptType.Observer)
    @AttrRef("Color")
    public Color getColor() {
        return color;
    }

    /**
     * @effects return Year
     */
    @DOpt(type = OptType.Observer)
    @AttrRef("Year")
    public int getYear() {
        return year;
    }

    /**
     * @effects return guaranteed
     */
    @DOpt(type = OptType.Observer)
    @AttrRef("guaranteed")
    public boolean isGuaranteed() {
        return guaranteed;
    }

    /**
     * @effects return true if ManName is valid
     */
    @DOpt(type = OptType.Helper)
    @AttrRef("ManName")
    public boolean isValidManName(String ManName) {
        return ManName != null && ManName.trim().split("\\s+").length >= 2;
    }

    /**
     * @effects if ManName is not valid throw Exception else this.ManName =
     * ManName
     */
    @DOpt(type = OptType.Mutator)
    @AttrRef("ManName")
    public void setManName(String ManName) {
        if (!isValidManName(ManName)) {
            throw new IllegalArgumentException("Name must consist of at least two words separated by spaces.");
        } else {
            this.ManName = ManName;
        }
    }

    /**
     * @effects return true if Model is valid (must be of the form M-ABC−MNP,
     * where ABC is a 3-letter word and MNP is a 3-digit word.)
     */
    @DOpt(type = OptType.Helper)
    @AttrRef("Model")
    private boolean isValidModel(String model) {
        return model != null && model.matches("M-[A-Z]{3}-\\d{3}");
    }

    /**
     * @effects if Model is not valid throw Exception else this.Model = Model
     */
    @DOpt(type = OptType.Mutator)
    @AttrRef("Model")
    public void setModel(String Model) {
        if (!isValidModel(Model)) {
            throw new IllegalArgumentException("Invalid model name (must be in the form M-ABC-MNP, where ABC is a 3-letter word and MNP is a 3-digit number).");
        } else {
            this.Model = Model;
        }
    }

    /**
     * @effects set this.year= year
     */
    @DOpt(type = OptType.Mutator)
    @AttrRef("Year")
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @effects set this.color = color
     */
    @DOpt(type = OptType.Mutator)
    @AttrRef("Color")
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @effects return MobilePhone as String
     */
    @DOpt(type = OptType.Default)
    @Override
    public String toString() {
        return "MobilePhone [ManName=" + ManName + ", Model=" + Model + ", color=" + color + ", year=" + year
                + ", guaranteed=" + guaranteed + "]";
    }
}
