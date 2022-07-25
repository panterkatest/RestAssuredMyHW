
package dto;

import lombok.*;

import javax.annotation.Generated;

@Data
@Builder
@AllArgsConstructor
public class UserNew {

    private String email;
    private String firstName;
    private Long id;
    private String lastName;
    private String password;
    private String phone;
    private Long userStatus;
    private String username;
}
