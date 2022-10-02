package brokilone.tacocloud.tacos;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author Kseniia Ushakova
 */
@Data
@Entity
public class TacoOrder implements Serializable {

  private static final long SerialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private Date placedAt = new Date();
  private String deliveryName;
  private String deliveryStreet;
  private String deliveryCity;
  private String deliveryState;
  private String deliveryZip;

//  @CreditCardNumber(message="Not a valid credit card number")
  private String ccNumber;

//  @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$",
//      message="Must be formatted MM/YY")
  private String ccExpiration;

  @Digits(integer=3, fraction=0, message="Invalid CVV")
  private String ccCVV;

  @OneToMany(cascade = CascadeType.ALL)
  private List<Taco> tacos = new ArrayList<>();

  @ManyToOne
  private User user;

  public void addTaco(Taco taco) {
    this.tacos.add(taco);
  }

  public void setUser(User user) {
    this.user = user;
    this.deliveryCity = user.getCity();
    this.deliveryName = user.getFullname();
    this.deliveryState = user.getState();
    this.deliveryStreet = user.getStreet();
    this.deliveryZip = user.getZip();
  }
}
