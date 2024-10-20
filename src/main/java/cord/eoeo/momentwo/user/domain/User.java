package cord.eoeo.momentwo.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String birthday;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String userProfileImage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleUserGrade roleUserGrade;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate createDate;

    @PrePersist // DB에 Insert 되기 직전에 실행된다.
    public void initDateAndGrade(){
        this.createDate = LocalDate.now();
        this.roleUserGrade = RoleUserGrade.ROLE_COMMON_MEMBER;
    }

    public User(String name, String username, String password,
                String nickname, String birthday, String phone, String userProfileImage){
        this.name = name;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.birthday = birthday;
        this.phone = phone;
        this.userProfileImage = userProfileImage;
    }
}
