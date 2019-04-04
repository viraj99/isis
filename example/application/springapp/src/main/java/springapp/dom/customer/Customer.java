package springapp.dom.customer;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Nature;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import springapp.dom.email.Email;

@DomainObject(nature=Nature.EXTERNAL_ENTITY)
@Entity 
@NoArgsConstructor(access = AccessLevel.PROTECTED) @RequiredArgsConstructor @ToString
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter private Long id;
    
    @Getter @NonNull private String firstName;
    @Getter @NonNull private String lastName;
    
    @OneToMany(mappedBy = "customer")
    @Getter @Setter 
    private List<Email> emails;

}