package id.atmaja.test.dao.user;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Entity
@Table(name = "user_model")
@Getter
@Setter
@NoArgsConstructor
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Nonnull
    @Size(min = 10, max = 13, message = "Phonenumebr length must be at least 10 or max 13")
    @Column(name = "phonenumber", nullable = false, unique = true)
    private String phonenumber;

    @Nonnull
    @Size(min = 1, max = 60)
    @Column(name = "name", nullable = false)
    private String name;

    @Nonnull
    @Column(name = "password", nullable = false)
    private String password;

    public void merge(final UserModel userModel) {
        if (userModel.phonenumber != null) {
            this.phonenumber = userModel.phonenumber;
        }

        if (userModel.password != null) {
            this.password = userModel.password;
        }

        if (userModel.name != null) {
            this.name = userModel.name;
        }
    }
}
