import utils. *;

/**
 * @Overview A projectile that can be fired by tank
 * 
 * @atributes
 *    damage    Integer  int
 *    radius    Integer  int
 *    angle     Integer  int
 * 
 * @object
 *    A type of Projectile is <pre> pt= <d,r,a></pre> where
 *          <pre> damage(d), radius(r), angle(a) </pre>
 * 
 * @abstract_properties
 *     mutable(damage) = true /\ optional(damage) = false /\ min(damage) = 1
 *     mutable(radius) = true /\ optional(radius) = false /\ min(radius) = 0+
 *     mutable(angle) = true /\ optional(angle) = false /\ min(angle) = 0
 */
public class Projectile{
    @DomainConstraint(type = "Integer", mutable = true, optional = false, min = 1)
    private int d;
    @DomainConstraint(type = "Integer", mutable = true, optional = false, min = 0)
    private int r;
    @DomainConstraint(type = "Integer", mutable = true, optional = false, min = 0)
    private int a;
}